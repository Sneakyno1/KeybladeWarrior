package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static keybladewarrior.ModFile.makeID;

public class HadesCursePower extends AbstractEasyPower {
    public static final String ID = makeID(HadesCursePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public HadesCursePower(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, PowerType.DEBUFF,true,owner,-1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, HadesCursePower.ID));
    }


    @Override
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0] ;

    }
}
