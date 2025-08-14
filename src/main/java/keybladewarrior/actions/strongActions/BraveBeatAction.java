package keybladewarrior.actions.strongActions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.actions.comboActions.AbstractComboAction;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.function.Consumer;

public class BraveBeatAction extends AbstractGameAction {
    AbstractPlayer p;
    int cardsToDraw;
    int damage;
    DamageInfo.DamageType damageTypeForTurn;


    public BraveBeatAction(AbstractPlayer p, int magicNumber, int damage, DamageInfo.DamageType damageTypeForTurn) {
        this.p = p;
        this.cardsToDraw = magicNumber;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;

    }

    public void update() {
        Consumer<Integer> consumer = (Integer x) -> {if (x>0) addToBot(new DrawCardAction(p, this.cardsToDraw)); };

        if (p.stance instanceof AbstractDriveForm && ((AbstractDriveForm) p.stance).hasTag(CustomTags.STRONG)){
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot(new DamageCallbackAction(mo, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL, consumer));
                }
        }
        else {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot(new DamageAction(mo, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
            }
        }
        this.isDone = true;
    }
}
