package keybladewarrior.patches.stancevfx;
// Code adapted from github: https://github.com/torrentails/StS-Marked-Mod/tree/master
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import keybladewarrior.driveForms.*;
import keybladewarrior.driveForms.driveVFX.*;

import java.lang.reflect.Field;


@SuppressWarnings("unused")
public class StanceChangeParticleGeneratorPatch {
    @SpirePatch(clz = StanceChangeParticleGenerator.class,
                method = "update")
    public static class update {

        public static SpireReturn<?> Prefix(StanceChangeParticleGenerator inst) {

            try {
                Field stanceIdField = inst.getClass().getDeclaredField("stanceId");
                stanceIdField.setAccessible(true);

                Field xField = inst.getClass().getDeclaredField("x");
                xField.setAccessible(true);

                if (stanceIdField.get(inst).toString().equals(ValorForm.STANCE_ID)) {
                    for (int i = 0; i < 20; ++i) {
                        AbstractDungeon.effectsQueue.add(new ValorFormChangeParticle((Float) xField.get(inst)));
                    }
                }

                if (stanceIdField.get(inst).toString().equals(WisdomForm.STANCE_ID)) {
                    for (int i = 0; i < 20; ++i) {
                        AbstractDungeon.effectsQueue.add(new WisdomFormChangeParticle((Float) xField.get(inst)));
                    }
                }

                if (stanceIdField.get(inst).toString().equals(MasterForm.STANCE_ID)) {
                    for (int i = 0; i < 20; ++i) {
                        AbstractDungeon.effectsQueue.add(new MasterFormChangeParticle((Float) xField.get(inst)));
                    }
                }

                if (stanceIdField.get(inst).toString().equals(FinalForm.STANCE_ID)) {
                    for (int i = 0; i < 20; ++i) {
                        AbstractDungeon.effectsQueue.add(new FinalFormChangeParticle((Float) xField.get(inst)));
                    }
                }

                if (stanceIdField.get(inst).toString().equals(AntiForm.STANCE_ID)) {
                    for (int i = 0; i < 20; ++i) {
                        AbstractDungeon.effectsQueue.add(new AntiFormChangeParticle((Float) xField.get(inst)));
                    }
                }

                    inst.isDone = true;

                    return SpireReturn.Return(null);


            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            return SpireReturn.Continue();
        }
    }
}