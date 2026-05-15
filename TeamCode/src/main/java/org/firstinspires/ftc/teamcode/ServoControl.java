package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ServoControl")
public class ServoControl extends LinearOpMode {

    private Servo clawServo;

    @Override
    public void runOpMode() {

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                clawServo.setPosition(1.0);
            }

            if (gamepad1.b) {
                clawServo.setPosition(0.0);
            }

            telemetry.addData("Servo Position", clawServo.getPosition());
            telemetry.update();
        }
    }
}
