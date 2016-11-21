package org.firstinspires.ftc.teamcode.autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.robot.*;
import org.firstinspires.ftc.teamcode.util.Handler;
import org.firstinspires.ftc.teamcode.util.ramp.ExponentialRamp;

/*
 * Created by EvanCoulson on 10/26/16.
 *
 * _______   __       __  __   ______       ______   ______   ___    __   _________  ______   ______        ______   ________   ______    ___   ___
 /_______/\ /_/\     /_/\/_/\ /_____/\     /_____/\ /_____/\ /__/\ /__/\ /________/\/_____/\ /_____/\      /_____/\ /_______/\ /_____/\  /___/\/__/\
 \::: _  \ \\:\ \    \:\ \:\ \\::::_\/_    \:::__\/ \::::_\/_\::\_\\  \ \\__.::.__\/\::::_\/_\:::_ \ \     \:::_ \ \\::: _  \ \\:::_ \ \ \::.\ \\ \ \
  \::(_)  \/_\:\ \    \:\ \:\ \\:\/___/\    \:\ \  __\:\/___/\\:. `-\  \ \  \::\ \   \:\/___/\\:(_) ) )_    \:(_) \ \\::(_)  \ \\:(_) ) )_\:: \/_) \ \
   \::  _  \ \\:\ \____\:\ \:\ \\::___\/_    \:\ \/_/\\::___\/_\:. _    \ \  \::\ \   \::___\/_\: __ `\ \    \: ___\/ \:: __  \ \\: __ `\ \\:. __  ( (
    \::(_)  \ \\:\/___/\\:\_\:\ \\:\____/\    \:\_\ \ \\:\____/\\. \`-\  \ \  \::\ \   \:\____/\\ \ `\ \ \    \ \ \    \:.\ \  \ \\ \ `\ \ \\: \ )  \ \
     \_______\/ \_____\/ \_____\/ \_____\/     \_____\/ \_____\/ \__\/ \__\/   \__\/    \_____\/ \_\/ \_\/     \_\/     \__\/\__\/ \_\/ \_\/ \__\/\__\/

 */
@Autonomous(name="AutonomousMode", group="Bot")
public class BlueCenterPark extends AutonomousOpMode {
    private final double DRIVE_POWER = 0.8;

    private FlickerSystem flickerSystem;
    private BallLiftSystem ballSystem;

    @Override
    public void runOpMode() {
        initializeAllDevices();
        this.flickerSystem = new FlickerSystem(this.hardwareMap);
        this.ballSystem = new BallLiftSystem(this.hardwareMap);
        drive(1.75);
        shoot();
        load();
        shoot();
        drive(4);
        park();
    }

    public void drive(double targetPosition) {
        try {
            driveWithEncoders(targetPosition, DRIVE_POWER);
        } catch (Exception e) {
            throw new NullPointerException(e.toString());
        }
     }

    public void shoot() {
        flickerSystem.setShootPosition();
        flickerSystem.shoot();
        flickerSystem.setLoadPosition();
    }

    public void load() {
        while (!flickerSystem.isBallLoaded()) {
            ballSystem.runLift(true);
            ballSystem.runBelt(true);
        }
        ballSystem.stopBelt();
        ballSystem.stopLift();
    }

    public void park() {
        try {
            driveWithEncoders(0,0);
        } catch (Exception e) {

        }
    }


}
