package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.WisdomForm;
import keybladewarrior.powers.DrivePoints;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class Invigorate extends AbstractEasyCard {
    public static final String ID =makeID(Invigorate.class.getSimpleName());


    public Invigorate(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        blck();
        addToBot(new ApplyPowerAction(p, p, new DrivePoints(p,this.magicNumber), this.magicNumber));
    }
}
