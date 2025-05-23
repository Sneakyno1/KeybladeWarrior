package keybladewarrior.actions.wiseActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

public class DashAction extends AbstractGameAction {
    AbstractPlayer p;
    int AmountToApply;

    public DashAction(AbstractPlayer p, int AmountToApply){
        this.p = p;
        this.AmountToApply = AmountToApply;

    }

    @Override
    public void update(){
        if (p.stance instanceof AbstractDriveForm){
            if (((AbstractDriveForm) p.stance).hasTag(CustomTags.WISE)) {
                addToBot(new GainBlockAction(p, p, AmountToApply));
            }
        }
        this.isDone = true;
    }

}
