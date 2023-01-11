package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence


@Autonomous(name="RR TEST Auto")

class TestAuto: LinearOpMode() {
    var drive = SampleMecanumDrive(hardwareMap)


    var startPose = Pose2d(0.0, 0.0, 0.0)

    override fun runOpMode() {

        waitForStart()

            drive.poseEstimate = startPose
            var trajSeq = drive.trajectorySequenceBuilder(startPose)
                .splineTo( Vector2d(10.0, 10.0), 0.0)



    }
}