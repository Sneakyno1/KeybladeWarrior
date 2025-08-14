package keybladewarrior.actions.comboActions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

public class DodgeRollAction extends AbstractComboAction{
    AbstractPlayer p;
    int NextTurnBlock;

    public DodgeRollAction(AbstractPlayer p, int magicNumber) {
        this.p = p;
        this.NextTurnBlock = magicNumber;

    }

    public void update() {
        if (this.PreviousCardWasAComboCard()){
            addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, NextTurnBlock), NextTurnBlock));
        }

        this.isDone = true;
    }
}
