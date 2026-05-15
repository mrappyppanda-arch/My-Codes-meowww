package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.mechanisms.TestBench;

@TeleOp(name = "Testing", group = "Testing")
public class Testing extends OpMode {
    TestBench bench = new TestBench();
    Gamepad gamepad = new Gamepad();


    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        bench.Drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        bench.State(-gamepad2.left_stick_y);
        if (gamepad1.dpad_up){
            bench.setState(2);
        } else if (gamepad1.dpad_down){
            bench.setState(0);
        }else if (gamepad1.dpad_right){
            bench.setState(1);
        } else if (gamepad1.dpad_left) {
            bench.setState(3);
        }

        telemetry.addLine("Use D-Pad to change state: Up = Highest, Right = Middle, Down = Lowest, Left = Manual");
        telemetry.addLine("State: " + bench.getState());
        telemetry.addLine("Slide Target: " + bench.getAaa());
        telemetry.addLine("Slide Current: " + bench.getPosition());
        telemetry.update();
    }
}