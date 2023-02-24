package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
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
            .lineTo(Vector2d(17.5,-4.25))
            .build()

        var getHighJunctionFirstTime = drive.trajectorySequenceBuilder(Pose2d(17.5, -4.25))
            .forward(39.0)
            .lineToSplineHeading(Pose2d(44.0, 7.0, Math.toRadians(45.0)))
            .build()

        var getToConeStackFirstTime = drive.trajectorySequenceBuilder(Pose2d(45.0,7.0, Math.toRadians(-45.0)))
            .lineToSplineHeading(Pose2d(49.0, 5.0, Math.toRadians(-90.0)))
            .back(22.0)
            .build()

        var cycleToJunction = drive.trajectorySequenceBuilder(Pose2d(49.0, 27.0, Math.toRadians(-90.0)))
            .lineToSplineHeading(Pose2d(51.0,13.5, Math.toRadians(-65.0)))
            .build()

        var cycleToConeStack = drive.trajectorySequenceBuilder(Pose2d(51.0, 13.5, Math.toRadians(-65.0)))
            .lineToSplineHeading(Pose2d(49.0,27.0, Math.toRadians(-90.0)))
            .build()

        var zoneOne = drive.trajectorySequenceBuilder(Pose2d(51.0,13.5, Math.toRadians(-65.0)))
            .lineToSplineHeading(Pose2d(49.0,27.0, Math.toRadians(-90.0)))
            .build()

        var zoneTwo = drive.trajectorySequenceBuilder(Pose2d(51.0,13.5, Math.toRadians(-65.0)))
            .lineToSplineHeading(Pose2d(49.0,5.0, Math.toRadians(-90.0)))
            .build()

        var zoneThree = drive.trajectorySequenceBuilder(Pose2d(51.0,13.5, Math.toRadians(-65.0)))
            .lineToSplineHeading(Pose2d(48.0,-20.0, Math.toRadians(-90.0)))
            .build()


//        arm.toggleGrab()


        drive.followTrajectorySequence(getToSignalCone)
//        zone = pipeline.zone
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(700)
        drive.followTrajectorySequence(getHighJunctionFirstTime)
//        arm.moveArmLiftJointToPos(820)
//        sleep(1500)
//        arm.moveArmLiftJointToPos(0)
//        lift.positionSetter(0)
//        drive.followTrajectorySequence(getToConeStackFirstTime)
//        sleep(500)
//
//
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(820)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(500)
//
//        lift.positionSetter(0)
//        arm.moveArmLiftJointToPos(0)
//        drive.followTrajectorySequence(cycleToConeStack)
//        sleep(1500)
//
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(820)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(500)
//
//        lift.positionSetter(0)
//        arm.moveArmLiftJointToPos(0)
//        drive.followTrajectorySequence(cycleToConeStack)
//        sleep(1500)
//
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(820)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(500)
//
//        lift.positionSetter(0)
//        arm.moveArmLiftJointToPos(0)
//        drive.followTrajectorySequence(cycleToConeStack)
//        sleep(1500)
//
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(820)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(500)

//        lift.positionSetter(0)
//        arm.moveArmLiftJointToPos(0)
//        drive.followTrajectorySequence(cycleToConeStack)
//        sleep(1500)
//
//        lift.positionSetter(2150)
//        arm.moveArmLiftJointToPos(820)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(500)



//        sleep(500)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(500)
//        drive.followTrajectorySequence(cycleToConeStack)
//        sleep(500)
//        drive.followTrajectorySequence(cycleToJunction)
//        sleep(500)
//        drive.followTrajectorySequence(cycleToConeStack)
//        sleep(500)
//        drive.followTrajectorySequence(cycleToJunction)
//        for (i in 1..5){
//
//        }

//        arm.moveArmLiftJointToPos(0)
//        lift.positionSetter(0)
//        sleep(500)
//        if(zone == 1){
//            drive.followTrajectorySequence(zoneOne)
//        } else if(zone == 2){
//            drive.followTrajectorySequence(zoneTwo)
//        } else if (zone == 3) {
//            drive.followTrajectorySequence(zoneThree)
//        }




        while (opModeIsActive()){
            telemetry.addData("x", drive.poseEstimate.x)
            telemetry.addData("y", drive.poseEstimate.y)
            telemetry.addData("heading", drive.poseEstimate.heading)
            telemetry.addData("Zone", zone)
            telemetry.update()
        }


    }

}