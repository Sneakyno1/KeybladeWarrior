package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import keybladewarrior.cards.skills.Wisdom;

import static keybladewarrior.ModFile.makeID;

public class MasterPower extends AbstractEasyPower{
    public static final String ID =makeID(MasterPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public MasterPower(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,true,owner,-1);
    }

    WisdomPower WisdomComponent = new WisdomPower(AbstractDungeon.player);
    ValorPower ValorComponent = new ValorPower(AbstractDungeon.player);


    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        WisdomComponent.onUseCard(card, action);
        ValorComponent.onUseCard(card, action);
        flash();
    }

}

