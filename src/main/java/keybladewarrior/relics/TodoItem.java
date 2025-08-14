package keybladewarrior.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.driveForms.WisdomForm;

import static keybladewarrior.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, KeybladeWarrior.Enums.CARD_COLOR);
    }


    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (newStance instanceof WisdomForm || newStance instanceof ValorForm){
                addToBot(new GainBlockAction(AbstractDungeon.player,3));
            }

    }
}
