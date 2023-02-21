package org.firstinspires.ftc.teamcode.hardware


import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.LEFT_SERVO_SETTER
import org.firstinspires.ftc.teamcode.RIGHT_SERVO_SETTER

class SetUpServo (private val hardwareMap: HardwareMap) {
    var leftSideServo: Servo = hardwareMap.servo.get(RIGHT_SERVO_SETTER)
    var rightSideServo: Servo = hardwareMap.servo.get(LEFT_SERVO_SETTER)

    init {
        rightSideServo.direction = Servo.Direction.REVERSE
    }

    fun zero(){
        leftSideServo.position = 0.118 // 0.118
        rightSideServo.position = 0.118
    }

    fun upRight(){
        leftSideServo.position = 0.453
        rightSideServo.position = 0.453
    }

    fun moveServo(power: Double){
        var pos = Range.clip(
            leftSideServo.position + (power/1000).toDouble(),
            0.0,
            1.0)

        leftSideServo.position = pos
        rightSideServo.position = pos

    }


}