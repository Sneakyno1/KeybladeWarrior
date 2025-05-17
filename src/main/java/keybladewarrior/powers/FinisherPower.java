package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class FinisherPower extends AbstractEasyPower{
    public static final String ID =makeID(FinisherPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public int comboLimit = 3;

    public FinisherPower(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,false,owner,0);
        this.canGoNegative = false;
        this.comboLimit = 3;
        updateDescription();
    }
    
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(CustomTags.COMBO)){
            this.amount++;
            if (this.amount % comboLimit == 0){
                this.amount = 0;
                addToBot(new ApplyPowerAction(this.owner, this.owner,new FinisherDuplicationPower(this.owner,1), 1));
            }
        }else {
            this.amount = 0;;
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        return;
    }

    @Override
    public void updateDescription(){
        int cardsLeft = Math.abs(this.amount-comboLimit);
        this.description = powerStrings.DESCRIPTIONS[0] + cardsLeft;
        if (cardsLeft<=1){
            this.description += powerStrings.DESCRIPTIONS[1];
        }else{
            this.description += powerStrings.DESCRIPTIONS[2];
        }
    }

}

