package keybladewarrior.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import keybladewarrior.util.CustomTags;

public class AbstractConsumeDrivePointsAction extends AbstractGameAction {
    public AbstractConsumeDrivePointsAction(){
        super();
    }

    @Override
    public void update() {
    }

    public boolean PreviousCardWasAComboCard(){

        boolean IgnoreComboCheck = false;
        if (IgnoreComboCheck){
            return true;
        }

        return (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 &&
                (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(
                        AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).hasTag(CustomTags.COMBO));
    }
}
