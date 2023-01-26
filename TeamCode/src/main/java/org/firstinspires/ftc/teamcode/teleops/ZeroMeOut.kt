package org.firstinspires.ftc.teamcode.teleops

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.hardware.Antenna

@TeleOp(name="XeroMeOut")
class ZeroMeOut: OpMode() {
    lateinit var antenna: Antenna


    override fun init() {
        antenna = Antenna(hardwareMap)
    }
    override fun loop() {
        antenna.zero()
    }

}