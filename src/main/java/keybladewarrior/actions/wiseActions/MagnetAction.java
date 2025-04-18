package keybladewarrior.actions.wiseActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

public class MagnetAction extends AbstractGameAction {
    AbstractPlayer p;
    int amount;

    public MagnetAction(AbstractPlayer p, int amount){
        this.p = p;
        this.amount = amount;

    }

    @Override
    public void update(){
        if (p.stance instanceof AbstractDriveForm){
            if (((AbstractDriveForm) p.stance).hasTag(CustomTags.WISE)) {
                addToTop(new BetterDiscardPileToHandAction(amount));
            }
        }
        this.isDone = true;
    }

}
