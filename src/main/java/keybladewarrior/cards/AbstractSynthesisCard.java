package keybladewarrior.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import jdk.nashorn.internal.ir.IfNode;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.miscellaneousActions.CreateMidSynthesisAction;
import keybladewarrior.cards.skills.MidSynthesis;
import keybladewarrior.util.CustomTags;

import java.util.ArrayList;

import static keybladewarrior.ModFile.makeID;

public abstract class AbstractSynthesisCard extends AbstractEasyCard {
    public static final String ID =makeID(AbstractSynthesisCard.class.getSimpleName());
    public static final int MaxNumberOfEffects = 7;
    public CardGroup SynthesisCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public int NumberOfEffects = 1;

    public AbstractSynthesisCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, cost, type, rarity, target, KeybladeWarrior.Enums.CARD_COLOR);
        this.tags.add((CustomTags.SYNTHESIS_MATERIAL));
    }

    public abstract void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard);

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard result = super.makeStatEquivalentCopy();
        if (result instanceof AbstractSynthesisCard) {
            AbstractSynthesisCard c = (AbstractSynthesisCard) result;
            c.SynthesisCards.group.addAll(this.SynthesisCards.group);
        }
        return result;
    }

//    public void CombineSynthesisCards(AbstractSynthesisCard NewCard){
//        if (ID != MidSynthesis.ID && NumberOfEffects == 1){
//            ReplaceWithMidSynthesis(this, NewCard);
//        }
//        else{
//
//            NewCard.AddSynthesisEffect(this);
//            this.initializeDescription();
//            this.initializeTitle();
//            AbstractDungeon.player.masterDeck.removeCard(NewCard);
//
//            NumberOfEffects++;
//            if (NumberOfEffects >= MaxNumberOfEffects) {
//                this.tags.remove(CustomTags.SYNTHESIS_MATERIAL);
//            }
//        }
//    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (AbstractCard card: SynthesisCards.group){
            card.use(p, m);
        }

//        if (this.baseDamage>0){
//            dmg(m, AbstractGameAction.AttackEffect.FIRE);
//        }
//        if (this.baseBlock>0){
//            blck();
//        }
//        if (SynthesisCards.size() > 1){
//            for (AbstractCard card: SynthesisCards.group){
//                card.use(p, m);
//            }
//        }
    }


//    public void ReplaceWithMidSynthesis(AbstractSynthesisCard inMasterDeck, AbstractSynthesisCard addingToDeck){
//       addToTop(new CreateMidSynthesisAction(inMasterDeck, addingToDeck ));
//    }


}
