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
        // Along with touch, we need to make sure the current pos is low, so accidental touches won't work
        if (touchSensor.isPressed && liftMotor.currentPosition < 1000) {
            encoderOffset = 0 // Since we are back to normal, we don't need the failsafe
            liftMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        }

        isTouched = touchSensor.isPressed

        // Only set target pos when joystick is out of neutral
        if (power != 0.0) {
            liftMotor.targetPosition = Range.clip(
                liftMotor.currentPosition + (1000 * power).toInt(), // Edit the target relative to the currentPosition for use with joysticks
                0 - encoderOffset, 4500) // Subtract the encoder offset so you can go down in a pinch
            liftMotor.mode = DcMotor.RunMode.RUN_TO_POSITION
        }

        liftMotor.power = 1.0 // Continually set this because we need power to reach target pos
    }

    fun positionSetter(pos :Int){
        liftMotor.mode = DcMotor.RunMode.RUN_TO_POSITION
        liftMotor.targetPosition = pos
        liftMotor.power = 1.0
    }

    fun reset() {
        liftMotor.targetPosition = 0
    }

    fun resetEncoder() {
        // Set it to a really high value so it can go down safely
        encoderOffset = 100000
    }
}