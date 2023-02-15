package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.hardware.*
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "setUpServoTestFile")
class setUpServoTestFile: OpMode() {

    lateinit var setUpServo: SetUpServo

    override fun init() {
       setUpServo = SetUpServo(hardwareMap)
    }


    override fun loop() {
        if (gamepad2.a){
            setUpServo.zero()
        }
        else if (gamepad2.b){
            setUpServo.upRight()
        }

        telemetry.addData("Servo Pos", setUpServo.leftSideServo.position)
        telemetry.update()
    }
}