package keybladewarrior.relics.uncommon;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import keybladewarrior.actions.miscellaneousActions.ProofOfConnectionChooseOneCardOfAnyColor;
import keybladewarrior.relics.AbstractEasyRelic;

import static keybladewarrior.ModFile.makeID;

public class ProofOfConnection extends AbstractEasyRelic {
    public static final String ID = makeID(ProofOfConnection.class.getSimpleName());

    public ProofOfConnection() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, null);
    }

    @Override
    public void atBattleStartPreDraw() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ProofOfConnectionChooseOneCardOfAnyColor());
    }

}
