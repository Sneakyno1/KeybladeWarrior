package keybladewarrior.actions.comboActions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class KickDiveAction extends AbstractComboAction{

    public KickDiveAction() {
    }

    public void update() {

        if (PreviousCardWasAComboCard()){
            addToTop(new GainEnergyAction(1));
            if (Settings.FAST_MODE) {
                addToTop(new VFXAction(new MiracleEffect(Color.BLACK, Color.PURPLE, "ATTACK_MAGIC_SLOW_1"), 0.0F));
            } else {
                addToTop(new VFXAction(new MiracleEffect(Color.BLACK, Color.PURPLE, "ATTACK_MAGIC_SLOW_1"), 0.3F));
            }
        }
        this.isDone = true;
    }
}
