package keybladewarrior.relics.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.powers.AbstractKeybladeWarriorPower;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.relics.AbstractEasyRelic;

import static keybladewarrior.ModFile.makeID;

public class PiggyBank extends AbstractEasyRelic {
    public static final String ID = makeID(PiggyBank.class.getSimpleName());
    public static final int MaxBankedPerCombat = 6;
    public PiggyBank() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL, KeybladeWarrior.Enums.CARD_COLOR);
        this.counter = 0;
    }

    @Override
    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;

        if (p.hasPower(DrivePoints.ID)){
            AbstractPower Points = p.getPower(DrivePoints.ID);
            this.counter = Math.min(Points.amount, MaxBankedPerCombat);
        }
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.counter>0){
            addToBot(new ApplyPowerAction(p,p,new DrivePoints(p,this.counter),this.counter));
            flash();
        }

        this.counter = 0;
    }

}
