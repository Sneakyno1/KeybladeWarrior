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
import keybladewarrior.cards.AbstractEasyCard;
import keybladewarrior.powers.DrivePoints;
import keybladewarrior.util.CustomTags;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static keybladewarrior.ModFile.makeID;

public class MagnetBurst extends AbstractEasyCard {
    public static final String ID =makeID(MagnetBurst.class.getSimpleName());
    public boolean PreviousCardWasComboCard = false;


    public MagnetBurst(){
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, KeybladeWarrior.Enums.CARD_COLOR);
        this.baseDamage = 16;
        this.baseMagicNumber = 2;
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
        //int numHit = 0;

        Consumer<Integer> consumer = (Integer x) -> {if (x>0) addToBot(new ApplyPowerAction(p,p,new DrivePoints(p,2),2)); };
        if (PreviousCardWasComboCard){
            //allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){

                addToBot(new DamageCallbackAction(mo,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,consumer));
                //numHit += (mo.lastDamageTaken > 0) ? 1 : 0;
            }

            //addToBot(new ApplyPowerAction(p,p,new DrivePoints(p,this.magicNumber*numHit),this.magicNumber*numHit));
        }
        else{
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){

                dmg(mo,AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
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