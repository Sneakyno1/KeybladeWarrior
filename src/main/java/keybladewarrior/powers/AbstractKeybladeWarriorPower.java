package keybladewarrior.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import keybladewarrior.ModFile;
import keybladewarrior.util.TexLoader;

public abstract class AbstractKeybladeWarriorPower extends AbstractEasyPower {


    public AbstractKeybladeWarriorPower(String ID, String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        super(ID, NAME, powerType, isTurnBased, owner, amount);
    }

    public float onGainDrive(float amount) {
        return amount;
    }
}