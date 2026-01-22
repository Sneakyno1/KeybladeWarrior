package keybladewarrior.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;

import static keybladewarrior.ModFile.makeID;

public abstract class AbstractSynthesisCard extends AbstractEasyCard {
    public static final String ID =makeID(AbstractSynthesisCard.class.getSimpleName());
    public static final int MaxNumberOfEffects = 7;
    public ArrayList<AbstractGameAction> SynthesisActions;

    public int NumberOfEffects = 1;

    public AbstractSynthesisCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, cost, type, rarity, target, KeybladeWarrior.Enums.CARD_COLOR);
        this.tags.add((CustomTags.SYNTHESIS_MATERIAL));
    }

    public abstract void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard);

    public void CombineSynthesisCards(AbstractSynthesisCard NewCard){
        NewCard.AddSynthesisEffect(this);

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(NewCard.uuid)) {
                AbstractDungeon.player.masterDeck.removeCard(c);
            }
        }


        NumberOfEffects++;
        if (NumberOfEffects >= MaxNumberOfEffects) {
            this.tags.remove(CustomTags.SYNTHESIS_MATERIAL);
        }
    }


}
