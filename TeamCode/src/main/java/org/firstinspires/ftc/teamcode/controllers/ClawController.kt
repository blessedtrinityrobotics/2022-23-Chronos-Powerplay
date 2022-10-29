package org.firstinspires.ftc.teamcode.controllers

import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.hardware.Claw

class ClawController(val gamepad2: Gamepad, val claw: Claw) : IController {
    var isClawButtonPressed = false

    override fun onLoop() {
        if (gamepad2.a && !isClawButtonPressed) {
            isClawButtonPressed = true
            claw.toggleGrab()
        }
        if (!gamepad2.a)
            isClawButtonPressed = false
    }
}