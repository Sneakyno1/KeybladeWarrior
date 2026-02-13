package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.cards.AbstractSynthesisCard;

import static keybladewarrior.ModFile.makeID;

public class ManifestIllusion extends AbstractSynthesisCard {
    public static final String ID =makeID(ManifestIllusion.class.getSimpleName());

    public ManifestIllusion(){
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        color = CardColor.COLORLESS;
        this.isInnate = true;
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
            abstractSynthesisCard.isInnate = this.isInnate;
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
