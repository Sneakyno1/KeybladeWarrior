package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class ComboTech extends AbstractSynthesisCard {
    public static final String ID =makeID(ComboTech.class.getSimpleName());

    public ComboTech(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE);
        color = CardColor.COLORLESS;
        this.tags.add(CustomTags.COMBO);
        this.resetAttributes();
        this.initializeDescription();
    }

    @Override
    public void upp() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}


    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {
        if (abstractSynthesisCard.SynthesisCards.findCardById(this.cardID) == null){
            abstractSynthesisCard.tags.add(CustomTags.COMBO);
            abstractSynthesisCard.SynthesisCards.addToTop(this.makeStatEquivalentCopy());
            abstractSynthesisCard.resetAttributes();

        }

        abstractSynthesisCard.SynthesisCards.findCardById(this.cardID).resetAttributes();
        abstractSynthesisCard.AddSynthesisEffect(abstractSynthesisCard);

    }

//    @Override
//    public void initializeDescription() {
//
//        if (cardStrings != null){
//            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1]
//                                + (this.magicNumber)  + cardStrings.EXTENDED_DESCRIPTION[2];
//        }
//        super.initializeDescription();
//    }
}
