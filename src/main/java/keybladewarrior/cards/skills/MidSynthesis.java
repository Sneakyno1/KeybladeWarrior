package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.cards.AbstractSynthesisCard;
import keybladewarrior.util.CustomTags;

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
        this.initializeDescription();
        this.update();
    }

    @Override
    public void upp() {
    }

    @Override
    public void initializeDescription() {
        if (!this.description.isEmpty()){
            super.initializeDescription();
//            if (!this.isEthereal){
//                this.description.remove(0);
//            }

//            if (!this.exhaust){
//                this.description.remove(this.description. size() - 1);
//                super.initializeDescription();

                if (SynthesisCards != null){
                    for (AbstractCard c: SynthesisCards.group){
                        c.initializeDescription();
                        this.description.add(c.description.get(1));
                    }
                }
//            }else{
//
//
//                if (SynthesisCards != null){
//                    for (AbstractCard c: SynthesisCards.group){
//                        c.initializeDescription();
//                        this.description.add(this.description.size() - 2, c.description.get(1));
//                    }
//                }
//            }
        }else {
            super.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (baseDamage > 0){dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);}
        if (baseBlock > 0){blck();}
        super.use(p, m);
    }

    @Override
    public void applyPowers() {
        if (baseDamage > 0){
            super.applyPowers();
        }
    }

    @Override
    protected void applyPowersToBlock() {
        if (baseBlock > 0){
            super.applyPowersToBlock();
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (this.tags.contains(CustomTags.COMBO)) {
            if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
                    .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                            .size() - 1)).hasTag(CustomTags.COMBO)){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            } else {
                this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            }
        }
    }
}
