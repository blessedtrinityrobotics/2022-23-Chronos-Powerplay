package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.smoothInput

@TeleOp(name="Jack's Mechanum (Bumper)")
class AltMechBumpTeleOp : BaseTeleOp() {
    override fun drive () {
        val multiplier = if (gamepad1.right_bumper) 0.4 else if (gamepad1.left_bumper) 0.2 else 1.0

        val throttle = smoothInput(gamepad1.left_stick_y.toDouble()) * multiplier
        val turn = smoothInput(gamepad1.left_stick_x.toDouble()) * multiplier * 1.1
        val strafe = smoothInput(gamepad1.right_stick_x.toDouble()) * multiplier

        drivetrain.holonomicDrive(throttle, strafe, turn)
    }
}