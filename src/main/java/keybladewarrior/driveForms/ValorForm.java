package keybladewarrior.driveForms;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import keybladewarrior.cards.Courage;
import keybladewarrior.cards.ExitDrive;
import keybladewarrior.powers.ValorPower;

import static keybladewarrior.ModFile.makeID;

public class ValorForm extends AbstractDriveForm{
    public static final String STANCE_ID = makeID(ValorForm.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static final String NAME = stanceString.NAME;
    public static final String[] DESCRIPTIONS = stanceString.DESCRIPTION;
    private static long sfxId;

    public ValorForm() {
        this.ID = STANCE_ID;
        this.name = NAME;
        updateDescription();
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onEnterStance() {
        super.onEnterStance();

//        if (sfxId != -1L)
//            stopIdleSfx();
//        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
//        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
//        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
//        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Wrath"));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new ValorPower(AbstractDungeon.player),1));
    }

    @Override
    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player, ValorPower.ID));
        stopIdleSfx();
    }

    @Override
    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId);
            sfxId = -1L;
        }
    }
}
