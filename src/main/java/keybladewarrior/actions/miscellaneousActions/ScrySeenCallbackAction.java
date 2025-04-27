package keybladewarrior.actions.miscellaneousActions;
// taken from https://github.com/Jedi515/sts-jedi
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ScrySeenCallbackAction
    extends ScryAction
{
    public Consumer<ArrayList<AbstractCard>> callback;
    public ScrySeenCallbackAction(int numCards, Consumer<ArrayList<AbstractCard>> callback) {
        super(numCards);
        this.callback = callback;
    }
}
