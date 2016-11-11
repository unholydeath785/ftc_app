/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.robot.*;
import org.firstinspires.ftc.teamcode.util.Handler;


/**
 * TeleOpMecanum Mode
 * <p>
 * Enables control of the robot via the gamepad
 */

@TeleOp(name="TeleOpMecanum", group="TeleOp")
public class  TeleOpMecanum extends OpMode {

	//region Motors
	MecanumDriveSystem driveSystem;

	//DcMotor flickerMotor;
	//DcMotor winchMotor;
	//endregion

	//region Servos
	//Servo servoRightWing;
	//Servo servoLeftWing;
	//Servo servoClimberRelease;
	//endregion

	//AnalogInput armPotentiometer;

    private Button climberReleaseButton;
    private Button leftWingButton;
    private Button flickerButton;
    private Button flickerShootPositionButton;
    private Button flickerLoadPositionButton;

    //private DcMotorServo armDcMotorServo;
	//double climbPos = 2.0; //TODO: Figure out the correct value
	//double extendPos = 1.1; //TODO: Figure out the correct value
	//double maxPos = 4.0; //TODO: Figure out the correct value
	//double minPos = 1.0; //TODO: Figure out the correct value

	public TeleOpMecanum() {

	}

	/*
	 * Code to run when the op mode is initialized goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
	 */
	@Override
	public void init()
	{
        driveSystem = new MecanumDriveSystem();
        this.driveSystem.init(this.hardwareMap);

        //this.armDcMotorServo = new DcMotorServo();
        //this.armDcMotorServo.init(this.hardwareMap, "armMotor", "armServo");
        //this.armDcMotorServo.forwardPower = 0.5;
        //this.armDcMotorServo.reversePower = 0.1;

		//winchMotor = hardwareMap.dcMotor.get("winchMotor");

		//servoLeftWing = hardwareMap.servo.get("servoLeftWing");
		//servoRightWing = hardwareMap.servo.get("servoRightWing");
		//servoClimberRelease = hardwareMap.servo.get("servoClimberRelease");

        this.climberReleaseButton = new Button();
        this.climberReleaseButton.isPressed =
            new Func<Boolean>()
            {
                @Override
                public Boolean value()
                {
                return gamepad2.b;
                }
            };
        this.climberReleaseButton.pressedHandler =
            new Handler()
            {
                @Override
                public void invoke()
                {
                    driveSystem.motorBackLeft.setPower(1);
                }
            };
        this.climberReleaseButton.releasedHandler =
            new Handler()
            {
                @Override
                public void invoke()
                {
                    driveSystem.motorBackLeft.setPower(0);
                }
            };

        this.leftWingButton = new Button();
        this.leftWingButton.isPressed =
            new Func<Boolean>()
            {
                @Override
                public Boolean value()
                {
                    return gamepad2.a;
                }
            };
        this.leftWingButton.pressedHandler =
            new Handler()
            {
                @Override
                public void invoke()
                {
                    driveSystem.motorFrontRight.setPower(1);
                }
            };
        this.leftWingButton.releasedHandler =
            new Handler()
            {
                @Override
                public void invoke()
                {
                    driveSystem.motorFrontRight.setPower(0);
                }
            };

        this.flickerButton = new Button(); //TODO: Flicker System implementation whoever is doing that
        this.flickerButton.isPressed =
            new Func<Boolean>()
                {
                    @Override
                    public Boolean value()
                    {
                        return gamepad1.right_trigger > 0;
                    }
                };
        this.flickerButton.pressedHandler =
            new Handler()
            {
                @Override
                public void invoke()
                {
                    //move flicker
                }
            };
        this.flickerButton.releasedHandler =
            new Handler()
            {
                @Override
                public void invoke()
                {
                    //stop flicker
                }
            };

        this.flickerShootPositionButton = new Button(); //TODO: Flicker System implementation whoever is doing that
        this.flickerShootPositionButton.isPressed =
                new Func<Boolean>()
                {
                    @Override
                    public Boolean value()
                    {
                        return gamepad2.dpad_up;
                    }
                };
        this.flickerShootPositionButton.pressedHandler =
                new Handler()
                {
                    @Override
                    public void invoke()
                    {
                        //move flicker
                    }
                };
        this.flickerShootPositionButton.releasedHandler =
                new Handler()
                {
                    @Override
                    public void invoke()
                    {
                        //stop flicker
                    }
                };

        this.flickerLoadPositionButton = new Button(); //TODO: Flicker System implementation whoever is doing that
        this.flickerLoadPositionButton.isPressed =
                new Func<Boolean>()
                {
                    @Override
                    public Boolean value()
                    {
                        return gamepad2.dpad_down;
                    }
                };
        this.flickerLoadPositionButton.pressedHandler =
                new Handler()
                {
                    @Override
                    public void invoke()
                    {
                        //move flicker
                    }
                };
        this.flickerLoadPositionButton.releasedHandler =
                new Handler()
                {
                    @Override
                    public void invoke()
                    {
                        //stop flicker
                    }
                };
	}

	/*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
	@Override
	public void loop()
	{
		//double currentPos = 0;//armPotentiometer.getVoltage();

		// scale the joystick value to make it easier to control
		// the robot more precisely at slower speeds.
        this.driveSystem.mecanumDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x, gamepad1.left_stick_y);

        climberReleaseButton.testAndHandle();
        leftWingButton.testAndHandle();
        flickerButton.testAndHandle();
        flickerShootPositionButton.testAndHandle();
        flickerLoadPositionButton.testAndHandle();

		//region Winch
		//if(gamepad1.right_trigger > 0 && gamepad2.right_trigger > 0)
		//{
            //winchMotor.setPower(1.0);
		//}
		//else if(gamepad1.left_trigger > 0 && gamepad2.left_trigger > 0)
		//{
			//winchMotor.setPower(-1.0);
		//}
		//else
		//{
			//winchMotor.setPower(0);
		//}
		//endregion

		//if (gamepad2.dpad_up)
		//{
			// move flicker ramp to shoot position
		//}
		//else if (gamepad2.dpad_down)
		//{
			//move flicker ramp to load position
		//}
		//else if (gamepad2.a)
		//{
			//this.armDcMotorServo.targetPosition = extendPos;
		//}
		//else if (gamepad2.x)
		//{
			//this.armDcMotorServo.targetPosition = climbPos;
		//}
		//else
		//{
            // if none of the above buttons are currently held down,
            // just hold the current position
			//this.armDcMotorServo.targetPosition = this.armDcMotorServo.getCurrentPosition();
		//}

        //this.armDcMotorServo.loop();
        double rightX = gamepad1.right_stick_x;
        double rightY = gamepad1.right_stick_y;
        double leftX = gamepad1.left_stick_x;
        double leftY = gamepad1.left_stick_y;
		telemetry.addData("Text", rightX + ", " + rightY + ", " + leftX + ", " + leftY);
		//telemetry.addData("pot", this.armDcMotorServo.getCurrentPosition());
		//telemetry.addData("targetPosition",this.armDcMotorServo.targetPosition);
		//telemetry.addData("rightWingPos",servoRightWing.getPosition());
		//telemetry.addData("leftWingPos",servoLeftWing.getPosition());
	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop()
	{

	}
}
