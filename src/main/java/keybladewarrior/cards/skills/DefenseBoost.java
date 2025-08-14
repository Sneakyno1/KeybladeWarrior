package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.miscellaneousActions.DefenseBoostAction;
import keybladewarrior.cards.AbstractEasyCard;

import static keybladewarrior.ModFile.makeID;

public class DefenseBoost extends AbstractEasyCard {
    public static final String ID =makeID(DefenseBoost.class.getSimpleName());


    public DefenseBoost(){
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 12;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(-2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DefenseBoostAction(p,this.magicNumber));
    }

}