package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.miscellaneousActions.AffinityForValorAction;
import keybladewarrior.cards.AbstractEasyCard;

import static keybladewarrior.ModFile.makeID;

public class AffinityForValor extends AbstractEasyCard {
    public static final String ID =makeID(AffinityForValor.class.getSimpleName());


    public AffinityForValor(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseMagicNumber=this.magicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new AffinityForValorAction(p,this.magicNumber));
    }
}
