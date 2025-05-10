package keybladewarrior.actions.miscellaneousActions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import keybladewarrior.driveForms.AbstractDriveForm;
import keybladewarrior.powers.DriveBankPower;
import keybladewarrior.powers.DrivePoints;

public class GainedDriveInDriveFormWithNoActiveDrivePointsAction extends AbstractGameAction {
    AbstractPlayer p;
    DrivePoints points;
    int amountToReduce;

    public GainedDriveInDriveFormWithNoActiveDrivePointsAction(AbstractPlayer p, DrivePoints drivePoints, int amountToReduce){
        this.p = p;
        this.points = drivePoints;
        this.amountToReduce = amountToReduce;

    }

    @Override
    public void update(){
        if (p.stance instanceof AbstractDriveForm) {
            if (p.hasPower(DriveBankPower.ID)){
                p.getPower(DriveBankPower.ID).onApplyPower(points,p,p);
            }
            points.reducePower(amountToReduce);
        }


        this.isDone = true;
    }

}
