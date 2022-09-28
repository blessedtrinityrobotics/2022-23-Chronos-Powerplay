package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime

class Claw(val hardwareMap: HardwareMap) {
    var leftClaw: Servo = hardwareMap.servo.get(LEFT_CLAW_SERVO)
    var rightClaw: Servo = hardwareMap.servo.get(RIGHT_CLAW_SERVO)

    var elapsedTime = ElapsedTime()

    // It can grab only if enough time has passed since it has last been grabbed
    // This is because it only uses one button to toggle
    var canGrab = false
        get() = elapsedTime.milliseconds() >= GRABBER_DEBOUNCE_TIME

    var isGrabbing = false
        private set

    init {
        leftClaw.direction = Servo.Direction.REVERSE
    }

    fun toggleGrab() {
        isGrabbing = !isGrabbing
        leftClaw.position = if (isGrabbing) 0.25 else 0.0
        rightClaw.position = if (isGrabbing) 0.25 else 0.0
        // reset the timer since it is used by the can grab
        elapsedTime.reset()
    }
}