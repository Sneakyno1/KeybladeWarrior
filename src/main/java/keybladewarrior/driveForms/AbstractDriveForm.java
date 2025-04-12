package keybladewarrior.driveForms;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import keybladewarrior.cards.skills.ExitDrive;
import keybladewarrior.powers.DrivePoints;

public class AbstractDriveForm extends AbstractStance {
    public int BaseCostToEnterForm = 0;
    public int CurrentFormCost = 0;
    public int BaseFormCostPerTurn = 0;
    public int CurrentFormCostPerTurn = 0;
    public int FormCostModifier = 0;
    public float FormCostMultiplier = 1;

    @Override
    public void onEnterStance(){
        AbstractPlayer p = AbstractDungeon.player;
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);

        //check if player has enough to enter
        if (Drive != null && Drive.amount >= CurrentFormCost){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrivePoints(p),-CurrentFormCost));
            //Drive.amount = Math.max((Drive.amount - CurrentFormCost), 0);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ExitDrive(), 1, false));
        }
        //if player doesn't have enough drive points to change we'll force them back into neutral
        else {
            AbstractDungeon.actionManager.addToBottom(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction("Neutral"));
        }

    }

    @Override
    public void onExitStance(){

    }

    @Override
    public void onEndOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);

        //check if the player has any drive points and keep in the form if so while updating the drive points
        if (Drive != null && Drive.amount > 0){
            Drive.amount = Math.max((Drive.amount - CurrentFormCostPerTurn), 0);
        }else {
            //right now player is just force back into neutral
            //TODO: force player into antiform when its made
            AbstractDungeon.actionManager.addToBottom(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction("Neutral"));
        }

    }

    @Override
    public void updateDescription() {

    }
}
