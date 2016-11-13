package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.util.ramp.*;

public class MecanumDriveSystem
{
    public DcMotor  motorFrontLeft   = null;
    public DcMotor  motorFrontRight  = null;
    public DcMotor  motorBackLeft   = null;
    public DcMotor  motorBackRight  = null;

    public final int ticksPerRotation = 1120;

    HardwareMap hwMap           =  null;

    public final double minimumPower = 0.1; //TODO: Figure out the best value for this.

    /* Constructor */
    public MecanumDriveSystem()
    {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hwMap)
    {
        this.hwMap = hwMap;

        this.motorFrontLeft   = this.hwMap.dcMotor.get("motor_front_left");
        this.motorFrontRight  = this.hwMap.dcMotor.get("motor_front_right");
        this.motorBackLeft   = this.hwMap.dcMotor.get("motor_back_left");
        this.motorBackRight  = this.hwMap.dcMotor.get("motor_back_right");
        this.motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        this.motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        this.motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        this.motorBackRight.setDirection(DcMotor.Direction.FORWARD);

        // Set all drive motors to zero power
        drive(0);

        runUsingEncoders();
    }

    public void runUsingEncoders()
    {
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setTargetPosition(int position)
    {
        int frontLeftTarget = this.motorFrontLeft.getCurrentPosition() + position;
        int frontRightTarget = this.motorFrontRight.getCurrentPosition() + position;
        int backLeftTarget = this.motorBackLeft.getCurrentPosition() + position;
        int backRightTarget = this.motorBackRight.getCurrentPosition() + position;

        // Tell the motors where we are going
        this.motorFrontLeft.setTargetPosition(frontLeftTarget);
        this.motorFrontRight.setTargetPosition(frontRightTarget);
        this.motorBackLeft.setTargetPosition(backLeftTarget);
        this.motorBackRight.setTargetPosition(backRightTarget);

        runUsingEncoders();
    }

    public boolean anyMotorsBusy()
    {
        return (this.motorFrontLeft.isBusy() && this.motorFrontRight.isBusy() && this.motorBackRight.isBusy() && this.motorBackLeft.isBusy());
    }

    public int revolutionsToTicks(double revolutions)
    {
        return (int) Math.round(revolutions * this.ticksPerRotation);
    }

    public double ticksToRevolutions(int ticks)
    {
        return ((double) ticks / this.ticksPerRotation);
    }

    public int getMinimumDistanceFromTarget()
    {
        int d = this.motorFrontLeft.getTargetPosition() - this.motorFrontLeft.getCurrentPosition();
        d = Math.min(d, this.motorFrontRight.getTargetPosition() - this.motorFrontRight.getCurrentPosition());
        d = Math.min(d, this.motorBackLeft.getTargetPosition() - this.motorBackLeft.getCurrentPosition());
        d = Math.min(d, this.motorBackRight.getTargetPosition() - this.motorBackRight.getCurrentPosition());

        return d;
    }

    // Tweeks the left and right motors by increment.
    // increment is added to the current power of the left wheels
   /// and subtracted from the current power of the right wheels.
    public void tweakTankDrive(double increment)
    {
        double leftMotorPower = this.motorBackLeft.getPower();
        double rightMotorPower = this.motorBackRight.getPower();

        tankDrive(leftMotorPower + increment, rightMotorPower - increment);
    }

    public void tankDrive(double leftPower, double rightPower)
    {
        this.motorFrontLeft.setPower(leftPower);
        this.motorBackLeft.setPower(leftPower);
        this.motorFrontRight.setPower(rightPower);
        this.motorBackRight.setPower(rightPower);
    }

    public void drive(double power)
    {
        this.motorFrontLeft.setPower(power);
        this.motorFrontRight.setPower(power);
        this.motorBackLeft.setPower(power);
        this.motorBackRight.setPower(power);
    }

    public void adjustPower(Ramp ramp)
    {
        // Adjust the motor power as we get closer to the target
        int minDistance = this.getMinimumDistanceFromTarget();
        double revolutionsAway = this.ticksToRevolutions(minDistance);

        double scaledPower = ramp.value(minDistance);

        drive(scaledPower);
    }

    public void mecanumDrive(float rightX, float rightY, float leftX, float leftY)
    {
        rightX = Range.clip(rightX, -1, 1);
        rightY = Range.clip(rightY, -1, 1);
        leftX = Range.clip(leftX, -1, 1);
        leftY = Range.clip(leftY, -1, 1);

        rightX = scaleJoystickValue(rightX);
        rightY = scaleJoystickValue(rightY);
        leftX = scaleJoystickValue(leftX);
        leftY =  scaleJoystickValue(leftY);

        // write the values to the motors
        double frontRightPower = leftY + rightX + leftX;
        double backRightPower = leftY + rightX - leftX;
        double frontLeftPower = leftY - rightX - leftX;
        double backLeftPower = leftY - rightX + leftX;
        motorFrontRight.setPower(Range.clip(leftY + rightX + leftX, -1, 1));
        motorBackRight.setPower(Range.clip(leftY + rightX - leftX, -1, 1));
        motorFrontLeft.setPower(Range.clip(leftY - rightX - leftX, -1, 1));
        motorBackLeft.setPower(Range.clip(leftY - rightX + leftX, -1, 1));
    }

    float scaleJoystickValue(float joystickValue)
    {
        if(joystickValue > 0)
        {
            return (float)((joystickValue*joystickValue)*.62);
        }
        else
        {
            return (float)(-(joystickValue*joystickValue)*.62);
        }
    }
}

