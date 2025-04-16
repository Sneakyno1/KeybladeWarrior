package keybladewarrior.actions.strongActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.util.CustomTags;

import java.util.Objects;

public class SynchBladeAction extends AbstractGameAction {
    AbstractPlayer p;
    AbstractMonster m;
    int ExtraAttacks = 0;
    DamageInfo info;

    public SynchBladeAction(AbstractPlayer p, AbstractMonster m, int magicNumber, DamageInfo info) {
        this.p = p;
        this.m = m;
        this.ExtraAttacks = magicNumber;
        this.info = info;

    }

    @Override
    public void update(){
        int NumOfAttacks = 2;

        if (p.stance instanceof AbstractDriveForm){
            if (((AbstractDriveForm) p.stance).hasTag(CustomTags.STRONG)) {
                NumOfAttacks += this.ExtraAttacks;
            }
        }

        for (int i = 0; i< NumOfAttacks; i++){
            addToBot(new DamageAction(m,info, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        this.isDone = true;
    }

}
