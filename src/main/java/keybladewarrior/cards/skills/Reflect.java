package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.ReflectPower;

import static keybladewarrior.ModFile.makeID;

public class Reflect extends AbstractEasyCard {
    public static final String ID =makeID(Reflect.class.getSimpleName());
    private float damageReturnPercentage = .5f;


    public Reflect(){
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 25;
    }

    @Override
    public void upgrade() {
        if (this.timesUpgraded < 3){
            upgradeBlock(5);
            this.timesUpgraded++;


            if (this.timesUpgraded == 1){
                this.name = cardStrings.EXTENDED_DESCRIPTION[0];
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
                this.damageReturnPercentage = .75f;
                this.upgraded = false;
            }

            if (this.timesUpgraded == 2){
                this.name = cardStrings.EXTENDED_DESCRIPTION[2];
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[3];
                this.damageReturnPercentage = 1f;
                this.selfRetain = true;
                this.upgraded = false;
            }

            if (this.timesUpgraded == 3){
                upgradeBaseCost(1);
                this.upgraded = true;
                this.selfRetain = true;
                this.name = this.name + "+";
            }


            initializeDescription();
            initializeTitle();
        }
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard result = super.makeStatEquivalentCopy();

        if (result.timesUpgraded == 1){
            result.name = cardStrings.EXTENDED_DESCRIPTION[0];
            result.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
            ((Reflect) result).damageReturnPercentage = .75f;
            result.upgraded = false;
        }

        if (result.timesUpgraded == 2){
            result.name = cardStrings.EXTENDED_DESCRIPTION[2];
            result.rawDescription = cardStrings.EXTENDED_DESCRIPTION[3];
            ((Reflect) result).damageReturnPercentage = 1f;
            result.selfRetain = true;
            result.upgraded = false;
        }

        if (result.timesUpgraded == 3){
            result.selfRetain = true;
            result.upgraded = true;
            result.name = cardStrings.EXTENDED_DESCRIPTION[2] + "+";
        }

        return result;

    }

    @Override
    public void upp() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(p,p,new ReflectPower(p, damageReturnPercentage)));
    }

}