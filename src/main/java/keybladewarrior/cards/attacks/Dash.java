package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.wiseActions.DashAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.WisdomForm;
import keybladewarrior.util.CustomTags;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class Dash extends AbstractEasyCard {
    public static final String ID =makeID(Dash.class.getSimpleName());


    public Dash(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = this.damage = 8;
        this.baseBlock = this.block = 8;
        this.tags.add(CustomTags.WISE);


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
        addToBot(new DashAction(p,this.block));
    }


}
