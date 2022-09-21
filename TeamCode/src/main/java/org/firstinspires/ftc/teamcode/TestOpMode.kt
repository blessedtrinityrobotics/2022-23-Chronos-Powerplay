package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp(name="Test TeleOp")
class TestOpMode : OpMode() {
    lateinit var frontLeftMotor: DcMotor
    lateinit var backLeftMotor: DcMotor
    lateinit var frontRightMotor: DcMotor
    lateinit var backRightMotor: DcMotor
    lateinit var liftMotor: DcMotor


    override fun init() {
        frontLeftMotor = hardwareMap.dcMotor.get("front_left")
        backLeftMotor = hardwareMap.dcMotor.get("back_left")
        frontRightMotor = hardwareMap.dcMotor.get("front_right")
        backRightMotor = hardwareMap.dcMotor.get("back_right")
        frontRightMotor.direction = DcMotorSimple.Direction.REVERSE
        backRightMotor.direction = DcMotorSimple.Direction.REVERSE
        liftMotor = hardwareMap.dcMotor.get("lift")
    }

    override fun loop() {
        var leftPower = gamepad1.left_stick_y
        var rightPower = gamepad1.right_stick_y

        frontLeftMotor.power = leftPower.toDouble()
        backLeftMotor.power = leftPower.toDouble()

        frontRightMotor.power = rightPower.toDouble()
        backRightMotor.power = rightPower.toDouble()

        liftMotor.power = if (gamepad1.dpad_up) 0.25 else if (gamepad1.dpad_down) -0.25 else 0.0
    }

}