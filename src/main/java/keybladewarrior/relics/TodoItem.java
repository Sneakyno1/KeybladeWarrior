package keybladewarrior.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import keybladewarrior.KeybladeWarrior;
import keybladewarrior.powers.DrivePoints;

import static keybladewarrior.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, KeybladeWarrior.Enums.CARD_COLOR);
    }

    @Override
    public void onShuffle() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrivePoints(AbstractDungeon.player),2));
        super.onShuffle();
    }
}
