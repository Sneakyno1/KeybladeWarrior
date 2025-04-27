package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.interfaces.OnBeingScriedInterface;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class MPHaste extends AbstractEasyCard implements OnBeingScriedInterface {
    public static final String ID =makeID(MPHaste.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;


    public MPHaste(){
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        tags.add(CustomTags.SPELL);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster monster) {
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new DrawCardAction(p,this.magicNumber));
    }

    @Override
    public void onBeingScried() {
        AbstractPlayer p = AbstractDungeon.player;
        addToTop(new DrawCardAction(p,this.magicNumber));
        addToTop(new DrawCardAction(p,this.magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = TEXT[0];
        return false;
    }
}
