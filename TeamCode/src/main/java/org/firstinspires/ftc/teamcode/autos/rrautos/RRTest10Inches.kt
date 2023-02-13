package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive


@Autonomous(name="RR 10 TEST Auto")

class RRTest10Inches: LinearOpMode() {
    lateinit var drive: SampleMecanumDrive



    var startPose = Pose2d(0.0, 0.0, 0.0)

    override fun runOpMode() {
        drive = SampleMecanumDrive(hardwareMap)

        drive.poseEstimate = startPose
        var trajSeq = drive.trajectorySequenceBuilder(Pose2d())
            .forward(10.0)
            .build()

        waitForStart()

        while(opModeIsActive()){
            drive.followTrajectorySequence(trajSeq)

            while (opModeIsActive()){

                drive.update()
                val (x, y, heading) = drive.poseEstimate
                telemetry.addData("x", x)
                telemetry.addData("y", y)
                telemetry.addData("heading", heading)
                telemetry.update()
            }
        }



    }
}