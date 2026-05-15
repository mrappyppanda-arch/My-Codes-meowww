package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestBench {
    private DcMotor linearSlideMotor0;

    private DcMotor leftB, leftF, rightB, rightF;

    private int aaa = 0;
    private enum SlideState{
        zero,
        middle,
        high,
        manual
    }
    private SlideState slideState = SlideState.zero;

    public void init(HardwareMap hardwareMap){
        //touch sensor code

        // Dc motor

        linearSlideMotor0 = hardwareMap.get(DcMotor.class, "VLL");
        linearSlideMotor0.setDirection(DcMotor.Direction.REVERSE);
        linearSlideMotor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlideMotor0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideMotor0.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor0.setTargetPosition(0);

        leftB = hardwareMap.get(DcMotor.class,"LeftB");
        leftF = hardwareMap.get(DcMotor.class,"LeftF");
        rightB = hardwareMap.get(DcMotor.class,"RightB");
        rightF = hardwareMap.get(DcMotor.class,"RightF");

        leftB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftB.setDirection(DcMotorSimple.Direction.REVERSE);
        leftF.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void Drive(double y, double x ,double z) {
        double fl = y + x + z;
        double bl = y - x + z;
        double fr = y - x - z;
        double br = y + x - z;

        double max = Math.max(Math.abs(fl), Math.max(Math.abs(bl), Math.max(Math.abs(fr), Math.abs(br))));
        if (max > 1.0) {
            fl /= max;
            bl /= max;
            fr /= max;
            br /= max;
        }

        leftF.setPower(fl);
        leftB.setPower(bl);
        rightF.setPower(fr);
        rightB.setPower(br);
    }
    public void State(double power) {
        aaa = clamp(aaa, 0, 850);
        linearSlideMotor0.setPower(1.0);
        linearSlideMotor0.setTargetPosition(aaa);
        if (slideState == slideState.zero) {
            aaa = 0;
        } else if (slideState == slideState.middle) {
            aaa = 425;
        } else if (slideState == slideState.high) {
            aaa = 850;
        } else if (slideState == slideState.manual) {
            aaa += (int) (power * 10); // Adjust the multiplier for sensitivity
        }
    }

    public int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }

    public void setState(int state){
        if (state == 0){
            slideState = SlideState.zero;
        } else if (state == 1) {
            slideState = SlideState.middle;
        } else if (state == 2) {
            slideState = SlideState.high;
        } else if (state == 3) {
            slideState = SlideState.manual;
        }
    }

    public int getPosition() {
        return linearSlideMotor0.getCurrentPosition();
    }
    public int getAaa() {
        return aaa;
    }

    public String getState() {
        if(slideState == SlideState.zero) {
            return "Zero";
        } else if (slideState == SlideState.middle) {
            return "Middle";
        } else if (slideState == SlideState.high) {
            return "High";
        } else if (slideState == SlideState.manual) {
            return "Manual";
        } else {
            return "Unknown State";
        }
    }
    public void setPowerSpeed(double speed) {
        // accepts values from -1.0 to 1.0
        linearSlideMotor0.setPower(speed);
    }

}
