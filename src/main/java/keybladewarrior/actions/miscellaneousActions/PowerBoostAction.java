package keybladewarrior.actions.miscellaneousActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.StrengthPower;
import keybladewarrior.powers.DrivePoints;

public class PowerBoostAction extends AbstractGameAction {
    AbstractPlayer p;
    int StrengthDivisor;

    public PowerBoostAction(AbstractPlayer p, int divisor){
        this.p = p;
        this.StrengthDivisor = divisor;

    }

    @Override
    public void update(){
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);
        int StrengthToAdd = 0;

        if (Drive != null && Drive.amount > 0){
            StrengthToAdd = Drive.amount / this.StrengthDivisor;
        }

        if (StrengthToAdd > 0){
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, StrengthToAdd), StrengthToAdd));
        }
        this.isDone = true;
    }

}
