package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.miscellaneousActions.AutoValorAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class AutoValor extends AbstractEasyCard {
    public static final String ID =makeID(AutoValor.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;


    public AutoValor(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseBlock = 6;
    }

    @Override
    public void upp() {
        upgradeBlock(4);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new AutoValorAction(p,m,TEXT));
    }
}
