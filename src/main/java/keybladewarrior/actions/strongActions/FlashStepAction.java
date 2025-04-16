package keybladewarrior.actions.strongActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

public class FlashStepAction extends AbstractGameAction {
    AbstractPlayer p;
    int AmountToApply;

    public FlashStepAction(AbstractPlayer p, int AmountToApply){
        this.p = p;
        this.AmountToApply = AmountToApply;

    }

    @Override
    public void update(){
        if (((AbstractDriveForm)p.stance).DriveTags.contains(CustomTags.STRONG)){
            addToBot(new DrawCardAction(p,AmountToApply));
        }
        this.isDone = true;
    }

}
