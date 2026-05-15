package org.firstinspires.ftc.teamcode.softwareImprovements;



public class PID {
    private double proportional;
    private double derivative;
    private double integral;
    private double error;
    private double lastError;
    private double integralSum;
    private double out;

    public void PIDControl(double kP, double kI, double kD){
        this.proportional = error * kP;
        this.integral += error * kI;
        this.derivative = (error - derivative) * kD;
    }

    public double calculate(double time, double current, double target){
        error = target - current;
        //calculate the PID output here
        derivative = (error - lastError) / time;

        // sum of all error over time
        integralSum = integralSum + (error * time);

        out = (this.proportional * error) + (this.integral * integralSum) + (this.derivative * derivative);

        lastError = error;

        return out;
    }
}
