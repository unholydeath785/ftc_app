package org.firstinspires.ftc.teamcode.autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
public class BlueCenterPark extends AutonomousOpMode {
    private HardwareMap hardwareMap;
    private FlickerSystem flickerSystem;
    private MecanumDriveSystem driveSystem;
    private ExponentialRamp ramp;

    public void initialzeAllDevices(HardwareMap map) {
        this.hardwareMap = map;
        this.flickerSystem = new FlickerSystem(map);
        this.driveSystem = new MecanumDriveSystem();
        this.driveSystem.init(map);
    }

    @Override
    public void runOpMode() {
        initialzeAllDevices(hardwareMap);
        drive(100);
        shoot();
        drive(2000);
        park();
    }

    public void drive(int targetPosition) {
        this.driveSystem.runUsingEncoders();
        try {
            driveWithEncoders(targetPosition, 1.0);
        } catch(Exception e) {
        }
    }

    public void shoot() {
        flickerSystem.shoot();
    }

    public void park() {

        boolean park = this.driveSystem.anyMotorsBusy();
        if(!park){
            this.driveSystem.drive(0.0); //use drive or driveWithEncoder?
        }

    }
   /*
        drive:
         get four motor wheels from hardwaremap drive to BELUGA
         stop

       shoot:
        flicker motor hardwareMap get flicker
        do 2x
            spin motor with encoders
            turn with encoders 1 cycle

       drive:
        get four wheels from hwmap
        while (not at x)
           drive foward ramp up to 100%
           if (approaching x)
               ramp down exponential

       park:
        Pointy stick hit
        keep going
        stahp
    */



}
