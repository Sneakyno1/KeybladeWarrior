package keybladewarrior.patches.actions;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import keybladewarrior.driveForms.AbstractDriveForm;

//This patch is to add stances to the useCardAction Game action

@SuppressWarnings("unused")
public class UseCardActionPatchAddDriveForms {

    @SpirePatch(clz = UseCardAction.class, method = SpirePatch.CONSTRUCTOR,
                paramtypez={
                        AbstractCard.class,
                        AbstractCreature.class
                }
    )
        public static class constructor{
            public static void Postfix(UseCardAction _instance, AbstractCard card, AbstractCreature target) {
                AbstractPlayer p = AbstractDungeon.player;
                if (!card.dontTriggerOnUseCard) {
                    if (p.stance instanceof AbstractDriveForm){
                        ((AbstractDriveForm) p.stance).onUseCard(card, _instance);
                    }
                }
            }
        }
}