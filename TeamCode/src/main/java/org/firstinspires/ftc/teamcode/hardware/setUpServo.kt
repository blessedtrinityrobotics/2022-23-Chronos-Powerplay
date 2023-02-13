package org.firstinspires.ftc.teamcode.hardware


import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.LEFT_SERVO_SETTER
import org.firstinspires.ftc.teamcode.RIGHT_SERVO_SETTER

class setUpServo (hardwareMap: HardwareMap) {
    var leftSideServo: Servo = hardwareMap.servo.get(RIGHT_SERVO_SETTER)
    var rightSideServo: Servo = hardwareMap.servo.get(LEFT_SERVO_SETTER)

    
    fun zero(){
        leftSideServo.position = 0.0
        rightSideServo.position = 0.0
    }

    fun upRight(){
        leftSideServo.position = 0.5
        rightSideServo.position = 0.5
    }


}