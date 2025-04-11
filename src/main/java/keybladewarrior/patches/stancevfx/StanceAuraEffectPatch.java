//package keybladewarrior.patches.stancevfx;
//// Code adapted from github: https://github.com/torrentails/StS-Marked-Mod/tree/master
//import com.evacipated.cardcrawl.modthespire.lib.*;
//import com.megacrit.cardcrawl.helpers.Hitbox;
//import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
//import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
//import javassist.CtBehavior;
//import keybladewarrior.driveForms.ValorForm;
//
//import java.lang.reflect.Field;
//
//import static basemod.BaseMod.logger;
//
//
//@SuppressWarnings("unused")
//public class StanceAuraEffectPatch {
//
//    @SpirePatch(clz = StanceAuraEffect.class, method = SpirePatch.CONSTRUCTOR)
//    public static class StanceAuraEffect_Patch {
//
//        @SpireInsertPatch(locator = Locator.class)
//        public static void constructor(StanceAuraEffect effect, String stanceId) {
//            if (stanceId.equals(ValorForm.STANCE_ID)) {
//                try {
//                    Field colorField = AbstractGameEffect.class.getDeclaredField("color");
//                    colorField.setAccessible(true);
//                    colorField.set(effect, ValorForm.getColor(0.0f));
//
//                } catch (NoSuchFieldException | IllegalAccessException e) {
//                    logger.error("Can't access color field on AbstractGameEffect");
//                }
//            }
//        }
//
//
//        private static class Locator
//                extends SpireInsertLocator {
//
//            @Override
//            public int[] Locate(CtBehavior ctBehavior) throws Exception {
//                Matcher finalMatcher = new Matcher.FieldAccessMatcher(Hitbox.class, "cX");
//
//                return LineFinder.findInOrder(ctBehavior, finalMatcher);
//            }
//        }
//    }
//}