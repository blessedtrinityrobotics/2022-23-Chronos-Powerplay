package org.firstinspires.ftc.teamcode.autos.rrautos

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.SignalSleevePipeline
import org.firstinspires.ftc.teamcode.hardware.Claw
import org.firstinspires.ftc.teamcode.hardware.Lift
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvWebcam

abstract  class BasedRoadRunnerAuto: LinearOpMode() {
    lateinit var drive: SampleMecanumDrive
    lateinit var claw: Claw
    lateinit var lift: Lift
    lateinit var webcam : OpenCvWebcam
    lateinit var pipeline : SignalSleevePipeline
    var zone = 0
    var startPose = Pose2d(0.0, 0.0, 0.0)

    abstract fun auto()

    fun setUp() {
        drive = SampleMecanumDrive(hardwareMap)
        claw = Claw(hardwareMap)
        lift = Lift(hardwareMap)
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
    }

    override fun runOpMode() {
        setUp()
        claw.toggleGrab()
        waitForStart()
        auto()
    }

}