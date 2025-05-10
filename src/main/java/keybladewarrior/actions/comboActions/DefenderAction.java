package keybladewarrior.actions.comboActions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.BlurPower;

public class DefenderAction extends AbstractComboAction{
    AbstractPlayer p;
    int BlurToApply;

    public DefenderAction(AbstractPlayer p, int magicNumber) {
        this.p = p;
        this.BlurToApply = magicNumber;

    }

    public void update() {
        if (this.PreviousCardWasAComboCard()){
            addToTop(new ApplyPowerAction(p,p,new BlurPower(p,BlurToApply), BlurToApply));
        }

        this.isDone = true;
    }
}
