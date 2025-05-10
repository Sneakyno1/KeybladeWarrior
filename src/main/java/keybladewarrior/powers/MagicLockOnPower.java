package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static keybladewarrior.ModFile.makeID;

public class MagicLockOnPower extends AbstractEasyPower{
    public static final String ID =makeID(MagicLockOnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public MagicLockOnPower(AbstractCreature owner, int amount){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,true,owner,amount);
    }

    @Override
    public void atStartOfTurn() {
        if (AbstractDungeon.player.drawPile.size() <= 0){
            addToTop(new EmptyDeckShuffleAction());
        }

        flash();
        addToBot(new ScryAction(this.amount));

    }

    @Override
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
