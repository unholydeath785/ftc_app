package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by EvanCoulson on 11/15/16.
 */

public class BallLiftSystem {
    private HardwareMap map;
    private DcMotor lifter;
    private DigitalChannel inputStream;
    private Telemetry telemetry;
    private LiftPosition position;

    private boolean debug;
    private boolean running;

    public BallLiftSystem(HardwareMap map) {
        this.map = map;
        this.lifter = map.dcMotor.get("ballLifterMotor");
        this.inputStream = map.digitalChannel.get("ballLifterSwitch");
        this.position = LiftPosition.AT_SWITCH;
    }

    public void spin() {
        while (running) {
            lifter.setPower(0.42);
            lifter.setDirection(DcMotorSimple.Direction.FORWARD);
            lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            lifter.setTargetPosition(lifter.getCurrentPosition() + 1120);
        }
    }

    public void runToSwitchPosition() {

        position = LiftPosition.AT_ENCODER;
    }

    public void runToEncoderPosition() {
        position = LiftPosition.AT_SWITCH;
    }

    public void togglePosition() {
        if (position == LiftPosition.AT_SWITCH) {
            runToEncoderPosition();
        } else {
            runToSwitchPosition();
        }
    }

    public boolean getState() {
        try {
            return inputStream.getState();
        } catch (Exception e) {
            if (debug) {
                telemetry.addData("ERROR: ", "BALL LIFT INPUT STREAM IS NULL");
            }
            throw new NullPointerException("Null Ball Lift Stream");
        }
    }

    public void setDebug(Telemetry telemetry) {
        this.telemetry = telemetry;
        this.debug = true;
    }

    public void setSpinning(boolean running) {
        this.running = running;
    }

    private enum LiftPosition {
        AT_SWITCH,
        AT_ENCODER
    }
}
