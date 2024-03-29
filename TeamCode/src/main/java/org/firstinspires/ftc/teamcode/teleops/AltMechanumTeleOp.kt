package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.CRAWL_SPEED
import org.firstinspires.ftc.teamcode.smoothInput
@Disabled
@TeleOp(name="Jack's Mechanum (Trigger)")
class AltMechanumTeleOp : BaseTeleOp() {
    override fun drive() {
        val boost = gamepad1.right_trigger.toDouble()
        val multiplier = 1.0 - Range.scale(boost, 0.0, 1.0, 0.0, 1.0 - CRAWL_SPEED)
        val throttle = smoothInput(gamepad1.left_stick_y.toDouble()) * multiplier
        val turn = smoothInput(gamepad1.left_stick_x.toDouble()) * multiplier * 1.1
        val strafe = smoothInput(gamepad1.right_stick_x.toDouble()) * multiplier

        drivetrain.holonomicDrive(throttle, strafe, turn)
    }

}