package keybladewarrior.patches.stancevfx;
// Code adapted from github: https://github.com/torrentails/StS-Marked-Mod/tree/master
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import javassist.CtBehavior;
import keybladewarrior.cards.skills.Wisdom;
import keybladewarrior.driveForms.*;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

import static basemod.BaseMod.logger;


@SuppressWarnings("unused")
public class StanceAuraEffectPatch {

    @SpirePatch(clz = StanceAuraEffect.class, method = SpirePatch.CONSTRUCTOR)
    public static class StanceAuraEffect_Patch {
        public final static List<String> DriveIDs = Arrays.asList(ValorForm.STANCE_ID, WisdomForm.STANCE_ID,
                MasterForm.STANCE_ID, FinalForm.STANCE_ID, AntiForm.STANCE_ID);

        @SpireInsertPatch(locator = Locator.class)
        public static void constructor(StanceAuraEffect effect, String stanceId) {
            if (DriveIDs.contains(stanceId)) {
                try {
                    Field colorField = AbstractGameEffect.class.getDeclaredField("color");
                    colorField.setAccessible(true);

                    if (Objects.equals(stanceId, ValorForm.STANCE_ID)){
                        colorField.set(effect, ValorForm.getColor(0.0f));
                    }
                    else if (Objects.equals(stanceId, WisdomForm.STANCE_ID)){
                        colorField.set(effect, WisdomForm.getColor(0.0f));
                    }
                    else if (Objects.equals(stanceId, MasterForm.STANCE_ID)){
                        colorField.set(effect, MasterForm.getColor(0.0f));
                    }
                    else if (Objects.equals(stanceId, FinalForm.STANCE_ID)){
                        colorField.set(effect, FinalForm.getColor(0.0f));
                    }
                    else if (Objects.equals(stanceId, AntiForm.STANCE_ID)){
                        colorField.set(effect, AntiForm.getColor(0.0f));
                    }

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    logger.error("Can't access color field on AbstractGameEffect");
                }
            }
        }


        private static class Locator
                extends SpireInsertLocator {

            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(Hitbox.class, "cX");

                return LineFinder.findInOrder(ctBehavior, finalMatcher);
            }
        }
    }
}