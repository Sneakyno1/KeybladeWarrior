package keybladewarrior.actions.miscellaneousActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.cards.skills.MidSynthesis;
import keybladewarrior.powers.DrivePoints;

public class CreateMidSynthesisAction extends AbstractGameAction {
    AbstractSynthesisCard originalCardInDeck;
    AbstractSynthesisCard cardBeingAddedToDeck;

    public CreateMidSynthesisAction(AbstractSynthesisCard originalCardInDeck, AbstractSynthesisCard cardBeingAddedToDeck ){
        this.originalCardInDeck = originalCardInDeck;
        this.cardBeingAddedToDeck = cardBeingAddedToDeck;

    }

    @Override
    public void update(){
        AbstractSynthesisCard MidSynthesis = new MidSynthesis();
        originalCardInDeck.AddSynthesisEffect(MidSynthesis);
        cardBeingAddedToDeck.AddSynthesisEffect(MidSynthesis);

        AbstractDungeon.player.masterDeck.removeCard(originalCardInDeck);
        AbstractDungeon.player.masterDeck.removeCard(cardBeingAddedToDeck);

        AbstractDungeon.topLevelEffects.add(new FastCardObtainEffect(MidSynthesis, MidSynthesis.current_x, MidSynthesis.current_y));

        this.isDone = true;
    }

}
