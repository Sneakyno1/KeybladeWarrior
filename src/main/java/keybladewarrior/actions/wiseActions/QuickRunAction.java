package keybladewarrior.actions.wiseActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

public class QuickRunAction extends AbstractGameAction {
    AbstractPlayer p;
    int drawAmount;
    int discardAmount;

    public QuickRunAction(AbstractPlayer p, int drawAmount, int discardAmount){
        this.p = p;
        this.drawAmount = drawAmount;
        this.discardAmount = discardAmount;


    }

    @Override
    public void update(){
        if (p.stance instanceof AbstractDriveForm && ((AbstractDriveForm) p.stance).hasTag(CustomTags.WISE)) {
            if (discardAmount - 1 > 0){
                addToTop(new DiscardAction(p,p,discardAmount - 1,false));
            }
        }else {
            addToTop(new DiscardAction(p,p,discardAmount,false));
        }
        addToTop(new DrawCardAction(p, drawAmount));
        this.isDone = true;
    }

}
