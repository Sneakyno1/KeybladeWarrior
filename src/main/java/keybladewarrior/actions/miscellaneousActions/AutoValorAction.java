package keybladewarrior.actions.miscellaneousActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.util.CustomTags;

public class AutoValorAction extends AbstractGameAction {
    AbstractPlayer p;
    AbstractMonster m;
    String[] TEXT;

    public AutoValorAction(AbstractPlayer p, AbstractMonster m,String[] TEXT){
        this.p = p;
        this.m = m;
        this.TEXT = TEXT;

    }

    @Override
    public void update(){
        if (m != null && m.getIntentBaseDmg() >= 0) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
        }else {
            addToBot(new ChangeStanceAction(ValorForm.STANCE_ID));
        }

        this.isDone = true;
    }

}
