package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.hardware.Claw
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import org.firstinspires.ftc.teamcode.hardware.Lift
import org.firstinspires.ftc.teamcode.smoothInput
import kotlin.math.sign


abstract class BaseTeleOp : OpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var claw: Claw
    lateinit var lift: Lift

    var isClawButtonPressed = false

    abstract fun drive()

    override fun init() {
        drivetrain = Drivetrain(hardwareMap)
        claw = Claw(hardwareMap)
        lift = Lift(hardwareMap)
    }

    override fun loop() {
        drive()

        lift.move(smoothInput(-gamepad2.left_stick_y.toDouble()))

        if (gamepad2.a && !isClawButtonPressed) {
            isClawButtonPressed = true
            claw.toggleGrab()
        }
        if (!gamepad2.a)
            isClawButtonPressed = false

        if (gamepad2.y)
            lift.reset()

        if (gamepad2.x)
            lift.resetEncoder()

        telemetry.addData("Angle 1", drivetrain.imu.angle.firstAngle)
        telemetry.addData("Angle 2", drivetrain.imu.angle.secondAngle)
        telemetry.addData("Angle 3", drivetrain.imu.angle.thirdAngle)

        telemetry.addData("Lift Position", lift.position)
        telemetry.addData("Lift Target", lift.target)
        telemetry.addData("Is Touched?", lift.isTouched)
        telemetry.addData("Is grabbing?", claw.isGrabbing)
        telemetry.addData("Throttle %", drivetrain.driveVector.throttle)
        telemetry.addData("Turn %", drivetrain.driveVector.turn)
        telemetry.addData("Strafe %", drivetrain.driveVector.strafe)

        telemetry.addData("rightPos", drivetrain.frontRight.currentPosition)
        telemetry.update()
    }

}