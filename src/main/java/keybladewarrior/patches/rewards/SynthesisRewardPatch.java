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
import keybladewarrior.relics.StarterRelicForSora;
import keybladewarrior.rewards.SynthesisReward;
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
                    chance = 80;
                    //chance += blizzardPotionMod;
                } else if (_instance instanceof MonsterRoom) {
                    if (!AbstractDungeon.getMonsters().haveMonstersEscaped()) {
                        chance = 30;
                       // chance += blizzardPotionMod;
                    }
                } else if (_instance instanceof EventRoom) {
                    chance = 50;
                    //chance += blizzardPotionMod;
                }
                if (MathUtils.random(0, 99) < chance || Settings.isDebug) {
                    //CardCrawlGame.metricData.potions_floor_spawned.add(Integer.valueOf(AbstractDungeon.floorNum));

                    _instance.rewards.add(new SynthesisReward());
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

                        existingSynthesis.initializeDescription();
                        existingSynthesis.update();
                    }
                }else{
                    AbstractSynthesisCard MidSynthesis = new MidSynthesis();

                    for (AbstractCard c: synthesisGroup.group){
                        ((AbstractSynthesisCard) c).AddSynthesisEffect(MidSynthesis);
                        AbstractDungeon.player.masterDeck.removeCard(c);
                    }

                    MidSynthesis.initializeDescription();
                    MidSynthesis.update();
                    AbstractDungeon.topLevelEffects.add(new FastCardObtainEffect(MidSynthesis.makeStatEquivalentCopy(), MidSynthesis.current_x, MidSynthesis.current_y));
                }
            }

            return SpireReturn.Continue();
        }

    }

    @SpirePatch(clz = AbstractDungeon.class,
            method = "getRewardCards")
    public static class getRewardCards {
        @SpirePostfixPatch
        public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> _result) {
            ArrayList<AbstractCard> synthesisCards = new ArrayList<AbstractCard>();

            if (AbstractDungeon.player.hasRelic(StarterRelicForSora.ID)){
                for(Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
                    if (    (c.getValue()).color == AbstractCard.CardColor.COLORLESS
                            && (c.getValue()).hasTag(CustomTags.SYNTHESIS_MATERIAL)
                            && !(Objects.equals((c.getValue()).cardID, MidSynthesis.ID))
                    ) {

                        synthesisCards.add(c.getValue());
                    }
                }

                _result.add(synthesisCards.get(AbstractDungeon.cardRng.random(synthesisCards.size() - 1)));

            }

            return _result;
        }
    }


}
