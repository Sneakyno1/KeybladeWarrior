package keybladewarrior.patches.rewards;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import javassist.CtBehavior;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.cards.skills.MidSynthesis;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


@SuppressWarnings("unused")
public class SynthesisRewardPatch{

    //Adds a functionality to generate and add a synthesis reward to the combat rewards
    @SpirePatch(clz = AbstractRoom.class,
                method = "update")
    public static class updateRoom {
        @SpireInsertPatch(locator = SynthesisRewardPatch.updateRoom.Locator.class)
        public static SpireReturn<Void> Insert(AbstractRoom _instance) {

                int chance = 0;
                if (_instance instanceof MonsterRoomElite) {
                    chance = 60;
                    //chance += blizzardPotionMod;
                } else if (_instance instanceof MonsterRoom) {
                    if (!AbstractDungeon.getMonsters().haveMonstersEscaped()) {
                        chance = 30;
                       // chance += blizzardPotionMod;
                    }
                } else if (_instance instanceof EventRoom) {
                    chance = 40;
                    //chance += blizzardPotionMod;
                }
                if (_instance.rewards.size() >= 4)
                    chance = 0;
                if (MathUtils.random(0, 99) < chance || Settings.isDebug || true) {
                    //CardCrawlGame.metricData.potions_floor_spawned.add(Integer.valueOf(AbstractDungeon.floorNum));

                    //make into synthesis reward instead of potion
                    _instance.rewards.add(getSynthesisCardReward());
                    //blizzardPotionMod -= 10;
                } else {

                    //blizzardPotionMod += 10;
                }


            return SpireReturn.Continue();
        }

        private static class Locator
                extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractRoom.class, "addPotionToRewards");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    @SpirePatch(clz = CombatRewardScreen.class,
            method = "updateEffects")
    public static class updateEffects {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(CombatRewardScreen _instance) {

            CardGroup synthesisGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c instanceof AbstractSynthesisCard && c.hasTag(CustomTags.SYNTHESIS_MATERIAL)) {
                    synthesisGroup.addToTop(c);
                }
            }

            if (synthesisGroup.size()>1){
                if  (synthesisGroup.findCardById(MidSynthesis.ID)!=null){
                    AbstractSynthesisCard existingSynthesis = (AbstractSynthesisCard) synthesisGroup.findCardById(MidSynthesis.ID);
                    for (AbstractCard c: synthesisGroup.group){
                        if (!Objects.equals(c.cardID, MidSynthesis.ID)) {
                            ((AbstractSynthesisCard) c).AddSynthesisEffect(existingSynthesis);
                            AbstractDungeon.player.masterDeck.removeCard(c);
                        }
                    }
                }else{
                    AbstractSynthesisCard MidSynthesis = new MidSynthesis();

                    for (AbstractCard c: synthesisGroup.group){
                        ((AbstractSynthesisCard) c).AddSynthesisEffect(MidSynthesis);
                        AbstractDungeon.player.masterDeck.removeCard(c);
                    }

                    AbstractDungeon.topLevelEffects.add(new FastCardObtainEffect(MidSynthesis, MidSynthesis.current_x, MidSynthesis.current_y));
                }
            }





            return SpireReturn.Continue();
        }

        private static class Locator
                extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(CardRewardScreen.class, "acquireCard");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    public static RewardItem getSynthesisCardReward() {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        AbstractPlayer player = AbstractDungeon.player;
        int numCards = 3;
        RewardItem reward = new RewardItem();
        reward.cards.clear();

//        for (AbstractRelic r : player.relics)
//            numCards = r.changeNumberOfCardsInReward(numCards);
//
//        if (ModHelper.isModEnabled("Binary"))
//            numCards--;

        for (int i = 0; i < numCards; i++) {
            AbstractCard card = null;
            boolean containsDupe = true;

            while (containsDupe) {
                containsDupe = false;

                card = getRandomSynthesisCard();

                for (AbstractCard c : retVal) {
                    if (c.cardID.equals(card.cardID))
                        containsDupe = true;
                }
            }
            if (card != null)
                retVal.add(card);
        }

        ArrayList<AbstractCard> retVal2 = new ArrayList<>();
        for (AbstractCard c : retVal)
            retVal2.add(c.makeCopy());
        for (AbstractCard c : retVal2) {
//            if (c.rarity != AbstractCard.CardRarity.RARE && cardRng.randomBoolean(cardUpgradedChance) && c.canUpgrade()) {
//                c.upgrade();
//                continue;
//            }
            for (AbstractRelic r : player.relics)
                r.onPreviewObtainCard(c);
        }

        reward.cards.addAll(retVal2);
        reward.text = "Choose a Synthesis Material";
        return reward;
    }


    public static AbstractCard getRandomSynthesisCard() {
        ArrayList<AbstractCard> synthesisCards = new ArrayList();
        AbstractCard card;

        for(Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            if (    (c.getValue()).color == AbstractCard.CardColor.COLORLESS
                    && (c.getValue()).hasTag(CustomTags.SYNTHESIS_MATERIAL)
                    && !(Objects.equals((c.getValue()).cardID, MidSynthesis.ID))
                ) {

                synthesisCards.add(c.getValue());
            }
        }


        card = synthesisCards.get(MathUtils.random(synthesisCards.size() - 1));

        return card;
    }

}
