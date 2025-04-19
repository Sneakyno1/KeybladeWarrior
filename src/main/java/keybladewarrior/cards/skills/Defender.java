package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.comboActions.DefenderAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class Defender extends AbstractEasyCard {
    public static final String ID =makeID(Defender.class.getSimpleName());


    public Defender(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        tags.add(CustomTags.COMBO);
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
        blck();
        addToBot(new DefenderAction(p, this.magicNumber));
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
