package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.LEFT_CLAW_SERVO
import org.firstinspires.ftc.teamcode.RIGHT_CLAW_SERVO

class Claw(val hardwareMap: HardwareMap) {
    var leftClaw: Servo = hardwareMap.servo.get(LEFT_CLAW_SERVO)
    var rightClaw: Servo = hardwareMap.servo.get(RIGHT_CLAW_SERVO)

    var isGrabbing = false
        private set

    init {
        leftClaw.direction = Servo.Direction.REVERSE
    }

    fun toggleGrab() {
        isGrabbing = !isGrabbing
        leftClaw.position = if (isGrabbing) 0.275 else 0.075
        rightClaw.position = if (isGrabbing) 0.275 else 0.075
    }
}