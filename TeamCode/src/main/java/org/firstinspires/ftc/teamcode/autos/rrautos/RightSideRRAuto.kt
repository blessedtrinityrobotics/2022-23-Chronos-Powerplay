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

        var getToFirstPole = drive.trajectorySequenceBuilder(Pose2d())
            .forward(10.0)
            .turn(Math.toRadians(45.0))
            .forward(1.0)
            .build()

        var goToSignalCone =drive.trajectorySequenceBuilder(Pose2d())
            .forward(-1.0)
            .turn(Math.toRadians(-63.0))
            .forward(8.0)
            .turn(Math.toRadians(15.0))
            .forward(4.0)
            .build()

        var pushAwaySignalCone = drive.trajectorySequenceBuilder(Pose2d())
            .forward(34.0)
            .waitSeconds(0.25)
            .forward(3.0)
            .waitSeconds(0.25)
            .forward(-3.0)
            .build()

        var getToConeStackFirstTime =drive.trajectorySequenceBuilder(Pose2d())
            .turn(Math.toRadians(-85.0))
            .forward(27.5)
            .build()

        var forward2In = drive.trajectorySequenceBuilder(Pose2d())
            .forward(2.0)
            .build()

        var goToMiddleJunction = drive.trajectorySequenceBuilder(Pose2d())
            .forward(-10.0)
            .turn(Math.toRadians(-180.0))
            .forward(20.0)
            .build()

        var goToStack = drive.trajectorySequenceBuilder(Pose2d())
            .forward(-17.0)
            .turn(Math.toRadians(141.5))
            .forward(10.0)
            .build()

        var parkingSetter = drive.trajectorySequenceBuilder(Pose2d())
            .forward(-2.0)
            .turn(Math.toRadians(15.0))
            .forward(8.5)
            .turn(Math.toRadians(-35.0))
            .build()

        var zoneOne = drive.trajectorySequenceBuilder(Pose2d())
            .forward(-15.0)
            .build()

        var zoneTwo = drive.trajectorySequenceBuilder(Pose2d())
            .forward(-5.0)
            .build()

        var zoneThree = drive.trajectorySequenceBuilder(Pose2d())
            .forward(15.0)
            .build()



        //places first cone
        lift.positionSetter(FIRST_CONE_HEIGHT)
        drive.followTrajectorySequence(getToFirstPole)
//        claw.toggleGrab()
        lift.positionSetter(0)

        //goes to Signal Cone and pushes it out of the way
        drive.followTrajectorySequence(goToSignalCone)
        zone = pipeline.zone
        drive.followTrajectorySequence(pushAwaySignalCone)

        //stacks first cone from the stack to the middle junction
        lift.positionSetter(FIRST_CONE_STACK_HEIGHT)
        drive.followTrajectorySequence(getToConeStackFirstTime)
//        claw.toggleGrab()
        sleep(1000)
        lift.positionSetter(MIDDLE_JUNCTION_HEIGHT)
        sleep(500)
        drive.followTrajectorySequence(goToMiddleJunction)
//        claw.toggleGrab()
        sleep(250)
        // parking time
        drive.followTrajectorySequence(parkingSetter)

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