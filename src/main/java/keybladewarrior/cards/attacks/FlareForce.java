package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;

import static keybladewarrior.ModFile.makeID;

public class FlareForce extends AbstractEasyCard {
    public static final String ID =makeID(FlareForce.class.getSimpleName());


    public FlareForce(){
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 3;
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(3);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            dmgRandom(AbstractGameAction.AttackEffect.FIRE);
        }
    }
}
