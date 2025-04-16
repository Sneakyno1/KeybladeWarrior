package keybladewarrior.actions.comboActions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

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
