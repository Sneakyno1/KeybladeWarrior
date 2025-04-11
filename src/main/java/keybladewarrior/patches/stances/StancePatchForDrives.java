package keybladewarrior.patches.stances;

// Code adapted from github: https://github.com/torrentails/StS-Marked-Mod/tree/master


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;
import keybladewarrior.driveForms.ValorForm;

@SuppressWarnings("unused")
public class StancePatchForDrives {

    @SpirePatch(clz = AbstractStance.class,
            method = "getStanceFromName")
    public static class getStanceFromName {
        public static SpireReturn<AbstractStance> Prefix(String stanceID) {
            if (stanceID.equals(ValorForm.STANCE_ID)) {
                return SpireReturn.Return(new ValorForm());
            }

            return SpireReturn.Continue();
        }
    }
}