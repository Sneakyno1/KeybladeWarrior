package keybladewarrior.relics;

import com.megacrit.cardcrawl.stances.AbstractStance;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.driveForms.WisdomForm;

import static keybladewarrior.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");
    Boolean FirstValorOrWisdomEntry = true;

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, KeybladeWarrior.Enums.CARD_COLOR);
    }

    @Override
    public void atPreBattle() {
        this.beginLongPulse();
        FirstValorOrWisdomEntry = true;
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (FirstValorOrWisdomEntry){
            if (newStance instanceof WisdomForm || newStance instanceof ValorForm){
                ((AbstractDriveForm) newStance).IgnoreCostToEnterForm = true;
                FirstValorOrWisdomEntry = false;
                this.stopPulse();
                this.usedUp();
            }
        }

    }
}
