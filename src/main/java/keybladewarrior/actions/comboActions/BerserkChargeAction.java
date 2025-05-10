package keybladewarrior.actions.comboActions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import keybladewarrior.powers.DrivePoints;

public class BerserkChargeAction extends AbstractComboAction{
    AbstractPlayer p;
    int DrivePointsToApply;


    public BerserkChargeAction(AbstractPlayer p, int magicNumber) {
        this.p = p;
        this.DrivePointsToApply = magicNumber;

    }

    public void update() {
        if (this.PreviousCardWasAComboCard()){
            addToBot(new ApplyPowerAction(p,p,new DrivePoints(AbstractDungeon.player,DrivePointsToApply),DrivePointsToApply));
        }
        this.isDone = true;
    }
}
