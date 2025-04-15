package keybladewarrior.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.function.Consumer;

import static keybladewarrior.ModFile.makeID;

public class ComboPlus extends AbstractEasyCard {
    public static final String ID =makeID(ComboPlus.class.getSimpleName());
    public boolean PreviousCardWasComboCard = false;


    public ComboPlus(){
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 6;
        tags.add(CustomTags.COMBO);
        this.shuffleBackIntoDrawPile = false;
    }


    @Override
    public void upp() {
        upgradeDamage(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.shuffleBackIntoDrawPile = false;
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (PreviousCardWasComboCard) {
            this.shuffleBackIntoDrawPile = true;
            //addToBot(new MoveCardsAction(p.drawPile,p.discardPile,c -> c.uuid == this.uuid));
        }

    }

    @Override
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 1)).hasTag(CustomTags.COMBO)){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            this.PreviousCardWasComboCard = true;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            this.PreviousCardWasComboCard = false;
        }
    }

}