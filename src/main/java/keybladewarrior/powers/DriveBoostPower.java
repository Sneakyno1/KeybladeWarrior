package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class DriveBoostPower extends AbstractKeybladeWarriorPower{
    public static final String ID =makeID(DriveBoostPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public DriveBoostPower(AbstractCreature owner, int amount){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,false,owner,amount);
        canGoNegative = false;
    }


    @Override
    public void updateDescription(){
        if (amount == 1){
            this.description = powerStrings.DESCRIPTIONS[0] + amount
                    + powerStrings.DESCRIPTIONS[2] + powerStrings.DESCRIPTIONS[3];
        }else{
            this.description = powerStrings.DESCRIPTIONS[0] + amount
                             + powerStrings.DESCRIPTIONS[1] + powerStrings.DESCRIPTIONS[3];
        }
    }

    @Override
    public float onGainDrive(float amount){
        amount += this.amount;
        return amount;
    }
}
