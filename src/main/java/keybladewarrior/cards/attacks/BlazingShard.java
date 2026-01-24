package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.cards.AbstractSynthesisCard;

import static keybladewarrior.ModFile.makeID;

public class BlazingShard extends AbstractSynthesisCard {
    public static final String ID =makeID(BlazingShard.class.getSimpleName());

    public BlazingShard(){
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        color = CardColor.COLORLESS;
        this.baseDamage = 2;
    }

    @Override
    public void upp() { upgradeDamage(2); }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {


        if (abstractSynthesisCard.SynthesisCards.findCardById(this.cardID) != null){
            abstractSynthesisCard.SynthesisCards.findCardById(this.cardID).baseDamage += this.baseDamage;

        }else {

            abstractSynthesisCard.SynthesisCards.addToTop(this.makeStatEquivalentCopy());
            abstractSynthesisCard.type = CardType.ATTACK;

            if (abstractSynthesisCard.target == CardTarget.NONE
                    || abstractSynthesisCard.target == CardTarget.SELF){

                abstractSynthesisCard.target = CardTarget.ENEMY;
            }
        }
        abstractSynthesisCard.AddSynthesisEffect(abstractSynthesisCard);
    }

}
