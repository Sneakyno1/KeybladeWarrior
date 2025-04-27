package keybladewarrior.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.Objects;
import java.util.function.Consumer;

import static keybladewarrior.ModFile.makeID;

public class CounterGuard extends AbstractEasyCard {
    public static final String ID =makeID(CounterGuard.class.getSimpleName());


    public CounterGuard(){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 12;
        tags.add(CustomTags.STRONG);
    }



    @Override
    public void upp() {
        upgradeDamage(6);
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Consumer<Integer> consumer = (Integer x) -> {
            if (x>0) {
                if (p.stance instanceof AbstractDriveForm){
                    if (((AbstractDriveForm) p.stance).hasTag(CustomTags.STRONG)) {
                        addToTop(new GainBlockAction(p, m.lastDamageTaken));
                    }else{
                        addToTop(new GainBlockAction(p, (m.lastDamageTaken/2)));
                    }
                }else{
                        addToTop(new GainBlockAction(p, (m.lastDamageTaken/2)));
                }
            }
        };
        addToBot(new DamageCallbackAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY,consumer));
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