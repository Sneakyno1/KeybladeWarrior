package keybladewarrior.driveForms;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import keybladewarrior.cards.ExitDrive;
import keybladewarrior.powers.DrivePoints;

public class AbstractDriveForm extends AbstractStance {

    @Override
    public void onEnterStance(){
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction((AbstractCard)new ExitDrive(), 1, false));
    }

    @Override
    public void onExitStance(){
        AbstractPlayer p = AbstractDungeon.player;
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);

        if (Drive != null){
            Drive.CurrentFormCost = 0;
        }

    }

    @Override
    public void updateDescription() {

    }
}
