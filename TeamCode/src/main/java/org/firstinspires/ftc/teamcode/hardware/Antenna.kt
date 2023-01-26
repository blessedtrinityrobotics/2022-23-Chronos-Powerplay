package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap
import org.firstinspires.ftc.teamcode.ANTENNA_LEFT
import org.firstinspires.ftc.teamcode.ANTENNA_RIGHT


class Antenna(val hardwareMap: HardwareMap) {
    var leftAntenna: Servo = hardwareMap.servo.get(ANTENNA_LEFT)
    var rightAntenna: Servo = hardwareMap.servo.get(ANTENNA_RIGHT)

    var isGrabbing = false
        private set

    init {
        leftAntenna.direction = Servo.Direction.REVERSE
    }

    fun zero(){
        leftAntenna.position = 0.0
        rightAntenna.position = 0.0
    }

    fun toggleGrab(){
        isGrabbing = !isGrabbing
        leftAntenna.position = if(isGrabbing) 0.0 else 0.7
        rightAntenna.position = if(isGrabbing) 0.0 else 0.7
    }

}