package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class DenseShard extends AbstractSynthesisCard {
    public static final String ID =makeID(DenseShard.class.getSimpleName());

    public DenseShard(){
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        color = CardColor.COLORLESS;
        this.baseBlock = 2;
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }


    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {

        if (abstractSynthesisCard.SynthesisCards.findCardById(this.cardID) != null){
            abstractSynthesisCard.SynthesisCards.findCardById(this.cardID).baseBlock += this.baseBlock;

        }else {

            abstractSynthesisCard.SynthesisCards.addToTop(this.makeStatEquivalentCopy());

            if (abstractSynthesisCard.target == CardTarget.NONE){
                abstractSynthesisCard.target = CardTarget.SELF;
            }
        }
        abstractSynthesisCard.AddSynthesisEffect(abstractSynthesisCard);
    }
}
