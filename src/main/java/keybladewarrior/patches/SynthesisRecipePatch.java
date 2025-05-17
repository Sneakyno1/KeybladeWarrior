package keybladewarrior.patches;

// Code adapted from github: https://github.com/torrentails/StS-Marked-Mod/tree/master


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.MetricData;
import com.megacrit.cardcrawl.shop.OnSaleTag;
import com.megacrit.cardcrawl.shop.ShopScreen;
import javassist.CtBehavior;
import keybladewarrior.relics.uncommon.SynthesisRecipe;

@SuppressWarnings("unused")
public class SynthesisRecipePatch {

    @SpirePatch(clz = ShopScreen.class,
            method = "initCards")
    public static class initCards {
        @SpireInsertPatch(locator = Locator.class,
                          localvars={"saleCard"})
        public static SpireReturn<Void> Insert(AbstractCard saleCard) {
            AbstractPlayer p = AbstractDungeon.player;
            if (p.hasRelic(SynthesisRecipe.ID) && p.getRelic(SynthesisRecipe.ID).counter > 0){
                saleCard.price = 0;
            }

            return SpireReturn.Continue();
        }



        private static class Locator
                extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(ShopScreen.class, "saleTag");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }

    @SpirePatch(clz = ShopScreen.class,
            method = "purchaseCard",
            paramtypez={
                    AbstractCard.class
            }
    )
    public static class purchaseCard {
        @SpireInsertPatch(locator = Locator.class,
        localvars = {"saleTag"})
        public static SpireReturn<Void> Insert(ShopScreen _instance, AbstractCard hoveredCard, OnSaleTag _saleTag) {
            AbstractPlayer p = AbstractDungeon.player;
            if (p.hasRelic(SynthesisRecipe.ID)
                    && p.getRelic(SynthesisRecipe.ID).counter > 0
                    && hoveredCard.equals(_saleTag.card)
                ){
                p.getRelic(SynthesisRecipe.ID).counter--;
                p.getRelic(SynthesisRecipe.ID).stopPulse();

                if (p.getRelic(SynthesisRecipe.ID).counter == 0){
                    p.getRelic(SynthesisRecipe.ID).usedUp();
                }
            }

            return SpireReturn.Continue();
        }



        private static class Locator
                extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(MetricData.class, "addShopPurchaseData");
                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }
}