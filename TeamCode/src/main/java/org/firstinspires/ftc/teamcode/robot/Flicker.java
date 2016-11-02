package org.firstinspires.ftc.teamcode.robot;

<<<<<<< HEAD
/**
 * Created by EvanCoulson on 10/23/16.
 */
public class Flicker {
=======
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by EvanCoulson on 10/25/16.
 */
public class Flicker {
    private DcMotor flicker;
//    public Button shootButton;

    public Flicker(HardwareMap map) {
        this.flicker = map.dcMotor.get("flicker");
    }

    public void shoot() {
        if (!flicker.isBusy()) {
            flicker.setTargetPosition(flicker.getCurrentPosition() + 1120);
            flicker.setDirection(DcMotor.Direction.FORWARD);
            flicker.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            flicker.setPower(0.8);
        }
    }
>>>>>>> 6256564b7bd27a26b8b20f1050555b4e7481069e
}
