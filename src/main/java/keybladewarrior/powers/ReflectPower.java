package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static keybladewarrior.ModFile.makeID;

public class ReflectPower extends AbstractEasyPower{
    public static final String ID =makeID(ReflectPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    float damageReturnPercentage = .5f;

    public ReflectPower(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,false,owner,-1);
        updateDescription();
    }

    public ReflectPower(AbstractCreature owner, float percentageOfDamageToReflectBack){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,false,owner,-1);
        damageReturnPercentage = percentageOfDamageToReflectBack;
        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(ReflectPower.ID)){
            if (this.damageReturnPercentage < ((ReflectPower) power).damageReturnPercentage){
                this.damageReturnPercentage = ((ReflectPower) power).damageReturnPercentage;
                updateDescription();
            }
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        int modifiedDamageAmount = (int)((float)info.output * damageReturnPercentage);

        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            flash();
            addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, modifiedDamageAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction( AbstractDungeon.player, AbstractDungeon.player, ReflectPower.ID));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void updateDescription(){
        if (damageReturnPercentage <= .5f){
        this.description = powerStrings.DESCRIPTIONS[0];
        } else if (damageReturnPercentage <= .75f) {
            this.description = powerStrings.DESCRIPTIONS[1];
        }else {
            this.description = powerStrings.DESCRIPTIONS[2];
        }
    }
}

