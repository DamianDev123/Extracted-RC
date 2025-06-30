package org.firstinspires.ftc.robotcontroller.internal;

import android.content.Context;
import android.util.Log;

import com.qualcomm.hardware.lynx.LynxAnalogInputController;
import com.qualcomm.hardware.lynx.LynxDcMotorController;
import com.qualcomm.hardware.lynx.LynxDigitalChannelController;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.lynx.LynxServoController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;

@TeleOp(name="MyAutonomousOpMode")
public class MyAutonomousOpMode extends OpMode {

    private static final String TAG = "MyAutonomousOpMode";

    LynxModule module;
    LynxDcMotorController dcController;
    LynxServoController servoController;
    LynxAnalogInputController analogInputController;
    LynxDigitalChannelController digitalChannelController;

    @Override
    public void init() {
        Context context = hardwareMap.appContext;

        if (hardwareMap.getAll(LynxModule.class).isEmpty()) {
            throw new RuntimeException("No Lynx modules found!");
        }
        module = hardwareMap.getAll(LynxModule.class).get(0);

        try {
            dcController = new LynxDcMotorController(context, module);
            servoController = new LynxServoController(context, module);
            analogInputController = new LynxAnalogInputController(context, module);
            digitalChannelController = new LynxDigitalChannelController(context, module);
            Log.i(TAG, "Lynx controllers initialized");
        } catch (RobotCoreException | InterruptedException e) {
            throw new RuntimeException("Failed to initialize Lynx controllers", e);
        }
    }

    @Override
    public void loop() {
        double voltage = 0;
        voltage = analogInputController.getAnalogInputVoltage(0);
        Log.i(TAG, "Analog Input Voltage (port 0): " + voltage);
    }
}
