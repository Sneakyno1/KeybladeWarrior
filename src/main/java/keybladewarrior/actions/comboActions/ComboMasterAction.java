package keybladewarrior.actions.comboActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ComboMasterAction extends AbstractComboAction{
    AbstractPlayer p;
    AbstractMonster m;
    int DebuffStackAmount = 0;


    public ComboMasterAction(AbstractPlayer p, AbstractMonster m, int magicNumber) {
        this.p = p;
        this.m = m;
        this.DebuffStackAmount = magicNumber;

    }

    public void update() {
        if (this.PreviousCardWasAComboCard()){
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.DebuffStackAmount, false), this.DebuffStackAmount, true, AbstractGameAction.AttackEffect.NONE));
                addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.DebuffStackAmount, false), this.DebuffStackAmount, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        else {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.DebuffStackAmount, false), this.DebuffStackAmount, true, AbstractGameAction.AttackEffect.NONE));
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.DebuffStackAmount, false), this.DebuffStackAmount, true, AbstractGameAction.AttackEffect.NONE));
        }

        this.isDone = true;
    }
}
