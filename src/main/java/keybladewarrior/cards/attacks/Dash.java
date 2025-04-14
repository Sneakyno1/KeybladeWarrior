package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.WisdomForm;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class Dash extends AbstractEasyCard {
    public static final String ID =makeID(Dash.class.getSimpleName());


    public Dash(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 8;
        this.baseBlock = 8;


    }
    @Override
    public void upp() {
        upgradeDamage(4);
        upgradeBlock(4);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);

        if (Objects.equals(p.stance.ID, WisdomForm.STANCE_ID)){
            blck();
        }
    }


}
