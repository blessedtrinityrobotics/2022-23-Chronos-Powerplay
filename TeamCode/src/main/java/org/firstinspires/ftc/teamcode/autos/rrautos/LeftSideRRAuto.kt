package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous


@Autonomous(name="LeftSideRRAuto")
class LeftSideRRAuto: BasedRoadRunnerAuto() {

// armLiftJointPos = 870 is horizontal


    override fun auto() {
        lift.reset()
        drive.poseEstimate = startPose

        var getToFirstPole = drive.trajectorySequenceBuilder(Pose2d())
            .forward(8.0)
            .turn(Math.toRadians(-30.0))
            .forward(2.0)
            .build()

        var getToSignalCone =  drive.trajectorySequenceBuilder(Pose2d())
//            .forward(18.0)
            .lineTo(Vector2d(17.5,4.25))
            .build()

        var getHighJunctionFirstTime = drive.trajectorySequenceBuilder(Pose2d(17.5, 4.25))
            .forward(39.0)
            .lineToSplineHeading(Pose2d(47.0, 0.0, Math.toRadians(-45.0)))
            .build()

        var getToConeStackFirstTime = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(3.0, 0.0, Math.toRadians(-95.0)))
            .back(20.0)
            .build()

        var cycleToJunction = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,-22.5, Math.toRadians(-65.0)))
            .build()

        var cycleToConeStack = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,20.5, Math.toRadians(-94.0)))
            .build()

        var zoneOne = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,22.0, Math.toRadians(-90.0)))
            .build()

        var zoneThree = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(0.0,-22.5, Math.toRadians(0.0)))
            .build()

        // gets to signal cone
        drive.followTrajectorySequence(getToSignalCone)
//        zone = pipeline.zone
        sleep(1500)
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(820)
//        drive.followTrajectorySequence(getHighJunctionFirstTime)
//        arm.moveArmLiftJointToPos(0)
//        lift.positionSetter(0)
//        sleep(1500)
//        drive.followTrajectorySequence(getToConeStackFirstTime)
//        sleep(1500)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(1500)
//        drive.followTrajectorySequence(cycleToConeStack)

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