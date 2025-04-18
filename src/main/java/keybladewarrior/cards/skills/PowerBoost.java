package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.miscellaneousActions.PowerBoostAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class PowerBoost extends AbstractEasyCard {
    public static final String ID =makeID(PowerBoost.class.getSimpleName());


    public PowerBoost(){
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.exhaust = true;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PowerBoostAction(p,this.magicNumber));
    }

}