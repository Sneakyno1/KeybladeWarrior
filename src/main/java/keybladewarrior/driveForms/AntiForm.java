package keybladewarrior.driveForms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
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
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import keybladewarrior.driveForms.driveVFX.AntiFormParticleEffect;
import keybladewarrior.driveForms.driveVFX.MasterFormParticleEffect;
import keybladewarrior.powers.HadesCursePower;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;

import static keybladewarrior.ModFile.makeID;

@SpireInitializer
public class AntiForm extends AbstractDriveForm{
    public static final String STANCE_ID = makeID(AntiForm.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static final String NAME = stanceString.NAME;
    public static final String[] DESCRIPTIONS = stanceString.DESCRIPTION;
    private static long sfxId = -1L;
    public static final ArrayList<AbstractCard.CardTags> DriveTags = new ArrayList<AbstractCard.CardTags>() {};

    public static final Color COLOR_MIN = new Color(-0);//CardHelper.getColor(42, 2, 55);
    public static final Color COLOR_MAX = CardHelper.getColor(84, 4, 110);

    private static Color cachedColor = null;

    private static final String ENTER_SOUND = "MONSTER_CHAMP_CHARGE";
    private static final String LOOP_SOUND = "STANCE_LOOP_WRATH";
    private static float TIMER = 0.1F;

    ValorForm ValorComponent = new ValorForm();

    private int RealPlayerHealth = 1;
    public int AntiFormPLayerHealth = 5;

    public AntiForm() {
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

    public AntiForm(boolean IgnoreCostToEnterForm) {
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

        //remember players health and set it to 5
//        this.RealPlayerHealth = p.currentHealth;
//        if (p.currentHealth <= 5){
//            p.heal(5 - p.currentHealth);
//        }else {
//            p.heal(-(p.currentHealth - 5));
//        }
//        p.healthBarUpdatedEvent();


        //apply intangible, strength, and "entangle" but for skills
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new IntangiblePlayerPower(p,1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,6), 6));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new HadesCursePower(p)));


//        for(AbstractCard c : AbstractDungeon.player.hand.group) {
//            if (c.type != AbstractCard.CardType.ATTACK) {
//                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand, true));
//            }
//        }

        if (sfxId != -1L)
            stopIdleSfx();
        CardCrawlGame.sound.play(ENTER_SOUND);
        sfxId = CardCrawlGame.sound.playAndLoop(LOOP_SOUND);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(getColor(), true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.ID));

    }

    @Override
    public void onExitStance() {
        AbstractPlayer p = AbstractDungeon.player;
//        int overHeal = Math.max(0, p.currentHealth - 5);
//
//        if (p.currentHealth > 0){
//            p.heal(this.RealPlayerHealth - p.currentHealth + overHeal);
//        }


        stopIdleSfx();
        super.onExitStance();
    }


    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new IntangiblePlayerPower(AbstractDungeon.player,1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new HadesCursePower(p)));
    }

    @Override
    public void onEndOfTurn() {

//        for (AbstractCard c : AbstractDungeon.player.hand.group) {
//            if (c.type != AbstractCard.CardType.ATTACK) {
//                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand, true));
//            }
//        }

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        if (card.type == AbstractCard.CardType.ATTACK) {
           AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
       }
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
                AbstractDungeon.effectsQueue.add(new AntiFormParticleEffect());
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



}


