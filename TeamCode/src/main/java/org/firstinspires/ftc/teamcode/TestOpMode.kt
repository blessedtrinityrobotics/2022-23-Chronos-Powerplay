package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.pow
import kotlin.math.sign

/*
* Use adb connect 192.168.43.1:5555
*
*/


@TeleOp(name="TestTeleOp")
class TestOpMode : OpMode() {
    lateinit var liftMotor: DcMotor

    lateinit var drivetrain: Drivetrain
    lateinit var claw: Claw

    val BOTTOM_LIFT_POS = -20
    val TOP_LIFT_POS = -4200


    override fun init() {
        drivetrain = Drivetrain(hardwareMap)
        claw = Claw(hardwareMap)

        liftMotor = hardwareMap.dcMotor.get("lift")
        liftMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        liftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }

    override fun loop() {
        var leftPower = gamepad1.left_stick_y.toDouble()
        var rightPower = gamepad1.left_stick_x.toDouble()

        drivetrain.arcadeDrive(leftPower.pow(2.0) * sign(leftPower), rightPower.pow(2.0) * sign(rightPower))

        lift(gamepad2.right_stick_y.toDouble())
        telemetry.addData("Lift Position", liftMotor.currentPosition)

        if (gamepad2.a && claw.canGrab) {
            claw.toggleGrab()
        }

        telemetry.addData("Is grabbing?", claw.isGrabbing)
        telemetry.addData("Left Power", drivetrain.leftPower)
        telemetry.addData("Right Power", drivetrain.rightPower)
        telemetry.update()
    }

    private fun lift(power: Double) {
        // TODO: Make the directions not cursed
        if ((sign(power) != 1.0 || liftMotor.currentPosition < BOTTOM_LIFT_POS)
            && (sign(power) != -1.0 || liftMotor.currentPosition > TOP_LIFT_POS)) {
            liftMotor.power = power
        } else {
            liftMotor.power = 0.0
        }
    }

}