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

@Autonomous(name="Parking Auto")
class ParkingAuto : LinearOpMode() {
    lateinit var webcam : OpenCvWebcam
    lateinit var pipeline : SignalSleevePipeline
    lateinit var drivetrain: Drivetrain

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap)

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

        while (opModeIsActive()) {

            getToCone()
            sleep(1000)
            val zone = pipeline.zone
            park(zone)

            stop()
        }

    }

    private fun getToCone(){
        while ( drivetrain.frontRight.currentPosition > AUTO_CONE_DISTANCE && opModeIsActive()){
            drivetrain.tankDrive(-0.3,-0.3)
        }
        drivetrain.tankDrive(0.0,0.0)
    }

    private fun park(zone :Int){
        while ( drivetrain.frontRight.currentPosition > AUTO_SET_DISTANCE && opModeIsActive()){
            drivetrain.tankDrive(-0.3,-0.3)
        }
        drivetrain.tankDrive(0.0,0.0)

        sleep(1000)

        if(zone == 1){
            while(drivetrain.imu.angle.firstAngle < LEFT_TURN  && opModeIsActive()) {
                drivetrain.tankDrive(0.3,-0.3)
            }
            drivetrain.tankDrive(0.0,0.0)
            sleep(1000)
            drivetrain.encoderReset()
            while (drivetrain.frontRight.currentPosition > AUTO_PARK_DISTANCE && opModeIsActive()){
                drivetrain.tankDrive(-0.3, -0.3)
            }
            drivetrain.tankDrive(0.0,0.0)

        } else if(zone == 3){
            while (drivetrain.imu.angle.firstAngle > RIGHT_TURN && opModeIsActive()){
                drivetrain.tankDrive(-0.3,0.3)
            }
            drivetrain.tankDrive(0.0,0.0)
            drivetrain.encoderReset()
            while (drivetrain.frontRight.currentPosition > AUTO_PARK_DISTANCE && opModeIsActive()){
                drivetrain.tankDrive(-0.3, -0.3)
            }
            drivetrain.tankDrive(0.0,0.0)

        } else { // when Zone is two and it should do nothing

        }

    }

}