package keybladewarrior.driveForms;

import basemod.interfaces.OnCardUseSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;

import static keybladewarrior.ModFile.makeID;

@SpireInitializer
public class MasterForm extends AbstractDriveForm{
    public static final String STANCE_ID = makeID(MasterForm.class.getSimpleName());
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static final String NAME = stanceString.NAME;
    public static final String[] DESCRIPTIONS = stanceString.DESCRIPTION;
    private static long sfxId = -1L;
    public static final ArrayList<AbstractCard.CardTags> DriveTags = new ArrayList<AbstractCard.CardTags>() {{
        add(CustomTags.WISE);
        add(CustomTags.STRONG);
    }};

    public static final Color COLOR_MIN = CardHelper.getColor(92, 92, 92);
    public static final Color COLOR_MAX = CardHelper.getColor(128, 128, 128);

    private static Color cachedColor = null;

    private static final String ENTER_SOUND = "STANCE_ENTER_CALM";
    private static final String LOOP_SOUND = "STANCE_LOOP_DIVINITY";
    private static float TIMER = 0.1F;

    WisdomForm WisdomComponent = new WisdomForm();
    ValorForm ValorComponent = new ValorForm();

    public MasterForm() {
        this.ID = STANCE_ID;
        this.name = NAME;

        this.BaseCostToEnterForm = 8;
        this.CurrentFormCost = this.BaseCostToEnterForm;
        this.BaseFormCostPerTurn = 4;
        this.CurrentFormCostPerTurn = this.BaseFormCostPerTurn;
        this.FormCostModifier = 0;
        this.FormCostMultiplier = 1;


        this.updateDescription();
    }

    public MasterForm(boolean IgnoreCostToEnterForm) {
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
        super.onEnterStance();

        if (sfxId != -1L)
            stopIdleSfx();
        CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_DIVINITY");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Calm"));

    }

    @Override
    public void onExitStance() {
        stopIdleSfx();
    }

    @Override
    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("STANCE_LOOP_CALM", sfxId);
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
    public void onUseCard(AbstractCard card, UseCardAction action){
        WisdomComponent.onUseCard(card, action);
         ValorComponent.onUseCard(card, action);
    }

}


