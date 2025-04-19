package keybladewarrior.actions.miscellaneousActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.StrengthPower;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.powers.DrivePoints;

public class AffinityForValorAction extends AbstractGameAction {
    AbstractPlayer p;
    int StrengthGain;

    public AffinityForValorAction(AbstractPlayer p, int amount){
        this.p = p;
        this.StrengthGain = amount;

    }

    @Override
    public void update(){
        if (p.stance.ID.equals(ValorForm.STANCE_ID)){
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.StrengthGain), this.StrengthGain));
        }else {
            addToBot(new ChangeStanceAction(ValorForm.STANCE_ID));
        }

        this.isDone = true;
    }

}
