package keybladewarrior.actions.comboActions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.powers.DrivePoints;

import java.util.function.Consumer;

public class BerserkChargeAction extends AbstractComboAction{
    AbstractPlayer p;
    int DrivePointsToApply;


    public BerserkChargeAction(AbstractPlayer p, int magicNumber) {
        this.p = p;
        this.DrivePointsToApply = magicNumber;

    }

    public void update() {
        if (this.PreviousCardWasAComboCard()){
            addToBot(new ApplyPowerAction(p,p,new DrivePoints(AbstractDungeon.player,DrivePointsToApply),DrivePointsToApply));
        }
        this.isDone = true;
    }
}
