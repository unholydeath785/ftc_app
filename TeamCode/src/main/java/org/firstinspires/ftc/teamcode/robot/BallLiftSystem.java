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

    private final double LIFT_POWER = 0.42;
    private final double BELT_POWER = 0.6;
    private final double INTAKE_POWER = 0.8;
    private final int ticksPerRotation = 1120;

    private HardwareMap map;
    private DcMotor lifter;
    private DcMotor belt;
    private DcMotor intake;
    public boolean autonomous;

    private boolean debug;

    public BallLiftSystem(HardwareMap map) {
        this.map = map;
        this.lifter = map.dcMotor.get("ballLiftMotor");
        this.belt = map.dcMotor.get("ballBeltMotor");
        this.intake = map.dcMotor.get("ballIntakeMotor");

    }
    public void runIntake(boolean isFoward){
        if(isFoward){
            intake.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        else{
            intake.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setPower(INTAKE_POWER);
    }
    public void runIntake(double revolutions){
        intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intake.setTargetPosition(intake.getCurrentPosition()+revolutionsToTics(revolutions));
        intake.setPower(INTAKE_POWER);
        
    }
    public void runLift(boolean isFoward) {
        if (isFoward)
            lifter.setDirection(DcMotorSimple.Direction.FORWARD);
        else
            lifter.setDirection(DcMotorSimple.Direction.REVERSE);
        lifter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifter.setPower(LIFT_POWER);
    }

    public void runLift(double revolutions) {
        lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lifter.setTargetPosition(lifter.getCurrentPosition() + revolutionsToTics(revolutions));
        lifter.setPower(LIFT_POWER);
        while (lifter.isBusy()) {

        }
    }

    public void stopLift() {
        lifter.setPower(0.0);
    }

    public void runBelt(boolean isFoward) {
        if (isFoward)
            belt.setDirection(DcMotorSimple.Direction.FORWARD);
        else
            belt.setDirection(DcMotorSimple.Direction.REVERSE);
        belt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        belt.setPower(BELT_POWER);
    }

    public void runBelt(double revolutions) {
        belt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        belt.setTargetPosition(belt.getCurrentPosition() + revolutionsToTics(revolutions));
        belt.setPower(BELT_POWER);
        while (belt.isBusy()) {

        }
    }

    public void stopBelt() {
        belt.setPower(0.0);
    }

    private int revolutionsToTics(double revolutions) {
        return (int) Math.round(revolutions * this.ticksPerRotation);
    }
}
