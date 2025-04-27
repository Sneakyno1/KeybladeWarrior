package keybladewarrior.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.comboActions.MagnetBurstAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static keybladewarrior.ModFile.makeID;

public class MagnetBurst extends AbstractEasyCard {
    public static final String ID =makeID(MagnetBurst.class.getSimpleName());


    public MagnetBurst(){
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 16;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        tags.add(CustomTags.COMBO);
    }



    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(4);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MagnetBurstAction(p,this.magicNumber,this.damage,this.damageTypeForTurn));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 1)).hasTag(CustomTags.COMBO)){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

}