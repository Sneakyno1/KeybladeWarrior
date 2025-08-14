package keybladewarrior.driveForms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.EstablishmentPowerAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import keybladewarrior.driveForms.driveVFX.FinalFormParticleEffect;
import keybladewarrior.driveForms.driveVFX.MasterFormParticleEffect;
import keybladewarrior.powers.AbstractKeybladeWarriorPower;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;

import static keybladewarrior.ModFile.makeID;

public class FinalForm extends AbstractDriveForm{
    public static final String STANCE_ID = makeID(FinalForm.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static final String NAME = stanceString.NAME;
    public static final String[] DESCRIPTIONS = stanceString.DESCRIPTION;
    private static long sfxId = -1L;
    public static final ArrayList<AbstractCard.CardTags> DriveTags = new ArrayList<AbstractCard.CardTags>() {{
        add(CustomTags.WISE);
        add(CustomTags.STRONG);
    }};

    public final int ThornsAmount = 4;

    public static final Color COLOR_MIN = CardHelper.getColor(210, 210, 210);
    public static final Color COLOR_MAX = CardHelper.getColor(210, 210, 210);

    private static Color cachedColor = null;

    private static final String ENTER_SOUND = "POWER_MANTRA";
    private static final String LOOP_SOUND = "STANCE_LOOP_CALM";
    private static float TIMER = 0.1F;

    MasterForm MasterFormComponent = new MasterForm();

    public FinalForm() {
        this.ID = STANCE_ID;
        this.name = NAME;

        this.BaseCostToEnterForm = 12;
        this.CurrentFormCost = this.BaseCostToEnterForm;
        this.BaseFormCostPerTurn = 10;
        this.CurrentFormCostPerTurn = this.BaseFormCostPerTurn;
        this.FormCostModifier = 0;
        this.FormCostMultiplier = 1;


        this.updateDescription();
    }

    public FinalForm(boolean IgnoreCostToEnterForm) {
        this();
        this. IgnoreCostToEnterForm = IgnoreCostToEnterForm;
        this.updateDescription();
    }

    @Override
    public boolean hasTag(AbstractCard.CardTags tagToCheck) {
        return DriveTags.contains(tagToCheck);
    }

    @Override
    public void updateDescription(){
        this.description = DESCRIPTIONS[0];
    }


    @Override
    public void onEnterStance() {

        AbstractPlayer p = AbstractDungeon.player;

        if (p.hasPower(DrivePoints.ID)){
            ((DrivePoints) (p.getPower(DrivePoints.ID))).IgnoreNoDriveGain = true;
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ThornsPower(p,ThornsAmount), ThornsAmount));

        if (sfxId != -1L)
            stopIdleSfx();
        CardCrawlGame.sound.play(ENTER_SOUND);
        sfxId = CardCrawlGame.sound.playAndLoop(LOOP_SOUND);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(getColor(), true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(p.hb.cX, p.hb.cY, this.ID));

        super.onEnterStance();

    }

    @Override
    public void onExitStance() {
        stopIdleSfx();

        AbstractPlayer p = AbstractDungeon.player;

        if (p.hasPower(DrivePoints.ID)){
            ((DrivePoints) (p.getPower(DrivePoints.ID))).IgnoreNoDriveGain = false;
        }

        if (p.hasPower(ThornsPower.POWER_ID)){
            p.getPower(ThornsPower.POWER_ID).reducePower(ThornsAmount);
            p.getPower(ThornsPower.POWER_ID).updateDescription();

            if (p.getPower(ThornsPower.POWER_ID).amount <= 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,ThornsPower.POWER_ID));

            }
        }

        super.onExitStance();
    }

    @Override
    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop(LOOP_SOUND, sfxId);
            sfxId = -1L;
        }
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = TIMER;
                AbstractDungeon.effectsQueue.add(new FinalFormParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect(STANCE_ID));
        }

    }

    public static Color getColor() { return getColor(1.0f); }

    public static Color getColor(float a) {
        if (cachedColor == null) {
            cachedColor = COLOR_MIN.cpy().lerp(COLOR_MAX, MathUtils.random(1.0f));

            //CustomPlayer.logger.info("Caching new color: " + cachedColor.toString());
        }

        Color color = cachedColor.cpy();

        if (a > 1.0f) {
            a = a / 255.0f;
        }
        color.a = a;

        return color;
    }

    public static Color getAuraColor(float a) {
        Color color = CardHelper.getColor(50, 50, 50);

        if (a > 1.0f) {
            a = a / 255.0f;
        }
        color.a = a;

        return color;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        MasterFormComponent.onUseCard(card, action);
    }

    @Override
    public void onEndOfTurn() {
        if (!AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") &&
                !AbstractDungeon.player.hasPower("Equilibrium")){
            AbstractDungeon.actionManager.addToBottom(new RetainCardsAction(AbstractDungeon.player, AbstractDungeon.player.hand.size()));
        }

        //AbstractDungeon.actionManager.addToBottom(new EstablishmentPowerAction(1));

        super.onEndOfTurn();
    }


//    @Override
//    public void atStartOfTurn() {
//        for (AbstractCard c : AbstractDungeon.player.hand.group) {
//            c.setCostForTurn(c.cost - 1);
//        }
//    }
}


