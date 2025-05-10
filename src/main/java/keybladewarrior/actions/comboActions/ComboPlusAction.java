package keybladewarrior.actions.comboActions;

import com.megacrit.cardcrawl.cards.AbstractCard;

public class ComboPlusAction extends AbstractComboAction{
    AbstractCard c;

    public ComboPlusAction(AbstractCard c) {
        this.c = c;
    }

    public void update() {
        c.shuffleBackIntoDrawPile = this.PreviousCardWasAComboCard();
        this.isDone = true;
    }
}
