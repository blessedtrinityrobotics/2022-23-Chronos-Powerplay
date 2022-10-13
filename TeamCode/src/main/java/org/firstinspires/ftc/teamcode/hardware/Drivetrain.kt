package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.BACK_LEFT_MOTOR
import org.firstinspires.ftc.teamcode.BACK_RIGHT_MOTOR
import org.firstinspires.ftc.teamcode.FRONT_LEFT_MOTOR
import org.firstinspires.ftc.teamcode.FRONT_RIGHT_MOTOR
import kotlin.math.abs
import kotlin.math.max

data class DriveInfo(var throttle: Double, var strafe: Double, var turn: Double)

class Drivetrain(val hardwareMap: HardwareMap) {
    var driveVector = DriveInfo(0.0, 0.0, 0.0);

    var frontLeft: DcMotor = hardwareMap.dcMotor.get(FRONT_LEFT_MOTOR)
    var backLeft: DcMotor = hardwareMap.dcMotor.get(BACK_LEFT_MOTOR)
    var frontRight: DcMotor = hardwareMap.dcMotor.get(FRONT_RIGHT_MOTOR)
    var backRight: DcMotor = hardwareMap.dcMotor.get(BACK_RIGHT_MOTOR)

    init {
        frontRight.direction = DcMotorSimple.Direction.REVERSE
        backRight.direction = DcMotorSimple.Direction.REVERSE
    }

    fun tankDrive(left: Double, right: Double) {
        applyPower(left, right)
    }

    fun arcadeDrive(throttle: Double, steer: Double) {
        applyPower(
        Range.clip(throttle - steer, -1.0, 1.0),
        Range.clip(throttle + steer, -1.0, 1.0))
    }

    fun holonomicDrive(throttle: Double, strafe: Double, steer: Double) {
        val total = abs(throttle) + abs(strafe) + abs(steer)
        val scale = max(total,1.0)
        frontLeft.power = (throttle - strafe + steer) / scale
        backLeft.power = (throttle - strafe - steer) / scale
        frontRight.power = (throttle + strafe + steer) / scale
        backRight.power = (throttle + strafe - steer) / scale


        driveVector.throttle = throttle/total
        driveVector.turn = steer/total
        driveVector.strafe = strafe/total
    }

    private fun applyPower(leftPower: Double, rightPower:Double) {
        frontLeft.power = leftPower
        backLeft.power = leftPower

        frontRight.power = rightPower
        backRight.power = rightPower

        driveVector.turn = abs(leftPower - rightPower)/2.0 // Percentage difference
        driveVector.throttle = (leftPower + rightPower)/2.0 // Average
    }
}