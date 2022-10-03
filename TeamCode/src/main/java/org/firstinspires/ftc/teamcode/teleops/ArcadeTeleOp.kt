package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.smoothInput
import kotlin.math.pow
import kotlin.math.sign

@TeleOp(name="Arcade TeleOp")
class ArcadeTeleOp : BaseTeleOp() {
    override fun drive() {
        var throttle = gamepad1.left_stick_y.toDouble()
        var steer = gamepad1.left_stick_x.toDouble()

        drivetrain.arcadeDrive(smoothInput(throttle), smoothInput(steer))
    }
}