package keybladewarrior.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

import static keybladewarrior.ModFile.makeID;

public class DrivePoints extends AbstractEasyPower{
    public static final String ID =makeID(DrivePoints.class.getSimpleName());
    public int TotalDrivePointsGained = 0;
    public int TotalDriveGainedThisCombat = 0;
    public int DrivePointsToGainOnTurnStart = 0;

    public int CurrentFormCost = 0;
    public int FormCostModifier = 0;
    public float FormCostMultiplier = 1;

    public boolean ForcedFormExit = false;

    public DrivePoints(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, AbstractPower.PowerType.BUFF,true,owner,1);
        this.canGoNegative = false;
    }

    @Override
    public void stackPower(int AmountToStack){

        super.stackPower(AmountToStack);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount > 0){
            amount = Math.max((amount - CurrentFormCost), 0);
        }else {
            ForcedFormExit = true;
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
            addToBot(new ChangeStanceAction("Neutral"));
        }

    }
}
