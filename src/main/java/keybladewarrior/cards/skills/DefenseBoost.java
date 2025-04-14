package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class DefenseBoost extends AbstractEasyCard {
    public static final String ID =makeID(DefenseBoost.class.getSimpleName());


    public DefenseBoost(){
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 9;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(-1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);
        blck();

        if (Drive != null && Drive.amount >= this.magicNumber){
            Drive.reducePower(this.magicNumber);
            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
        }
    }

}