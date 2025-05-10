package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.WisdomForm;

import static keybladewarrior.ModFile.makeID;

public class Wisdom extends AbstractEasyCard {
    public static final String ID =makeID(Wisdom.class.getSimpleName());


    public Wisdom(){
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ChangeStanceAction(WisdomForm.STANCE_ID));
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new DiscardAction(p,p,1,false));
    }
}
