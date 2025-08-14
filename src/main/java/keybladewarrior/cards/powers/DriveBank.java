//package keybladewarrior.cards.powers;
//
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import keybladewarrior.KeybladeWarrior;
//import keybladewarrior.cards.AbstractEasyCard;
//import keybladewarrior.powers.DriveBankPower;
//
//import static keybladewarrior.ModFile.makeID;
//
//public class DriveBank extends AbstractEasyCard {
//    public static final String ID =makeID(DriveBank.class.getSimpleName());
//
//
//    public DriveBank(){
//        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, KeybladeWarrior.Enums.CARD_COLOR);
//        this.baseMagicNumber = this.magicNumber = 2;
//    }
//
//    @Override
//    public void upp() {
//        upgradeMagicNumber(2);
//        super.upgrade();
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster monster) {
//        addToBot(new ApplyPowerAction(p, p, new DriveBankPower(p, this.magicNumber), this.magicNumber));
//    }
//}
