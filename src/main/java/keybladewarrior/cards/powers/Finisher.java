package keybladewarrior.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.FinisherPower;

import static keybladewarrior.ModFile.makeID;

public class Finisher extends AbstractEasyCard {
    public static final String ID =makeID(Finisher.class.getSimpleName());


    public Finisher(){
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ApplyPowerAction(p, p, new FinisherPower(p)));
    }
}
