package keybladewarrior.powers;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.driveForms.ValorForm;
import keybladewarrior.driveForms.WisdomForm;

import java.util.Objects;

import static keybladewarrior.ModFile.makeID;

public class FauxMasteryPower extends AbstractEasyPower{
    public static final String ID =makeID(FauxMasteryPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public FauxMasteryPower(AbstractCreature owner){
        super(ID, getPowerStrings(ID).NAME, PowerType.BUFF,false,owner,-1);
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        AbstractPlayer p = AbstractDungeon.player;
        if (Objects.equals(p.stance.ID, ValorForm.STANCE_ID) || Objects.equals(p.stance.ID, WisdomForm.STANCE_ID)){
            AbstractDriveForm Form = (AbstractDriveForm) p.stance;
            Form.CurrentFormCostPerTurn = 0;
            Form.IgnoreFormCostPerTurn = true;
        }
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        AbstractPlayer p = AbstractDungeon.player;
        if (Objects.equals(newStance.ID, ValorForm.STANCE_ID) || Objects.equals(newStance.ID, WisdomForm.STANCE_ID)){
            AbstractDriveForm Form = (AbstractDriveForm) newStance;
            Form.CurrentFormCostPerTurn = 0;
            Form.IgnoreFormCostPerTurn = true;
        }
    }

    @Override
    public void updateDescription(){
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}

