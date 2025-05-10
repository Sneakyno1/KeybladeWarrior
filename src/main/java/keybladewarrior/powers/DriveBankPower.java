package keybladewarrior.powers;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class DriveBankPower extends AbstractEasyPower{
    public static final String ID =makeID(DriveBankPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static Color blueColor2 = Color.SKY.cpy();

    public int DriveBankedThisTurn = 0;

    //amount is how many drive points can be banked per turn
    //amount2 is how many drive points you've banked so far, resets to 0 when drive points are given to the player
    public DriveBankPower(AbstractCreature owner, int amount){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,true,owner,amount);
        this.isTwoAmount = true;
        this.canGoNegative = this.canGoNegative2 = false;
        this.amount2 = 0;
        updateDescription();

    }



    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {

        if (!Objects.equals(oldStance.ID, newStance.ID) && this.amount2 > 0){
            AbstractPlayer p = AbstractDungeon.player;
            DrivePoints points = (DrivePoints) p.getPower(DrivePoints.ID);

            if (points == null){
                points = new DrivePoints(p,this.amount2);

                points.GainFromBank = true;
                addToBot(new ApplyPowerAction(p, p, points, this.amount2));


            }else {
                points.GainFromBank = true;
                addToBot(new ApplyPowerAction(p, p, new DrivePoints(p,this.amount2), this.amount2));
            }

            this.amount2 = 0;
            this.DriveBankedThisTurn = 0;
            updateDescription();
        }


        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.DriveBankedThisTurn = 0;
        updateDescription();
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

                    AmountToBank = Math.min(power.amount, this.amount-DriveBankedThisTurn);
                    this.DriveBankedThisTurn += AmountToBank;
                    this.amount2 += AmountToBank;
                }
            }else {
                if (((DrivePoints) power).IgnoreNoDriveGain) {
                    //do nothing, don't need to bank since you can gain drive
                    return;
                }

                if (!Objects.equals(p.stance.ID, NeutralStance.STANCE_ID)) {

                    AmountToBank = Math.min(power.amount, this.amount - DriveBankedThisTurn);
                    this.DriveBankedThisTurn += AmountToBank;
                    this.amount2 += AmountToBank;

                    //If player doesn't have drive points, and has drive bank
                    //and is in any drive form, it means they entered for free prior to
                    //getting here, so we should reduce the power amount by the stack to prevent
                    //adding drive when it shouldn't be gained
                    power.reducePower(power.amount);
                }
            }
        }

        updateDescription();
    }

    @Override
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0] + amount2 + powerStrings.DESCRIPTIONS[1] +
                           powerStrings.DESCRIPTIONS[2] + amount  + powerStrings.DESCRIPTIONS[3];
    }

    @Override
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (amount == DriveBankedThisTurn) {
            blueColor2.a = c.a;
            c = blueColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        }
    }

}

