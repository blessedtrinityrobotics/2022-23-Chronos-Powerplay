package org.firstinspires.ftc.teamcode.autos

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled

@Disabled
@Autonomous(name="Test Auto for based auto")
class TestAuto: BaseAuto() {

    override fun auto() {

        encoderDrive(0.5,-2.0)
        sleep(5000)
        encoderDrive(0.5, 2.0)
        sleep(1000)
        imuTurn(0.5, -1.0)
        sleep(1000)
        imuTurn(0.5, 1.0)
        sleep(1000)
        holonomicDriveEncoder(0.5, -500.0)
        sleep(1000)
        holonomicDriveEncoder(0.5, 500.0)


    }


}