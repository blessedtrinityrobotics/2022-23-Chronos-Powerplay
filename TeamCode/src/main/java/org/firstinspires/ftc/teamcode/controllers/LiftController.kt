package org.firstinspires.ftc.teamcode.controllers

import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.hardware.Lift
import org.firstinspires.ftc.teamcode.smoothInput

class LiftController(val gamepad2: Gamepad, val lift: Lift) : IController {
    override fun onLoop() {
        val input = smoothInput(-gamepad2.left_stick_y.toDouble())
        lift.move(input)

        if (gamepad2.y)
            lift.reset()

        if (gamepad2.x)
            lift.resetEncoder()
    }
}