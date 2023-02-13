package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.FIRST_CONE_HEIGHT
import org.firstinspires.ftc.teamcode.FIRST_CONE_STACK_HEIGHT
import org.firstinspires.ftc.teamcode.MIDDLE_JUNCTION_HEIGHT

@Autonomous(name="LeftSideRRAuto")
class LeftSideRRAuto: BasedRoadRunnerAuto() {




    override fun auto() {
        lift.reset()
        drive.poseEstimate = startPose

        var getToFirstPole = drive.trajectorySequenceBuilder(Pose2d())
            .forward(8.0)
            .turn(Math.toRadians(-30.0))
            .forward(2.0)
            .build()

        var getToSignalCone =  drive.trajectorySequenceBuilder(Pose2d())
            .forward(21.0)
            .build()

        var getHighJunctionFirstTime = drive.trajectorySequenceBuilder(Pose2d())
            .lineToSplineHeading(Pose2d(10.0,0.0, Math.toRadians(90.0)))
            .build()


        // gets to signal cone
        drive.followTrajectorySequence(getToSignalCone)
        sleep(1500)
        drive.followTrajectorySequence(getHighJunctionFirstTime)

        //places first cone




        //goes to Signal Cone and pushes it out of the way
//        drive.followTrajectorySequence(goToSignalCone)
//        zone = pipeline.zone
//        drive.followTrajectorySequence(pushAwaySignalCone)
//
//        //stacks first cone from the stack to the middle junction
//        lift.positionSetter(FIRST_CONE_STACK_HEIGHT)
//        drive.followTrajectorySequence(getToConeStackFirstTime)
//        claw.toggleGrab()
//        sleep(1000)
//        lift.positionSetter(MIDDLE_JUNCTION_HEIGHT)
//        sleep(500)
//        drive.followTrajectorySequence(goToMiddleJunction)
//        claw.toggleGrab()
//        sleep(250)
//        lift.positionSetter(0)
//
//        // parking time
//        drive.followTrajectorySequence(parkingSetter)

//        if(zone == 1){
//            drive.followTrajectorySequence(zoneOne)
//        } else if (zone == 3){
//            drive.followTrajectorySequence(zoneThree)
//        } else { //is zone two
//            drive.followTrajectorySequence(zoneTwo)
//        }



        //stacks second cone from the stack to the middle junction
//        lift.positionSetter()
//        drive.followTrajectorySequence(goToStack)
//        sleep(1000)
//        claw.toggleGrab()
//        lift.positionSetter(MIDDLE_JUNCTION_HEIGHT)

        while (opModeIsActive()){
            telemetry.addData("Zone", zone)
            telemetry.update()
        }


    }

}