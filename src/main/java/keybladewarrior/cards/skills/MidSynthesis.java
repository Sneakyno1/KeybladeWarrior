package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import keybladewarrior.cards.AbstractSynthesisCard;

import static keybladewarrior.ModFile.makeID;

public class MidSynthesis extends AbstractSynthesisCard {
    public static final String ID =makeID(MidSynthesis.class.getSimpleName());

    public MidSynthesis(){
        super(ID, 0, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE);
        color = CardColor.COLORLESS;
        this.baseDamage = 0;
        this.baseBlock = 0;
    }

    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {
        for (AbstractCard c: SynthesisCards.group){
            this.description.add(c.description.get(1)); //= this.cardStrings.DESCRIPTION.concat(c.description.);
        }
        this.initializeDescription();
        this.update();
    }

    @Override
    public void upp() {
    }

}
