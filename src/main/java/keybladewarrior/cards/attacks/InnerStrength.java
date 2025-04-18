package keybladewarrior.cards.attacks;

import basemod.interfaces.PostPowerApplySubscriber;
import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }


    @Override
    public void applyPowers() {
        DrivePoints Drive = (DrivePoints) AbstractDungeon.player.getPower(DrivePoints.ID);

        if (Drive != null){
            this.ExtraDriveDamage = (int) Math.floor(Drive.amount/this.magicNumber);
        }else {
            this.ExtraDriveDamage = 0;
        }
        this.baseDamage += this.ExtraDriveDamage;
        super.applyPowers();
        this.baseDamage -= this.ExtraDriveDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        DrivePoints Drive = (DrivePoints) AbstractDungeon.player.getPower(DrivePoints.ID);

        if (Drive != null && Drive.amount > 0){
            this.ExtraDriveDamage = (int) Math.floor(Drive.amount/this.magicNumber);
        }else {
            this.ExtraDriveDamage = 0;
        }
        this.baseDamage += this.ExtraDriveDamage;
        super.calculateCardDamage(mo);
        this.baseDamage -= this.ExtraDriveDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
}
