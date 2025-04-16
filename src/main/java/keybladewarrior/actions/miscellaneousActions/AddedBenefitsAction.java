package keybladewarrior.actions.miscellaneousActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.driveForms.WisdomForm;
import keybladewarrior.util.CustomTags;

import java.util.Objects;

public class AddedBenefitsAction extends AbstractGameAction {
    AbstractPlayer p;
    AbstractMonster m;
    int AmountToApply;

    public AddedBenefitsAction(AbstractPlayer p, AbstractMonster m, int AmountToApply){
        this.p = p;
        this.m = m;
        this.AmountToApply = AmountToApply;

    }

    @Override
    public void update(){
        if (p.stance instanceof AbstractDriveForm) {
            if (((AbstractDriveForm) p.stance).hasTag(CustomTags.STRONG)) {
                addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.AmountToApply, false), this.AmountToApply));
            }

            if (((AbstractDriveForm) p.stance).hasTag(CustomTags.WISE)) {
                addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, this.AmountToApply, false), this.AmountToApply));
            }
        }


        this.isDone = true;
    }

}
