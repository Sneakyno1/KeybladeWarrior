package keybladewarrior.actions.comboActions;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class QuickThinkingAction extends AbstractComboAction{
    AbstractPlayer p;

    public QuickThinkingAction(AbstractPlayer p) {
        this.p = p;
    }

    public void update() {
        if (this.PreviousCardWasAComboCard()){
            addToTop(new DrawCardAction(p,1));
        }

        this.isDone = true;
    }
}
