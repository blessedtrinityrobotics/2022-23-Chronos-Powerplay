package org.firstinspires.ftc.teamcode.autos

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.FIRST_CONE_HEIGHT

@Autonomous(name="RIGHT AUTO")
class RightSideAuto: BaseAuto() {

    override fun auto() {
        lift.reset()
        claw.toggleGrab()
        waitForStart()
        holonomicDriveEncoder(0.25, -425.0)
        lift.positionSetter(FIRST_CONE_HEIGHT)
        encoderDrive(0.15,6.0)
        claw.toggleGrab()
        lift.positionSetter(0)
        encoderDrive(0.3,-8.0)
        holonomicDriveEncoder(0.25, 650.0)
        timeByPower(-0.5,1000)
        encoderDrive(0.3,22.0)
        sleep(1000)
        zone = pipeline.zone
        encoderDrive(0.15,15.0)
        encoderDrive(0.15,-15.0)
    }

}