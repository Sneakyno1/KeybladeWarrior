package keybladewarrior.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.FauxMasteryPower;

import static keybladewarrior.ModFile.makeID;

public class FauxMastery extends AbstractEasyCard {
    public static final String ID =makeID(FauxMastery.class.getSimpleName());


    public FauxMastery(){
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
    }

    @Override
    public void upp() {
        this.isInnate = true;
        upgradeBaseCost(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ApplyPowerAction(p, p, new FauxMasteryPower(p), 1));
    }
}
