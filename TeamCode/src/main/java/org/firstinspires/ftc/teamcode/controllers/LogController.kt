package org.firstinspires.ftc.teamcode.controllers

import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.hardware.Claw
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import org.firstinspires.ftc.teamcode.hardware.Lift

class LogController(val telemetry: Telemetry, val drivetrain: Drivetrain, val lift: Lift, val claw: Claw) : IController {
    override fun onLoop() {
        telemetry.addData("Lift Position", lift.position)
        telemetry.addData("Lift Target", lift.target)
        telemetry.addData("Is Touched?", lift.isTouched)
        telemetry.addData("Is grabbing?", claw.isGrabbing)
        telemetry.addData("Throttle %", drivetrain.driveVector.throttle)
        telemetry.addData("Turn %", drivetrain.driveVector.turn)
        telemetry.addData("Strafe %", drivetrain.driveVector.strafe)
        telemetry.update()
    }

}