package keybladewarrior.cards.skills;

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
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ChangeStanceAction(WisdomForm.STANCE_ID));
    }
}
