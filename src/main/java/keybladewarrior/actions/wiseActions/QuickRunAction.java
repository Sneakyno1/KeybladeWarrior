package keybladewarrior.actions.wiseActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

public class QuickRunAction extends AbstractGameAction {
    AbstractPlayer p;
    int drawAmount;
    int discardAmount;
    int extraDrawAmount;

    public QuickRunAction(AbstractPlayer p, int drawAmount, int discardAmount, int extraDrawAmount){
        this.p = p;
        this.drawAmount = drawAmount;
        this.discardAmount = discardAmount;
        this.extraDrawAmount = extraDrawAmount;


    }

    @Override
    public void update(){
        addToTop(new DiscardAction(p,p, discardAmount,false));
        if (p.stance instanceof AbstractDriveForm && ((AbstractDriveForm) p.stance).hasTag(CustomTags.WISE)) {
            addToTop(new DrawCardAction(p, drawAmount + extraDrawAmount));
        }else {
            addToTop(new DrawCardAction(p, drawAmount));

        }
        this.isDone = true;
    }

}
