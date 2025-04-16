package keybladewarrior.actions.miscellaneousActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.DexterityPower;
import keybladewarrior.powers.DrivePoints;

public class DefenseBoostAction extends AbstractGameAction {
    AbstractPlayer p;
    int DexDivisor;

    public DefenseBoostAction(AbstractPlayer p, int AmountToApply){
        this.p = p;
        this.DexDivisor = AmountToApply;

    }

    @Override
    public void update(){
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);
        if (Drive != null && Drive.amount >= this.DexDivisor){
            Drive.reducePower(this.DexDivisor);
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
        }
        this.isDone = true;
    }

}
