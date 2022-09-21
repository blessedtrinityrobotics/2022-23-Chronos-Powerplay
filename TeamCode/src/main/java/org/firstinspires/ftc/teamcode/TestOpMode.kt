package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp(name="Test TeleOp")
class TestOpMode : OpMode() {
    lateinit var frontLeftMotor: DcMotor
    lateinit var backLeftMotor: DcMotor
    lateinit var frontRightMotor: DcMotor
    lateinit var backRightMotor: DcMotor



    override fun init() {
        frontLeftMotor = hardwareMap.dcMotor.get("front_left")
        backLeftMotor = hardwareMap.dcMotor.get("back_left")
        frontRightMotor = hardwareMap.dcMotor.get("front_right")
        backRightMotor = hardwareMap.dcMotor.get("back_right")
    }

    override fun loop() {
        var leftPower = gamepad1.left_stick_y
        var rightPower = gamepad1.right_stick_y

        frontLeftMotor.power = leftPower.toDouble()
        backLeftMotor.power = leftPower.toDouble()

        frontRightMotor.power = rightPower.toDouble()
        backRightMotor.power = rightPower.toDouble()
    }

}