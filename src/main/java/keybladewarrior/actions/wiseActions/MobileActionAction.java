package keybladewarrior.actions.wiseActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

public class MobileActionAction extends AbstractGameAction {
    AbstractPlayer p;

    public MobileActionAction(AbstractPlayer p){
        this.p = p;

    }

    @Override
    public void update(){
        addToTop(
                new DiscardAction(
                        p,p,1,

                        //boolean check for if the player is in wisdom form
                        !(p.stance instanceof AbstractDriveForm && ((AbstractDriveForm) p.stance).hasTag(CustomTags.WISE))
                )
        );
        this.isDone = true;
    }

}
