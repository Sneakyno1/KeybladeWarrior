package keybladewarrior.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class DrivePoints extends AbstractEasyPower{
    public static final String ID =makeID(DrivePoints.class.getSimpleName());
    public int TotalDriveGainedThisCombat = 0;
    public int DrivePointsToGainOnTurnStart = 0;
    public Boolean IgnoreNoDriveGain = false;



    public boolean ForcedFormExit = false;

    public DrivePoints(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, AbstractPower.PowerType.BUFF,true,owner,1);
        this.canGoNegative = false;
    }

    @Override
    public void stackPower(int AmountToStack){
        AbstractPlayer p = AbstractDungeon.player;
        if (!Objects.equals(p.stance.ID, NeutralStance.STANCE_ID) && !IgnoreNoDriveGain){
            return;
        }else{
        super.stackPower(AmountToStack);
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {


    }
}
