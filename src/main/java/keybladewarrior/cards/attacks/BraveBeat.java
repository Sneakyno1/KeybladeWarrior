package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.comboActions.MagnetBurstAction;
import keybladewarrior.actions.strongActions.BraveBeatAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class BraveBeat extends AbstractEasyCard {
    public static final String ID =makeID(BraveBeat.class.getSimpleName());


    public BraveBeat(){
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 6;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        tags.add(CustomTags.STRONG);
    }



    @Override
    public void upp() {
        upgradeDamage(4);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BraveBeatAction(p,this.magicNumber,this.damage,this.damageTypeForTurn));
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