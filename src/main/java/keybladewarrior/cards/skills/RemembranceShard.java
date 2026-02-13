package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class RemembranceShard extends AbstractSynthesisCard {
    public static final String ID =makeID(RemembranceShard.class.getSimpleName());

    public RemembranceShard(){
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        color = CardColor.COLORLESS;
        this.selfRetain = true;
        this.ignoreForDescription = true;
        this.resetAttributes();
        this.initializeDescription();
    }

    @Override
    public void upp() {}

    @Override
    public void upgrade() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}


    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {
        if (abstractSynthesisCard.SynthesisCards.findCardById(this.cardID) == null){
            abstractSynthesisCard.selfRetain = this.selfRetain;
            abstractSynthesisCard.SynthesisCards.addToTop(this.makeStatEquivalentCopy());
            abstractSynthesisCard.resetAttributes();

        }

        abstractSynthesisCard.SynthesisCards.findCardById(this.cardID).resetAttributes();
        abstractSynthesisCard.AddSynthesisEffect(abstractSynthesisCard);

    }

    @Override
    public boolean canUpgrade() {
        return false;
    }
}
