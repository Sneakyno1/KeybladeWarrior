package keybladewarrior.rewards;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import keybladewarrior.cards.skills.MidSynthesis;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class SynthesisReward extends CustomReward {
    public ArrayList<AbstractCard> synthesisCards = new ArrayList<AbstractCard>();
    public static int normalNumberOfCardsInReward = 3;
    public int numCards = 0;

    public SynthesisReward() {
        super(ImageMaster.REWARD_CARD_NORMAL, "Choose a Synthesis Material", CustomTags.SYNTHESIS);
        this.numCards = normalNumberOfCardsInReward;


        for(Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            if (    (c.getValue()).color == AbstractCard.CardColor.COLORLESS
                    && (c.getValue()).hasTag(CustomTags.SYNTHESIS_MATERIAL)
                    && !(Objects.equals((c.getValue()).cardID, MidSynthesis.ID))
            ) {

                synthesisCards.add(c.getValue());
            }
        }

        this.cards = getSynthesisCardReward();
    }

    public SynthesisReward(int NumCards) {
        super(ImageMaster.REWARD_CARD_NORMAL, "Choose a Synthesis Material", CustomTags.SYNTHESIS);
        this.numCards = NumCards;

        for(Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            if (    (c.getValue()).color == AbstractCard.CardColor.COLORLESS
                    && (c.getValue()).hasTag(CustomTags.SYNTHESIS_MATERIAL)
                    && !(Objects.equals((c.getValue()).cardID, MidSynthesis.ID))
            ) {

                synthesisCards.add(c.getValue());
            }
        }
        
        this.cards = getSynthesisCardReward();
    }


    @Override
    public boolean claimReward() {
        if (AbstractDungeon.player.hasRelic("Question Card"))
            AbstractDungeon.player.getRelic("Question Card").flash();
        if (AbstractDungeon.player.hasRelic("Busted Crown"))
            AbstractDungeon.player.getRelic("Busted Crown").flash();
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[4]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }

    public  ArrayList<AbstractCard> getSynthesisCardReward() {
        ArrayList<AbstractCard> retVal = new ArrayList<AbstractCard>();
        AbstractPlayer player = AbstractDungeon.player;

        if (!Settings.isDebug) {
            int numberOfCards = this.numCards;

            for (AbstractRelic r : player.relics)
                numCards = r.changeNumberOfCardsInReward(numCards);

            if (ModHelper.isModEnabled("Binary"))
                numCards--;

            for (int i = 0; i < numberOfCards; i++) {
                AbstractCard card = null;
                boolean containsDupe = true;

                while (containsDupe) {
                    containsDupe = false;

                    card = getRandomSynthesisCard();

                    for (AbstractCard c : retVal) {
                        if (c.cardID.equals(card.cardID)) {
                            containsDupe = true;
                            break;
                        }
                    }
                }
                if (card != null)
                    retVal.add(card);
            }
        }else {
            retVal.addAll(synthesisCards);
        }


        ArrayList<AbstractCard> retVal2 = new ArrayList<>();
        for (AbstractCard c : retVal)
            retVal2.add(c.makeCopy());
        for (AbstractCard c : retVal2) {
            for (AbstractRelic r : player.relics){
                r.onPreviewObtainCard(c);
            }
        }
        return retVal2;
    }

    public AbstractCard getRandomSynthesisCard() {

         return synthesisCards.get(AbstractDungeon.cardRng.random(synthesisCards.size() - 1));
    }
}
