package keybladewarrior.driveForms;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import keybladewarrior.cards.skills.ExitDrive;
import keybladewarrior.powers.DrivePoints;

import java.util.ArrayList;

import static keybladewarrior.util.Wiz.getCardsMatchingPredicate;

public class AbstractDriveForm extends AbstractStance {
    public int BaseCostToEnterForm = 0;
    public int CurrentFormCost = 0;
    public int BaseFormCostPerTurn = 0;
    public int CurrentFormCostPerTurn = 0;
    public int FormCostModifier = 0;
    public float FormCostMultiplier = 1;
    public boolean IgnoreFormCostPerTurn = false;
    public boolean IgnoreCostToEnterForm = false;


    public AbstractDriveForm(){}

    public AbstractDriveForm(boolean IgnoreCostToEnterForm){
        this.IgnoreCostToEnterForm = IgnoreCostToEnterForm;
    }

    public boolean ExitDriveInWorkingDeck(AbstractPlayer p){
        for (AbstractCard c : p.hand.group) {
            if (c.cardID.equals(ExitDrive.ID)) {
               return  true;
            }

        }

        for (AbstractCard c : p.discardPile.group) {
            if (c.cardID.equals(ExitDrive.ID)) {
                return  true;
            }

        }

        for (AbstractCard c : p.drawPile.group) {
            if (c.cardID.equals(ExitDrive.ID)) {
                return  true;
            }

        }

        return false;
    }

    private void ExhaustExitDriveIfInWorkingDeck(AbstractPlayer p) {
        for (AbstractCard c : p.hand.group) {
            if (c.cardID.equals(ExitDrive.ID)) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.hand, false));
            }

        }

        for (AbstractCard c : p.discardPile.group) {
            if (c.cardID.equals(ExitDrive.ID)) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.discardPile, false));
            }

        }

        for (AbstractCard c : p.drawPile.group) {
            if (c.cardID.equals(ExitDrive.ID)) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.drawPile, false));
            }

        }
    }

    @Override
    public void onEnterStance() {
        AbstractPlayer p = AbstractDungeon.player;
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);

        //check if player has enough drive points to enter
        if (IgnoreCostToEnterForm){
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ExitDrive(), 1, false));
        } else if (Drive != null && Drive.amount >= CurrentFormCost){
            Drive.reducePower(CurrentFormCost);
            //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrivePoints(p),-CurrentFormCost));

            if (!ExitDriveInWorkingDeck(p)){
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ExitDrive(), 1, false));
            }
        }
        //if player doesn't have enough drive points to change we'll force them back into their current form
        else {
            AbstractDriveForm currForm = (AbstractDriveForm) p.stance;

            currForm.IgnoreCostToEnterForm = true;
            AbstractDungeon.actionManager.addToBottom(new NotStanceCheckAction(currForm.ID, new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(currForm));
            currForm.IgnoreCostToEnterForm = false;
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
        if (IgnoreFormCostPerTurn) {
            return;
        }
        else if (Drive != null && Drive.amount > 0) {
            Drive.reducePower(CurrentFormCostPerTurn);
            //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrivePoints(p), -CurrentFormCostPerTurn));
        }
        else {
            //right now player is just force back into neutral
            //also removes exit drive card from players working deck if they were forced out of a drive form
            //TODO: force player into antiform when its made
            ExhaustExitDriveIfInWorkingDeck(p);

            AbstractDungeon.actionManager.addToBottom(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction("Neutral"));
        }
    }



    @Override
    public void updateDescription() {

    }

}
