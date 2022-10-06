package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.smoothInput

@TeleOp(name="Mechanum TeleOp")
class MechanumTeleOp : BaseTeleOp() {
    override fun drive() {
        val throttle = gamepad1.left_stick_y.toDouble()
        val strafe = gamepad1.left_stick_x.toDouble()
        val turn = gamepad1.right_stick_x.toDouble()

        drivetrain.holonomicDrive(smoothInput(throttle), smoothInput(strafe) * 1.1, smoothInput(turn))
    }

}