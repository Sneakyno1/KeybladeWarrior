package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.strongActions.SynchBladeAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class SynchBlade extends AbstractEasyCard {
    public static final String ID =makeID(SynchBlade.class.getSimpleName());


    public SynchBlade(){
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 3;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CustomTags.STRONG);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(1);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SynchBladeAction(p,m,this.magicNumber,new DamageInfo(p, damage, damageTypeForTurn)));
    }

    @Override
    public void triggerOnGlowCheck() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.stance instanceof AbstractDriveForm){
            if (((AbstractDriveForm) p.stance).hasTag(CustomTags.STRONG)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

}