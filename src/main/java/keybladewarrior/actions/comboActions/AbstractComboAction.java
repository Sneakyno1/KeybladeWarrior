package keybladewarrior.actions.comboActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import keybladewarrior.util.CustomTags;

public class AbstractComboAction extends AbstractGameAction {
    public boolean IgnoreComboCheck = false;
    public AbstractComboAction(){
        super();
    }

    @Override
    public void update() {
    }

    public boolean PreviousCardWasAComboCard(){

        if (IgnoreComboCheck){
            return true;
        }

        return (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 &&
                (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(
                        AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).hasTag(CustomTags.COMBO));
    }
}
