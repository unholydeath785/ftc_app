package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.teleop.TeleOpMecanum;
import org.firstinspires.ftc.teamcode.util.ramp.ExponentialRamp;
import org.firstinspires.ftc.teamcode.util.ramp.Ramp;

/**
 * Created by jacks on 11/7/2016.
 *
 *
 */

//              ___                   ___          ___           ___           ___           ___                    ___                       ___                       ___           ___
//             /  /\                 /  /|        /  /\         /__/|         /  /\         /  /\                  /  /\          ___        /  /\          ___        /  /\         /__/\
//            /  /:/_               /  /:/       /  /:/        |  |:|        /  /:/_       /  /::\                /  /:/_        /__/|      /  /:/_        /  /\      /  /:/_       |  |::\
//           /  /:/ /\  ___        /  /:/       /  /:/         |  |:|       /  /:/ /\     /  /:/\:\              /  /:/ /\      |  |:|     /  /:/ /\      /  /:/     /  /:/ /\      |  |:|:\
//          /  /:/ /:/ /__/\      /__/::\ __   /  /:/  ___   __|  |:|      /  /:/ /:/_   /  /:/~/:/             /  /:/ /::\     |  |:|    /  /:/ /::\    /  /:/     /  /:/ /:/_   __|__|:|\:\
//         /__/:/ /:/  \  \:\     \__\/\:\ /\ /__/:/  /  /\ /__/\_|:|____ /__/:/ /:/ /\ /__/:/ /:/___          /__/:/ /:/\:\  __|__|:|   /__/:/ /:/\:\  /  /::\    /__/:/ /:/ /\ /__/::::| \:\
//         \  \:\/:/   \  \:\ __    \  \:\//  \  \:\ /  /:/ \  \:\/:::::/ \  \:\/:/ /:/ \  \:\/:::::/          \  \:\/:/~/:/ /__/::::\   \  \:\/:/~/:/ /__/:/\:\   \  \:\/:/ /:/ \  \:\~~\__\/
//         \  \::/     \  \:\/:|    \__\:/    \  \:\  /:/   \  \::/~~~~   \  \::/ /:/   \  \::/~~~~            \  \::/ /:/     ~\~~\:\   \  \::/ /:/  \__\/  \:\   \  \::/ /:/   \  \:\
//         \  \:\      \  \:: /    /  /:/     \  \:\/:/     \  \:\        \  \:\/:/     \  \:\                 \__\/ /:/        \  \:\   \__\/ /:/        \  \:\   \  \:\/:/     \  \:\
//         \  \:\      \  \ /     /__/ /      \  \::/       \  \:\        \  \::/       \  \:\                  /__/:/          \__\/     /__/:/          \__\/    \  \::/       \  \:\
//         \__\/       \__\/      \__\/       \__\/         \__\/         \__\/         \__\/                  \__\/                     \__\/                     \__\/         \__\/

public class FlickerSystem {
    private DcMotor flicker;
    private Servo loadServo;
    private ServoPositions position;
    private Telemetry telemetry;

    public FlickerSystem(HardwareMap map) {
        this.flicker = map.dcMotor.get("flicker");
        this.loadServo = map.servo.get("flickerLoad");
        this.position = ServoPositions.FLICKERLOAD;
    }

    public void shoot() {
        if (!flicker.isBusy()) {
            flicker.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            flicker.setDirection(DcMotor.Direction.FORWARD);
            if (telemetry != null) {
                telemetry.addData("Current Position: ", flicker.getCurrentPosition() + "º");
                telemetry.addData("Taregt Position: ", (flicker.getCurrentPosition() + 1120) + "º");
            }
            flicker.setTargetPosition(flicker.getCurrentPosition() + 1120);
            flicker.setPower(0.8);
        }
    }

    public void setLoadPosition() {
        loadServo.setDirection(Servo.Direction.FORWARD);
        loadServo.setPosition(0.55);
        this.position = ServoPositions.FLICKERLOAD;

    }

    public void setShootPosition() {
        loadServo.setDirection(Servo.Direction.REVERSE);
        loadServo.setPosition(0.60);
        this.position = ServoPositions.FLICKERLOAD;
    }

    public void togglePosition() {
        if (telemetry != null) {
            telemetry.addData("Current Position: ", this.loadServo.getPosition() + "º");
        }
        if (this.position == ServoPositions.FLICKERLOAD) {
            setShootPosition();
        } else {
            setLoadPosition();
        }
    }

    public void setDebugMode(Telemetry telemetry) {
        this.telemetry = telemetry;
    }



    private enum ServoPositions {
        FLICKERLOAD,
        FLICKERSHOOT
    }
}
