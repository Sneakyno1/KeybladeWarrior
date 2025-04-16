package keybladewarrior.actions.comboActions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.powers.DrivePoints;

import java.util.function.Consumer;

public class MagnetBurstAction extends AbstractComboAction{
    AbstractPlayer p;
    int DrivePointsToApply;
    int damage;
    DamageInfo.DamageType damageTypeForTurn;


    public MagnetBurstAction(AbstractPlayer p, int magicNumber, int damage, DamageInfo.DamageType damageTypeForTurn) {
        this.p = p;
        this.DrivePointsToApply = magicNumber;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;

    }

    public void update() {
        Consumer<Integer> consumer = (Integer x) -> {if (x>0) addToBot(new ApplyPowerAction(p,p,new DrivePoints(AbstractDungeon.player,DrivePointsToApply),DrivePointsToApply)); };
        if (this.PreviousCardWasAComboCard()){
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
                addToBot(new DamageCallbackAction(mo,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,consumer));
            }
        }
        else{
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
                addToBot(new DamageAction(mo,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }

        this.isDone = true;
    }
}
