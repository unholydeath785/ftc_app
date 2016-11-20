package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by jacks on 11/7/2016.
 *
 *
 */

//              ___                   ___           ___           ___           ___           ___                    ___                       ___                       ___           ___
//             /  /\                  /  /|         /  /\         /__/|         /  /\         /  /\                  /  /\          ___        /  /\          ___        /  /\         /__/\
//            /  /:/_                /  /:/        /  /:/        |  |:|        /  /:/_       /  /::\                /  /:/_        /__/|      /  /:/_        /  /\      /  /:/_       |  |::\
//           /  /:/ /\  ___         /  /:/        /  /:/         |  |:|       /  /:/ /\     /  /:/\:\              /  /:/ /\      |  |:|     /  /:/ /\      /  /:/     /  /:/ /\      |  |:|:\
//          /  /:/ /:/ /__/\       /  /::\ __    /  /:/  ___   __|  |:|      /  /:/ /:/_   /  /:/~/:/             /  /:/ /::\     |  |:|    /  /:/ /::\    /  /:/     /  /:/ /:/_   __|__|:|\:\
//         /__/:/ /:/  \  \:\     /  / /\:\  /\ /__/:/  /  /\ /__/\_|:|____ /__/:/ /:/ /\ /__/:/ /:/___          /__/:/ /:/\:\  __|__|:|   /__/:/ /:/\:\  /  /::\    /__/:/ /:/ /\ /__/::::| \:\
//         \  \:\/:/   \  \:\ __ /__/ / \:\/ /  \  \:\ /  /:/ \  \:\/:::::/ \  \:\/:/ /:/ \  \:\/:::::/          \  \:\/:/~/:/ /__/::::\   \  \:\/:/~/:/ /__/:/\:\   \  \:\/:/ /:/ \  \:\~~\__\/
//         \  \::/     \  \:\/:| \__\/__\: /    \  \:\  /:/   \  \::/~~~~   \  \::/ /:/   \  \::/~~~~            \  \::/ /:/     ~\~~\:\   \  \::/ /:/  \__\/  \:\   \  \::/ /:/   \  \:\
//         \  \:\      \  \:: /     /  /: /     \  \:\/:/     \  \:\        \  \:\/:/     \  \:\                 \__\/ /:/        \  \:\   \__\/ /:/        \  \:\   \  \:\/:/     \  \:\
//         \  \:\      \  \ /      /__/  /      \  \::/       \  \:\        \  \::/       \  \:\                  /__/:/          \__\/     /__/:/          \__\/    \  \::/       \  \:\
//         \__\/       \__\/       \__\/        \__\/         \__\/         \__\/         \__\/                  \__\/                     \__\/                     \__\/         \__\/

public class FlickerSystem {
    private DcMotor flicker;
    private Servo loadWing;
    private ServoPositions position;
    private Telemetry telemetry;

    public FlickerSystem(HardwareMap map) {
        this.flicker = map.dcMotor.get("flicker");
        this.loadWing = map.servo.get("flickerLoad");
        this.position = ServoPositions.FLICKERLOAD;
        flicker.setDirection(DcMotor.Direction.REVERSE);
    }

    public void shoot() {
        if (position == ServoPositions.FLICKERSHOOT) {
            flicker.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            if (telemetry != null) {
                telemetry.addData("Current Position: ", flicker.getCurrentPosition() + "º");
                telemetry.addData("Taregt Position: ", (flicker.getCurrentPosition() + 1120) + "º");
            }
            flicker.setTargetPosition(flicker.getCurrentPosition() + 1120);
            flicker.setPower(0.8);
        }
    }

    public void stop() {
        flicker.setPower(0.0);
    }

    public void setLoadPosition() {
        loadWing.setPosition(0.51);
        //.5 or .6
        this.position = ServoPositions.FLICKERLOAD;
    }

    public void setShootPosition() {
        loadWing.setPosition(0.46);
        this.position = ServoPositions.FLICKERSHOOT;
    }

    public void togglePosition() {
        if (telemetry != null) {
            telemetry.addData("Current Position: ", this.loadWing.getPosition() + "º");
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
