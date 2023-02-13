package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.ARM_LIFT
import org.firstinspires.ftc.teamcode.ARM_ROTATOR
import org.firstinspires.ftc.teamcode.ARM_STABILIZER
import org.firstinspires.ftc.teamcode.CLAW

class Arm (hardwareMap: HardwareMap) {
    var claw: Servo = hardwareMap.servo.get(CLAW)
    var armRotator: Servo = hardwareMap.servo.get(ARM_ROTATOR) //
    var armStabilizer: Servo = hardwareMap.servo.get(ARM_STABILIZER) //this servo is what controls the belt
    var armLift: Servo = hardwareMap.servo.get(ARM_LIFT) //this is what is connected to the lift

    var isGrabbing = false
        private set
    var isRotated = false
        private set

    var liftToggle = false
        private set

    init {
        claw.position = 0.075
        armLift.position = 0.9166666
        armStabilizer.position = 0.0
    }

    fun init(){
        armRotator.position = 0.0
        armStabilizer.position = 0.0
        armLift.position = 1.0
    }

    fun toggleGrab(){
        isGrabbing = !isGrabbing
        claw.position = if(isGrabbing) 0.35 else 0.075
    }

    fun toggleArmLiftPos(){
        liftToggle = !liftToggle
        isRotated = !isRotated

        armLift.position = if(liftToggle) 0.2 else 1.0
        armRotator.position = if(isRotated) 0.66666666 else 0.0

    }

    fun moveArmRotator(power: Double){
        if (power != 0.0) {
            armRotator.position = Range.clip(
                armRotator.position + (power/50.0), // Edit the target relative to the currentPosition for use with joysticks
                -1.0,
                1.0)
        }
    }

    fun moveArmStabilizer(power: Double){
        if (power != 0.0) {
            armStabilizer.position = Range.clip(
                armStabilizer.position + (power/50.0), // Edit the target relative to the currentPosition for use with joysticks
                0.0,
                1.0)
        }
    }

    fun moveArmLiftJoint(power: Double){
        if (power != 0.0) {
            armLift.position = Range.clip(
                armLift.position + (power/50.0), // Edit the target relative to the currentPosition for use with joysticks
                0.125,
                0.9172222222222)
        }

        if(armLift.position > 0.52) {
            armStabilizer.position = 1.09945 * (0.9172 - armLift.position) + 0.2922
            armRotator.position = 0.0
        } else {
            armStabilizer.position = 0.984557 * (0.52 - armLift.position) + 0.1144
            armRotator.position = 0.6666666
        }

    }

}


