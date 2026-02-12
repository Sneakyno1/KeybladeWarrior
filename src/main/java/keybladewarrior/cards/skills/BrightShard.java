package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class BrightShard extends AbstractSynthesisCard {
    public static final String ID =makeID(BrightShard.class.getSimpleName());

    public BrightShard(){
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
        addToBot(new ApplyPowerAction(p,p,new DrivePoints(p, magicNumber),magicNumber));
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

//        if (abstractSynthesisCard.rawDescription.contains("Plated Armor")){
//
//            int index = abstractSynthesisCard.rawDescription.indexOf("Plated Armor");
//            char num = abstractSynthesisCard.rawDescription.charAt(index-2);
//            abstractSynthesisCard.rawDescription = abstractSynthesisCard.rawDescription.replaceFirst("NL Apply \\d{1,6} Plated Armor",("NL Apply "+ (char)(((int) num) + baseMagicNumber) +" Plated Armor"));
//
//        }
//        else {
//            abstractSynthesisCard.rawDescription = abstractSynthesisCard.rawDescription.concat(" NL Apply 2 Plated Armor.");
//        }
//
//        abstractSynthesisCard.initializeDescription();
    }

    @Override
    public void initializeDescription() {

        if (cardStrings != null){
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1]
                                + (this.magicNumber)  + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        super.initializeDescription();
    }
}
