package keybladewarrior.cards;

import basemod.abstracts.CustomSavable;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.attacks.BlazingShard;
import keybladewarrior.cards.skills.DenseShard;
import keybladewarrior.util.CustomTags;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import static com.megacrit.cardcrawl.helpers.CardLibrary.getCard;
import static keybladewarrior.ModFile.makeID;

public abstract class AbstractSynthesisCard extends AbstractEasyCard implements CustomSavable<ArrayList<CardSave>> {
    public static final String ID =makeID(AbstractSynthesisCard.class.getSimpleName());
    //public static final int MaxNumberOfEffects = 7;
    public CardGroup SynthesisCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    //public int NumberOfEffects = 1;

    public AbstractSynthesisCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, cost, type, rarity, target, KeybladeWarrior.Enums.CARD_COLOR);
        this.tags.add((CustomTags.SYNTHESIS_MATERIAL));
        //BaseMod.addSaveField(this.cardID,  ArrayList.class);
    }

    public abstract void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard);

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard result = super.makeStatEquivalentCopy();
        if (result instanceof AbstractSynthesisCard) {
            AbstractSynthesisCard c = (AbstractSynthesisCard) result;
            c.SynthesisCards.group.addAll(this.SynthesisCards.group);
        }
        result.tags.addAll(this.tags);
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


    @Override
    public ArrayList<CardSave> onSave() {
       // return SynthesisCards.getCardDeck();

        if (SynthesisCards.group.isEmpty()){
            return SynthesisCards.getCardDeck();
        }

        ArrayList<CardSave> SavedCards = new ArrayList<CardSave>();

        for (AbstractCard c: SynthesisCards.group){
            SavedCards.add(new CardSave(c.cardID, c.timesUpgraded, c.baseMagicNumber));
        }

        if (this.baseDamage > 0 ){
            SavedCards.add(new CardSave(BlazingShard.ID, 0, this.baseDamage));
        }

        if (this.baseBlock > 0 ){
            SavedCards.add(new CardSave(DenseShard.ID, 0, this.baseBlock));
        }

        return SavedCards;
    }

    @Override
    public void onLoad(ArrayList<CardSave> CardsSaved) {
        if (CardsSaved == null || CardsSaved.isEmpty()){
            return;
        }

        this.target = CardTarget.SELF;

        //TODO: Add checks for the cards that will give Exhaust and Ethereal
        for (CardSave cS: CardsSaved){
            AbstractCard source = getCard(cS.id);
            AbstractCard retVal = null;
            if (source == null) {
                retVal = getCard("Madness").makeCopy();
            } else if (Objects.equals(source.cardID, BlazingShard.ID)) {
                this.type = CardType.ATTACK;
                if (this.target == CardTarget.NONE || this.target == CardTarget.SELF){
                    this.target = CardTarget.ENEMY;
                }
                this.baseDamage = cS.misc;
                continue;
            } else if (Objects.equals(source.cardID, DenseShard.ID)) {
                if (this.target == CardTarget.NONE || this.target == CardTarget.SELF){
                    this.target = CardTarget.SELF;
                }
                this.baseBlock = cS.misc;
                continue;
            } else {
                retVal = getCard(cS.id).makeCopy();
            }

            for (int i = 0; i < cS.upgrades; i++)
                retVal.upgrade();

            retVal.misc = misc;
            if (cS.misc != 0) {
                retVal.baseMagicNumber = cS.misc;
            }

            retVal.resetAttributes();

            for (CardTags tag: retVal.tags){
                if (!this.tags.contains(tag)){
                    this.tags.add(tag);
                }
            }

            if (retVal.exhaust){
                this.exhaust = true;
            }
            if (retVal.isEthereal){
                this.isEthereal = true;
            }

            SynthesisCards.addToTop(retVal);
        }

        this.resetAttributes();

    }

    @Override
    public Type savedType(){

        return new TypeToken<ArrayList<CardSave>>(){}.getType();
       // return ArrayList<CardSave>.class;
    }
}
