package keybladewarrior.actions.wiseActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

public class WisdomShotAction extends AbstractGameAction {
    AbstractPlayer p;

    public WisdomShotAction(AbstractPlayer p){
        this.p = p;

    }

    @Override
    public void update(){
        if (p.stance instanceof AbstractDriveForm && ((AbstractDriveForm) p.stance).hasTag(CustomTags.WISE)) {
             addToTop(new DiscardAction(p,p,1,false));
        }
        this.isDone = true;
    }

}
