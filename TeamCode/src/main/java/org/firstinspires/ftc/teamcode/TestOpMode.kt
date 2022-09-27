package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.pow
import kotlin.math.sign

/*
* Use adb connect 192.168.43.1:5555
*
*/


@TeleOp(name="TestTeleOp")
class TestOpMode : OpMode() {
    lateinit var frontLeftMotor: DcMotor
    lateinit var backLeftMotor: DcMotor
    lateinit var frontRightMotor: DcMotor
    lateinit var backRightMotor: DcMotor
    lateinit var liftMotor: DcMotor
    lateinit var leftClaw: Servo
    lateinit var rightClaw: Servo
    var elapsedtime = ElapsedTime()
    var isGrabbing = false
    var grabDebounce = false

    val BOTTOM_LIFT_POS = -20
    val TOP_LIFT_POS = -4200


    override fun init() {
        frontLeftMotor = hardwareMap.dcMotor.get("front_left")
        backLeftMotor = hardwareMap.dcMotor.get("back_left")
        frontRightMotor = hardwareMap.dcMotor.get("front_right")
        backRightMotor = hardwareMap.dcMotor.get("back_right")
        frontRightMotor.direction = DcMotorSimple.Direction.REVERSE
        backRightMotor.direction = DcMotorSimple.Direction.REVERSE

        liftMotor = hardwareMap.dcMotor.get("lift")
        liftMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        liftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER


        leftClaw = hardwareMap.servo.get("left_claw")
        leftClaw.direction = Servo.Direction.REVERSE
        rightClaw = hardwareMap.servo.get("right_claw")

        elapsedtime.reset()
    }

    override fun loop() {
        var leftPower = gamepad1.left_stick_y.toDouble().pow(2.0)
        var rightPower = gamepad1.right_stick_y.toDouble().pow(2.0)

        frontLeftMotor.power = leftPower
        backLeftMotor.power = leftPower

        frontRightMotor.power = rightPower.toDouble()
        backRightMotor.power = rightPower.toDouble()

        lift(gamepad2.right_stick_y.toDouble())
        telemetry.addData("Is grabbing?", isGrabbing)
        telemetry.addData("Lift Position", liftMotor.currentPosition)
        telemetry.update()

        if (gamepad1.a && !grabDebounce) {
            grabDebounce = true
            isGrabbing = !isGrabbing
            leftClaw.position = if (isGrabbing) 0.25 else 0.0
            rightClaw.position = if (isGrabbing) 0.25 else 0.0
            Thread.sleep(1000)
            grabDebounce = false
        }

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