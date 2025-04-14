package keybladewarrior.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.EasyXCostAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import static keybladewarrior.ModFile.makeID;
import static keybladewarrior.util.Wiz.applyToSelfTop;
import static keybladewarrior.util.Wiz.atb;

public class BerserkCharge extends AbstractEasyCard {
    public static final String ID =makeID(BerserkCharge.class.getSimpleName());
    public boolean PreviousCardWasComboCard = false;


    public BerserkCharge(){
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        tags.add(CustomTags.COMBO);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(3);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new EasyXCostAction(this,
                (effect, params)->{
            for (int i = 0; i < effect;i++) {
                if (params[1] == 1) {
                    applyToSelfTop(new DrivePoints(p, params[0]));
                }
                dmgTop(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            }

            return true;
        },
                this.magicNumber, (this.PreviousCardWasComboCard) ? 1:0));
    }

    @Override
    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .size() - 1).hasTag(CustomTags.COMBO)){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            this.PreviousCardWasComboCard = true;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            this.PreviousCardWasComboCard = false;
        }
    }

}