package keybladewarrior.relics;

import keybladewarrior.KeybladeWarrior;

import static keybladewarrior.ModFile.makeID;

public class StarterRelicForSora extends AbstractEasyRelic {
    public static final String ID = makeID("StarterRelicForSora");

    public StarterRelicForSora() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, KeybladeWarrior.Enums.CARD_COLOR);
    }


//    @Override
//    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
//        if (newStance instanceof WisdomForm || newStance instanceof ValorForm){
//                addToBot(new GainBlockAction(AbstractDungeon.player,3));
//            }
//
//    }
}
