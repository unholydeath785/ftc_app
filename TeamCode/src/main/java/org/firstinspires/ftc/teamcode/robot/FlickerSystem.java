package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jacks on 11/7/2016.
 */

public class FlickerSystem {
    private DcMotor flicker;

    public FlickerSystem(HardwareMap map) {
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
}
