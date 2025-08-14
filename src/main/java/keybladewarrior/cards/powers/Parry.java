package keybladewarrior.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.ParryPower;
import keybladewarrior.powers.WellRoundedPower;

import static keybladewarrior.ModFile.makeID;

public class Parry extends AbstractEasyCard {
    public static final String ID =makeID(Parry.class.getSimpleName());


    public Parry(){
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        isEthereal = true;
    }

    @Override
    public void upp() {
        isEthereal = false;
        upgradeBaseCost(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ApplyPowerAction(p, p, new ParryPower(p), -1));
    }
}
