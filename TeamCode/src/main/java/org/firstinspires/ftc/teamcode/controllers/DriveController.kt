package org.firstinspires.ftc.teamcode.controllers

import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.CRAWL_SPEED
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import org.firstinspires.ftc.teamcode.smoothInput

class DriveController(val gamepad1: Gamepad, val drivetrain: Drivetrain) : IController {

    override fun onLoop() {
        val boost = gamepad1.right_trigger.toDouble()
        val multiplier = 1.0 - Range.scale(boost, 0.0, 1.0, 0.0, 1.0 - CRAWL_SPEED)
        val throttle = smoothInput(gamepad1.left_stick_y.toDouble()) * multiplier
        val turn = smoothInput(gamepad1.left_stick_x.toDouble()) * multiplier * 1.1
        val strafe = smoothInput(gamepad1.right_stick_x.toDouble()) * multiplier

        drivetrain.holonomicDrive(throttle, strafe, turn)
    }
}