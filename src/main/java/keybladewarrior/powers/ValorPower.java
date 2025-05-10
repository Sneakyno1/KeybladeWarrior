package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static keybladewarrior.ModFile.makeID;

public class ValorPower extends AbstractEasyPower{
    public static final String ID =makeID(ValorPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public int counter = 0;

    public ValorPower(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME,AbstractPower.PowerType.BUFF,true,owner,-1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            addToBot(new DrawCardAction(1));
        }
    }
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0];
    }

}
