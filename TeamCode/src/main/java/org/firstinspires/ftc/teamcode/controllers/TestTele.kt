package org.firstinspires.ftc.teamcode.controllers

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.hardware.Claw
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import org.firstinspires.ftc.teamcode.hardware.Lift

class TestTele : OpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var claw: Claw
    lateinit var lift: Lift
    lateinit var manager: ControllerManager

    override fun init() {
        drivetrain = Drivetrain(hardwareMap)
        claw = Claw(hardwareMap)
        lift = Lift(hardwareMap)

        manager = ControllerManager(
            DriveController(gamepad1, drivetrain),
            LiftController(gamepad2, lift),
            ClawController(gamepad1, claw),
            LogController(telemetry, drivetrain, lift, claw)
        )
    }

    override fun loop() {
        manager.updateControllers()
    }

}