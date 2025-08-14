package keybladewarrior.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import keybladewarrior.actions.miscellaneousActions.GainedDriveInDriveFormWithNoActiveDrivePointsAction;
import keybladewarrior.driveForms.AntiForm;
import keybladewarrior.driveForms.FinalForm;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class DrivePoints extends AbstractEasyPower{
    public static final String ID =makeID(DrivePoints.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public int TotalDriveGainedThisCombat = 0;
    public int DrivePointsToGainOnTurnStart = 0;
    public Boolean IgnoreNoDriveGain = false;
    public boolean GainFromBank = false;

    public DrivePoints(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, AbstractPower.PowerType.BUFF,true,owner,1);
        this.canGoNegative = false;
    }

    public DrivePoints(AbstractCreature owner, int amount){
        super(ID, getPowerStrings(ID).NAME, AbstractPower.PowerType.BUFF,true,owner,amount);

        for (AbstractPower pw : owner.powers) {
            if (pw instanceof AbstractKeybladeWarriorPower){
                this.amount = (int)((AbstractKeybladeWarriorPower)(pw)).onGainDrive(amount);
            }
        }

        if (((AbstractPlayer) owner).stance.ID.equals(FinalForm.STANCE_ID)){
            IgnoreNoDriveGain = true;
        }

//        if (!(owner.hasPower(ID))
//                && !Objects.equals(((AbstractPlayer) owner).stance.ID, NeutralStance.STANCE_ID)
//                && !IgnoreNoDriveGain
//                && !GainFromBank){
//            //Player shouldn't gain any drive points
//            addToBot(new GainedDriveInDriveFormWithNoActiveDrivePointsAction((AbstractPlayer) owner,this, amount));
//            return;
//        }



        updateDescription();
        this.canGoNegative = false;
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        updateDescription();
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        updateDescription();
    }

    @Override
    public void stackPower(int AmountToStack){
        AbstractPlayer p = AbstractDungeon.player;
        if (Objects.equals(p.stance.ID, AntiForm.STANCE_ID) && !IgnoreNoDriveGain && !GainFromBank){
            //Player shouldn't gain any drive points
            return;
        }else{

            for (AbstractPower pw : owner.powers) {
                if (pw instanceof AbstractKeybladeWarriorPower){
                    AmountToStack = (int)((AbstractKeybladeWarriorPower)(pw)).onGainDrive(AmountToStack);
                }
            }

            if (((AbstractPlayer) owner).stance.ID.equals(FinalForm.STANCE_ID)){
                AmountToStack /= 2;
            }

            super.stackPower(AmountToStack);
            GainFromBank = false;
        }
        updateDescription();
    }

    @Override
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0] + amount;
        if (amount == 1) {
            this.description += powerStrings.DESCRIPTIONS[2];
        }else {
            this.description += powerStrings.DESCRIPTIONS[1];
        }
    }
}
