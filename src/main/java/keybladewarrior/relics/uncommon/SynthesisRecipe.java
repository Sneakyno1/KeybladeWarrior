package keybladewarrior.relics.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import keybladewarrior.relics.AbstractEasyRelic;

import static keybladewarrior.ModFile.makeID;

public class SynthesisRecipe extends AbstractEasyRelic {
    public static final String ID = makeID(SynthesisRecipe.class.getSimpleName());

    public SynthesisRecipe() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, null);
        this.counter = 3;
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        if (room instanceof com.megacrit.cardcrawl.rooms.ShopRoom) {
            flash();
            this.pulse = true;
        } else {
            this.pulse = false;
        }
    }

}
