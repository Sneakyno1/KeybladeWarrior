package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static keybladewarrior.ModFile.makeID;

public class DriveGeneratorPower extends AbstractEasyPower{
    public static final String ID =makeID(DriveGeneratorPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public DriveGeneratorPower(AbstractCreature owner, int amount){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,false,owner,amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(owner, owner, new DrivePoints(owner,this.amount), this.amount));
    }

    @Override
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
