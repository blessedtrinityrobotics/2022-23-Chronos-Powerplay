package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.smoothInput
import kotlin.math.pow
import kotlin.math.sign

@TeleOp(name="Tank TeleOp")
class TankTeleOp : BaseTeleOp() {
    override fun drive() {
        var leftPower = gamepad1.left_stick_y.toDouble()
        var rightPower = gamepad1.right_stick_y.toDouble()

        drivetrain.tankDrive(smoothInput(leftPower), smoothInput(rightPower))
    }

}