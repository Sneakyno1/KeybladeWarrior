package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class PricklyFantasy extends AbstractSynthesisCard {
    public static final String ID =makeID(PricklyFantasy.class.getSimpleName());

    public PricklyFantasy(){
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        color = CardColor.COLORLESS;
        this.baseMagicNumber = 1;
        this.resetAttributes();
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new ThornsPower(p, magicNumber),magicNumber));
    }


    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {
        if (abstractSynthesisCard.SynthesisCards.findCardById(this.cardID) != null){
            abstractSynthesisCard.SynthesisCards.findCardById(this.cardID).baseMagicNumber += this.baseMagicNumber;

        }else {

            abstractSynthesisCard.SynthesisCards.addToTop(this.makeStatEquivalentCopy());

            if (abstractSynthesisCard.target == CardTarget.NONE){
                abstractSynthesisCard.target = CardTarget.SELF;
            }

            if (abstractSynthesisCard.type == CardType.STATUS){
                abstractSynthesisCard.type = CardType.SKILL;

            }
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
