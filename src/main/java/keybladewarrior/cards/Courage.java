package keybladewarrior.cards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.powers.AbstractEasyPower;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class Courage extends AbstractEasyCard{
    public static final String ID =makeID(Courage.class.getSimpleName());


    public Courage(){
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        DrivePoints Drive = (DrivePoints) p.getPower(DrivePoints.ID);

        int CurrentDrivePoints = (Drive == null) ? 0 : Drive.amount;
        if (CurrentDrivePoints >= 3){
            Drive.CurrentFormCost = 3;

        addToBot(new ChangeStanceAction(ValorForm.STANCE_ID));
        }
    }
}
