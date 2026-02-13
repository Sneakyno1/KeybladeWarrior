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
        this.cost = (this.misc>0) ? Math.floorDiv(this.misc,3) : 0;
    }

    @Override
    public void AddSynthesisEffect(AbstractSynthesisCard abstractSynthesisCard) {
        this.misc++;
        this.cost = (this.misc>0) ? Math.floorDiv(this.misc,3) : 0;
        this.initializeDescription();
        this.update();
    }

    @Override
    public void upp() {
    }

    @Override
    public void initializeDescription() {
        if (!this.description.isEmpty()){
            //super.initializeDescription();

            if (this.isInnate){
                this.rawDescription = this.rawDescription.replace(this.rawDescription, "*Innate. NL "+this.rawDescription);
            }
            if (this.isEthereal){
                this.rawDescription = this.rawDescription.replace(this.rawDescription, "*Ethereal. NL "+this.rawDescription);
            }
            if (this.selfRetain){
                this.rawDescription = this.rawDescription.replace(this.rawDescription, "*Retain. NL "+this.rawDescription);
            }

            if (SynthesisCards != null){
                for (AbstractCard c: SynthesisCards.group){

                    if (((AbstractSynthesisCard) c).ignoreForDescription){
                        continue;
                    }

                    c.initializeDescription();

                    String descriptionInProgress = c.rawDescription.replace("keybladewarrior:Synthesis_Material.","");

                    if (descriptionInProgress.contains("!M!")) {
                        descriptionInProgress = descriptionInProgress.replace("!M!", ("" + (c.magicNumber)));
                    }

                    this.rawDescription = this.rawDescription.concat(descriptionInProgress);
                }
            }

            if (this.exhaust){
                this.rawDescription = this.rawDescription.concat(" NL *Exhaust.");
            }
        }

        super.initializeDescription();

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

    @Override
    public boolean canUpgrade() {
        return false;
    }
}
