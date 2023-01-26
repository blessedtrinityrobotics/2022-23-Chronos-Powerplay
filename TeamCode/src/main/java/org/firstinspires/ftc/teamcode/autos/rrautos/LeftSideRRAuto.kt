package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.FIRST_CONE_HEIGHT

@Autonomous(name="LeftSideRRAuto")
class LeftSideRRAuto: BasedRoadRunnerAuto() {




    override fun auto() {
        lift.reset()
        drive.poseEstimate = startPose
        var trajSeq1 = drive.trajectorySequenceBuilder(Pose2d())
            .forward(10.0)
            .turn(Math.toRadians(-32.0))
            .build()

        var trarSeq2 =drive.trajectorySequenceBuilder(Pose2d())
            .forward(2.0)
            .build()

        var trarSeq3 =drive.trajectorySequenceBuilder(Pose2d())
            .forward(-2.0)
            .build()



        lift.positionSetter(FIRST_CONE_HEIGHT)
        drive.followTrajectorySequence(trajSeq1)
        drive.followTrajectorySequence(trarSeq2)
        drive.followTrajectorySequence(trarSeq3)
        claw.toggleGrab()
        lift.positionSetter(0)


    }

}