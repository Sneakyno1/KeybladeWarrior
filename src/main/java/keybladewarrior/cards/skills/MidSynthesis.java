package keybladewarrior.cards.skills;

import keybladewarrior.cards.AbstractSynthesisCard;

import static keybladewarrior.ModFile.makeID;

public class MidSynthesis extends AbstractSynthesisCard {
    public static final String ID =makeID(MidSynthesis.class.getSimpleName());

    public MidSynthesis(){
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        color = CardColor.COLORLESS;
        this.baseDamage = 0;
        this.baseBlock = 0;
    }

    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {

    }

    @Override
    public void upp() {
    }

    @Override
    public void update() {
        initializeDescription();
        super.update();
    }
}
