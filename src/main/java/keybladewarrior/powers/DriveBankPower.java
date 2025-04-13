package keybladewarrior.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class DriveBankPower extends AbstractEasyPower{
    public static final String ID =makeID(DriveBankPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public int BankedDrive = 0;
    public int DriveBankedThisTurn = 0;

    public DriveBankPower(AbstractCreature owner, int amount){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,true,owner,amount);
        updateDescription();

    }



    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        AbstractPlayer p = AbstractDungeon.player;
        if (Objects.equals(newStance.ID, NeutralStance.STANCE_ID)){
            addToBot(new ApplyPowerAction(p, p, new DrivePoints(p), this.BankedDrive));
            this.BankedDrive = 0;
            this.DriveBankedThisTurn = 0;
        }

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.DriveBankedThisTurn = 0;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        int AmountToBank = 0;

        //check if we're adding drive points
        if (Objects.equals(power.ID, DrivePoints.ID)){
            AbstractPlayer p = (AbstractPlayer) target;
            DrivePoints points = (DrivePoints) p.getPower(DrivePoints.ID);
            if (points != null){
                //do nothing if we're ignoring no drive gain anyway
                if (points.IgnoreNoDriveGain){
                    //do nothing, don't need to bank since you can gain drive
                    return;
                }

                //check if we need to bank the drive points
                if (!Objects.equals(p.stance.ID, NeutralStance.STANCE_ID)){

                    AmountToBank = Math.min(points.amount, this.amount-DriveBankedThisTurn);
                    this.DriveBankedThisTurn += AmountToBank;
                    this.BankedDrive += AmountToBank;
                }
            }
        }

        updateDescription();
    }

    @Override
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1] +
                           powerStrings.DESCRIPTIONS[2] + DriveBankedThisTurn + powerStrings.DESCRIPTIONS[3];
    }
}

