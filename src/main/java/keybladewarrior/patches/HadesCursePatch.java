package keybladewarrior.patches;

// Code adapted from github: https://github.com/torrentails/StS-Marked-Mod/tree/master


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import keybladewarrior.powers.HadesCursePower;
import keybladewarrior.relics.uncommon.SynthesisRecipe;

import java.lang.reflect.Field;

//This patch is for adding the Synthesis recipe logic to the Shop Screen

@SuppressWarnings("unused")
public class HadesCursePatch {

    @SpirePatch(clz = AbstractCard.class,
            method = "hasEnoughEnergy")
    public static class hasEnoughEnergy {
        @SpireInsertPatch
        public static SpireReturn<Boolean> Prefix(AbstractCard inst) {

            AbstractPlayer p = AbstractDungeon.player;

            if (p.hasPower(HadesCursePower.ID)
                &&
                    (inst.type == AbstractCard.CardType.SKILL || inst.type == AbstractCard.CardType.POWER)
            ){
                inst.cantUseMessage = "The power of the underworld is sapping my strength!";
                return SpireReturn.Return(false);
            }

            return SpireReturn.Continue();
        }

    }


}