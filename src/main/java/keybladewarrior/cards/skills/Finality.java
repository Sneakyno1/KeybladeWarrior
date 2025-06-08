package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.FinalForm;
import keybladewarrior.driveForms.MasterForm;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class Finality extends AbstractEasyCard {
    public static final String ID =makeID(Finality.class.getSimpleName());

    public Finality(){
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseMagicNumber = this.magicNumber = 18;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.selfRetain = true;
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot(new ApplyPowerAction(p, p, new DrivePoints(p, this.magicNumber),this.magicNumber));
        addToBot(new ChangeStanceAction(FinalForm.STANCE_ID));
    }
}
