package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.actions.comboActions.ComboMasterAction;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.driveForms.WisdomForm;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class ComboMaster extends AbstractEasyCard {
    public static final String ID =makeID(ComboMaster.class.getSimpleName());
    public boolean PreviousCardWasComboCard = false;


    public ComboMaster(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        tags.add(CustomTags.COMBO);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ComboMasterAction(p,m,this.magicNumber));
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