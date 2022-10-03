package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.BACK_LEFT_MOTOR
import org.firstinspires.ftc.teamcode.BACK_RIGHT_MOTOR
import org.firstinspires.ftc.teamcode.FRONT_LEFT_MOTOR
import org.firstinspires.ftc.teamcode.FRONT_RIGHT_MOTOR

class Drivetrain(val hardwareMap: HardwareMap) {
    var leftPower = 0.0
        private set
    var rightPower = 0.0
        private set

    var frontLeft: DcMotor = hardwareMap.dcMotor.get(FRONT_LEFT_MOTOR)
    var backLeft: DcMotor = hardwareMap.dcMotor.get(BACK_LEFT_MOTOR)
    var frontRight: DcMotor = hardwareMap.dcMotor.get(FRONT_RIGHT_MOTOR)
    var backRight: DcMotor = hardwareMap.dcMotor.get(BACK_RIGHT_MOTOR)

    init {
        frontRight.direction = DcMotorSimple.Direction.REVERSE
        backRight.direction = DcMotorSimple.Direction.REVERSE
    }

    fun tankDrive(left: Double, right: Double) {
        // in case we use this value seperately, like for telemetry, we need to expose it
        leftPower = left
        rightPower = right
        applyPower()
    }

    fun arcadeDrive(throttle: Double, steer: Double) {
        leftPower = Range.clip(throttle - steer, -1.0, 1.0)
        rightPower = Range.clip(throttle + steer, -1.0, 1.0)
        applyPower()
    }

    private fun applyPower() {
        frontLeft.power = leftPower
        backLeft.power = leftPower

        frontRight.power = rightPower
        backRight.power = rightPower
    }
}