package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.driveForms.WisdomForm;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class FlashStep extends AbstractEasyCard {
    public static final String ID =makeID(FlashStep.class.getSimpleName());


    public FlashStep(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 7;
        this.baseMagicNumber = this.magicNumber = 2;


    }
    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        if (Objects.equals(p.stance.ID, ValorForm.STANCE_ID)){
            addToBot(new DrawCardAction(p,this.magicNumber));
        }
    }


}
