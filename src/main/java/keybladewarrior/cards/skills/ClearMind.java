package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.miscellaneousActions.ScryCallbackAction;
import keybladewarrior.cards.AbstractEasyCard;

import java.util.ArrayList;
import java.util.function.Consumer;

import static keybladewarrior.ModFile.makeID;

public class ClearMind extends AbstractEasyCard {
    public static final String ID =makeID(ClearMind.class.getSimpleName());


    public ClearMind(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 7;
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseSecondMagic = this.secondMagic = 1;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
        super.upgrade();
    }

    @Override
    public void applyPowers() {
            secondMagic = baseSecondMagic;

            int tmp = baseBlock;
            baseBlock = secondMagic;

            super.applyPowers();

            secondMagic = block;
            baseBlock = tmp;

            super.applyPowers();

            isSecondMagicModified = (secondMagic != baseSecondMagic);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();

        Consumer<ArrayList<AbstractCard>> consumer = (ArrayList<AbstractCard> x) -> {
            x.forEach(c ->addToTop(new GainBlockAction(p,secondMagic)));
        };

        addToBot(new ScryCallbackAction(this.magicNumber, consumer));
    }
}
