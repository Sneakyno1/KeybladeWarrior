package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.comboActions.DefenderAction;
import keybladewarrior.actions.comboActions.DodgeRollAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class DodgeRoll extends AbstractEasyCard {
    public static final String ID =makeID(DodgeRoll.class.getSimpleName());


    public DodgeRoll(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 5;
        this.baseMagicNumber = this.magicNumber = 8;
        tags.add(CustomTags.COMBO);
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(3);
        super.upgrade();
    }

    @Override
    public void applyPowers() {
        magicNumber = baseMagicNumber;

        int tmp = baseBlock;
        baseBlock = magicNumber;

        super.applyPowers();

        magicNumber = block;
        baseBlock = tmp;

        super.applyPowers();

        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        blck();
        addToBot(new DodgeRollAction(p, this.magicNumber));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 1)).hasTag(CustomTags.COMBO)){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
