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
    var armLift: DcMotor = hardwareMap.dcMotor.get(ARM_LIFT) //this is what is connected to the lift

    var isGrabbing = false
        private set
    var isRotated = false
        private set

    var liftToggle = false
        private set

    init {
        armLift.targetPosition = 0
        armLift.mode = DcMotor.RunMode.RUN_TO_POSITION
        armLift.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun init(){
        claw.position = 0.075
        armRotator.position = 0.05
        armStabilizer.position = 0.2
    }

    fun toggleGrab(){
        isGrabbing = !isGrabbing
        claw.position = if(isGrabbing) 0.35 else 0.075
    }

    fun toggleArmLiftPos(){
        liftToggle = !liftToggle
        isRotated = !isRotated

//        armLift.position = if(liftToggle) 0.2 else 1.0
        armRotator.position = if(isRotated) 0.66666666 else 0.0

    }

    fun moveArmRotator(power: Double){
        if (power != 0.0) {
            armRotator.position = Range.clip(
                armRotator.position + (power/50.0), // Edit the target relative to the currentPosition for use with joysticks
                0.0,
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

            armLift.targetPosition = Range.clip(
                armLift.currentPosition + (power * 100).toInt(), // Edit the target relative to the currentPosition for use with joysticks
                0,
                1070)
            armLift.mode = DcMotor.RunMode.RUN_TO_POSITION
        }
        armLift.power = 0.2

        if(armLift.currentPosition > 520) {
            armStabilizer.position = -.00087696 * (1070 - armLift.currentPosition) + 0.879
            armRotator.position = 0.7
        } else {
            armStabilizer.position = -.0008547 * (520 - armLift.currentPosition) + 1.0
            armRotator.position = 0.05
        }

    }

    fun moveArmLiftJointToPos(pos: Int){
        armLift.targetPosition = Range.clip(
        pos, // Edit the target relative to the currentPosition for use with joysticks
        0,
        1070)
        armLift.mode = DcMotor.RunMode.RUN_TO_POSITION
        armLift.power = 0.2

        if (armLift.targetPosition > 520){
            armStabilizer.position = 0.95
            armRotator.position = 0.7
        } else {
            armStabilizer.position = -.0008547 * (520 - armLift.targetPosition) + 1.0
            armRotator.position = 0.05
        }
    }

}


