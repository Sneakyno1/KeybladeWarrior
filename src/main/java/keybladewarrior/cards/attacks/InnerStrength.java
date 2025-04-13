package keybladewarrior.cards.attacks;

import basemod.interfaces.PostPowerApplySubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class InnerStrength extends AbstractEasyCard {
    public static final String ID =makeID(InnerStrength.class.getSimpleName());
    public int ExtraDriveDamage = 0;


    public InnerStrength(){
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 12;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;


    }
    @Override
    public void upp() {
        this.upgradeDamage(6);
        this.upgradeMagicNumber(-1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);

        if (Drive != null && Drive.amount > 0){
            this.ExtraDriveDamage = (int) Math.floor(Drive.amount/this.magicNumber);
        }else {
            this.ExtraDriveDamage = 0;
        }
        this.damage = this.baseDamage + this.ExtraDriveDamage;
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }
}
