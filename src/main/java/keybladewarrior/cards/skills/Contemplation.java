package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class Contemplation extends AbstractEasyCard {
    public static final String ID =makeID(Contemplation.class.getSimpleName());


    public Contemplation(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseSecondMagic = this.secondMagic = 3;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new DrawCardAction(p,this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new DrivePoints(p,this.secondMagic), this.secondMagic));
    }
}
