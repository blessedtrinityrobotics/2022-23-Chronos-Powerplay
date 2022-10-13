package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.TouchSensor
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.LIFT_MOTOR
import org.firstinspires.ftc.teamcode.LIFT_TOUCH_SENSOR
import kotlin.math.sign

class Lift(private val hardwareMap: HardwareMap) {
    var liftMotor: DcMotor = hardwareMap.dcMotor.get(LIFT_MOTOR)
    var touchSensor: TouchSensor = hardwareMap.touchSensor.get(LIFT_TOUCH_SENSOR)

    var relativeZeroPosition = 0

    var position = 0
        get() = liftMotor.currentPosition - relativeZeroPosition

    var target = 0
        get() = liftMotor.targetPosition

    var isTouched = false
        private set

    var encoderOffset = 0

    init {
        liftMotor.direction = DcMotorSimple.Direction.REVERSE
        liftMotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        liftMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
    }

    fun move(power: Double) {
        if (touchSensor.isPressed) {
            encoderOffset = 0
            liftMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        }

        isTouched = touchSensor.isPressed

        if (power != 0.0) {
            liftMotor.targetPosition = Range.clip(liftMotor.currentPosition + (1000 * power).toInt(), 0 - encoderOffset, 4500)
            liftMotor.mode = DcMotor.RunMode.RUN_TO_POSITION
        }

        liftMotor.power = 1.0
    }

    fun reset() {
        liftMotor.targetPosition = 0
    }

    fun resetEncoder() {
        encoderOffset = -1000
    }
}