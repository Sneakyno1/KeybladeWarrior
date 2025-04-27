package keybladewarrior.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.MagicLockOnPower;
import keybladewarrior.powers.WellRoundedPower;

import static keybladewarrior.ModFile.makeID;

public class MagicLockOn extends AbstractEasyCard {
    public static final String ID =makeID(MagicLockOn.class.getSimpleName());


    public MagicLockOn(){
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ApplyPowerAction(p, p, new MagicLockOnPower(p,this.magicNumber), this.magicNumber));
    }
}
