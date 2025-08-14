package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.comboActions.KickDiveAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;

public class KickDive extends AbstractEasyCard {
    public static final String ID =makeID(KickDive.class.getSimpleName());


    public KickDive(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 8;
        tags.add(CustomTags.COMBO);
    }


    @Override
    public void upp() {
        upgradeDamage(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new KickDiveAction());
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