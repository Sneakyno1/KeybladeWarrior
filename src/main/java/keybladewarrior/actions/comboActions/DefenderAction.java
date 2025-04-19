package keybladewarrior.actions.comboActions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import keybladewarrior.powers.DrivePoints;

import java.util.function.Consumer;

public class DefenderAction extends AbstractComboAction{
    AbstractPlayer p;
    int BlurToApply;

    public DefenderAction(AbstractPlayer p, int magicNumber) {
        this.p = p;
        this.BlurToApply = magicNumber;

    }

    public void update() {
        if (this.PreviousCardWasAComboCard()){
            addToTop(new ApplyPowerAction(p,p,new BlurPower(p,BlurToApply), BlurToApply));
        }

        this.isDone = true;
    }
}
