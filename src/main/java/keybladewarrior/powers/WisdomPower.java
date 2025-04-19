package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static keybladewarrior.ModFile.makeID;

public class WisdomPower extends AbstractEasyPower{
    public static final String ID =makeID(WisdomPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public WisdomPower(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,true,owner,-1);
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (Settings.FAST_MODE) {
            addToBot(new GainBlockAction(owner, owner, 2, true));
        } else {
            addToBot(new GainBlockAction(owner, owner, 2));
        }
        flash();
    }

}

