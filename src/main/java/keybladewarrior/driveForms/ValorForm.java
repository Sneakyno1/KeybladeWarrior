package keybladewarrior.driveForms;

import basemod.BaseMod;
import basemod.interfaces.OnCardUseSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import keybladewarrior.driveForms.driveVFX.ValorFormParticleEffect;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;
import java.util.Collections;

import static keybladewarrior.ModFile.makeID;

public class ValorForm  extends AbstractDriveForm  {
    public static final String STANCE_ID = makeID(ValorForm.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static final String NAME = stanceString.NAME;
    public static final String[] DESCRIPTIONS = stanceString.DESCRIPTION;
    private static long sfxId = -1L;
    public static final ArrayList<AbstractCard.CardTags> DriveTags = new ArrayList<AbstractCard.CardTags>(Collections.singletonList(CustomTags.STRONG));

    public static final Color COLOR_MIN = CardHelper.getColor(110, 7, 12);
    public static final Color COLOR_MAX = CardHelper.getColor(220, 15, 25);

    private static Color cachedColor = null;

    private static final String ENTER_SOUND = "ORB_LIGHTNING_EVOKE";
    private static final String LOOP_SOUND = "STANCE_LOOP_WRATH";
    private static float TIMER = 0.1F;

    private static int StrengthPerCardPlayed = 1;
    public ValorForm() {
        this.ID = STANCE_ID;
        this.name = NAME;

        this.BaseCostToEnterForm = 0;
        this.CurrentFormCost = this.BaseCostToEnterForm;

        this.BaseFormCostPerTurn = 0;
        this.CurrentFormCostPerTurn = this.BaseFormCostPerTurn;

        this.FormCostModifier = 0;
        this.FormCostMultiplier = 1;


        this.updateDescription();
    }

    public ValorForm(boolean IgnoreCostToEnterForm) {
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
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = TIMER;
                AbstractDungeon.effectsQueue.add(new ValorFormParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect(STANCE_ID));
        }

    }

    @Override
    public void onEnterStance() {
        super.onEnterStance();
        if (sfxId != -1L){
            stopIdleSfx();
        }

        CardCrawlGame.sound.play(ENTER_SOUND);
        sfxId = CardCrawlGame.sound.playAndLoop(LOOP_SOUND);

        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(getColor(), true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.ID));

    }

    @Override
    public void onExitStance() {
        stopIdleSfx();
    }

    @Override
    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop(LOOP_SOUND, sfxId);
            sfxId = -1L;
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



    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.type == AbstractCard.CardType.ATTACK) {
//            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
//        }
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, StrengthPerCardPlayed), StrengthPerCardPlayed));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, StrengthPerCardPlayed), StrengthPerCardPlayed));
    }


}


