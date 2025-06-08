package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class UpperSlash extends AbstractEasyCard {
    public static final String ID =makeID(UpperSlash.class.getSimpleName());


    public UpperSlash(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = this.damage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }



    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        addToBot(new ApplyPowerAction(p, p, new DrivePoints(p,this.magicNumber), this.magicNumber));
    }

}