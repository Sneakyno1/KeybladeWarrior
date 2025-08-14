package keybladewarrior.cards.skills;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import keybladewarrior.cards.AbstractEasyCard;

import static keybladewarrior.ModFile.makeID;

public class Deflect extends AbstractEasyCard {
    public static final String ID =makeID(Deflect.class.getSimpleName());

    public Deflect(){
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        color = CardColor.COLORLESS;
        this.baseBlock = 2;
        this.isEthereal= true;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
}
