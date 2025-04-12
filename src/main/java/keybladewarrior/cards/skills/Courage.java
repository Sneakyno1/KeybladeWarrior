package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.ValorForm;

import static keybladewarrior.ModFile.makeID;

public class Courage extends AbstractEasyCard {
    public static final String ID =makeID(Courage.class.getSimpleName());


    public Courage(){
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ChangeStanceAction(ValorForm.STANCE_ID));
    }
}
