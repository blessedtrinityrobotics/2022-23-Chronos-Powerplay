package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.FIRST_CONE_HEIGHT
import org.firstinspires.ftc.teamcode.FIRST_CONE_STACK_HEIGHT
import org.firstinspires.ftc.teamcode.MIDDLE_JUNCTION_HEIGHT

@Autonomous(name="RightSideRRAuto")
class RightSideRRAuto: BasedRoadRunnerAuto() {




    override fun auto() {
        lift.reset()
        drive.poseEstimate = startPose
        var getToSignalCone =  drive.trajectorySequenceBuilder(Pose2d())
            .forward(18.0)
            .build()

        var getHighJunctionFirstTime = drive.trajectorySequenceBuilder(Pose2d())
            .forward(35.0)
            .back(10.0)
            .turn(Math.toRadians(45.0))
            .build()

        var getToConeStackFirstTime = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(3.0, 0.0, Math.toRadians(95.0)))
            .back(20.0)
            .build()

        var cycleToJunction = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,22.5, Math.toRadians(65.0)))
            .build()

        var cycleToConeStack = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,-20.5, Math.toRadians(94.0)))
            .build()

        var zoneOne = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,22.0, Math.toRadians(90.0)))
            .build()

        var zoneThree = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,-22.5, Math.toRadians(0.0)))
            .build()

        // gets to signal cone
        drive.followTrajectorySequence(getToSignalCone)
//        zone = pipeline.zone
        sleep(1500)
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(890)
        drive.followTrajectorySequence(getHighJunctionFirstTime)
//        arm.moveArmLiftJointToPos(0)
//        lift.positionSetter(0)
        sleep(1500)
        drive.followTrajectorySequence(getToConeStackFirstTime)
        sleep(1500)
        drive.followTrajectorySequence(cycleToJunction)
        sleep(1500)
        drive.followTrajectorySequence(cycleToConeStack)

//        if(zone == 1){
//            drive.followTrajectorySequence(zoneOne)
//        } else if (zone == 3) {
//            drive.followTrajectorySequence(zoneThree)
//        } else {
//            // this would be zone two and it does nothing
//        }




        while (opModeIsActive()){
            telemetry.addData("Zone", zone)
            telemetry.update()
        }


    }

}