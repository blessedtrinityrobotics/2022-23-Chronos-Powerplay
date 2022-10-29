package org.firstinspires.ftc.teamcode.autos

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.*
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import org.openftc.easyopencv.OpenCvCamera

import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvWebcam

/*
FTC Dashboard: http://192.168.43.1:8080/dash
 */

@Autonomous(name="Cam Test Auto")
class CamTestAuto : LinearOpMode() {
    lateinit var webcam : OpenCvWebcam
    lateinit var pipeline : SignalSleevePipeline

    override fun runOpMode() {
        pipeline = SignalSleevePipeline()

        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId",
            "id",
            hardwareMap.appContext.packageName
        )
        webcam = OpenCvCameraFactory.getInstance().createWebcam(
            hardwareMap.get(
                WebcamName::class.java, "Webcam 1"
            ), cameraMonitorViewId
        )
        webcam.setPipeline(pipeline)

        webcam.openCameraDeviceAsync(object : OpenCvCamera.AsyncCameraOpenListener {
            override fun onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT)
            }
            override fun onError(code: Int) {}
        })

        FtcDashboard.getInstance().startCameraStream(webcam, 30.0)

        waitForStart()

        while(opModeIsActive()) {
            telemetry.addData("Zone", pipeline.zone)
            telemetry.update()
        }

    }

}