package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.EasyXCostAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;
import static keybladewarrior.util.Wiz.applyToSelfTop;
import static keybladewarrior.util.Wiz.atb;

public class SynchBlade extends AbstractEasyCard {
    public static final String ID =makeID(SynchBlade.class.getSimpleName());


    public SynchBlade(){
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 3;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(3);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int NumOfAttacks = 2;

        if (Objects.equals(p.stance.ID, ValorForm.STANCE_ID)){
            NumOfAttacks += this.magicNumber;
        }

        for (int i = 0; i< NumOfAttacks; i++){
            dmg(m,AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
    }


}