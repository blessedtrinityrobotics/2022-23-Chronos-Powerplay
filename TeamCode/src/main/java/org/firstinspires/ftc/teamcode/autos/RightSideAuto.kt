package org.firstinspires.ftc.teamcode.autos

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.*
import org.firstinspires.ftc.teamcode.hardware.Claw
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import org.firstinspires.ftc.teamcode.hardware.Lift
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvWebcam

@Autonomous(name="Right Side Auto")
class RightSideAuto : LinearOpMode() {
    lateinit var webcam : OpenCvWebcam
    lateinit var pipeline : SignalSleevePipeline
    lateinit var drivetrain: Drivetrain
    lateinit var claw: Claw
    lateinit var lift: Lift

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap)
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

        claw.toggleGrab() //closes on the cone on init

        waitForStart()

        while (opModeIsActive()) {

            scoreFirstCone()
            recenterToTargetCone()
            getToTargetCone()
            sleep(1000)
            val zone = pipeline.zone
            scoreTargetCone()
            sleep(1000)
            park(zone)

            while (opModeIsActive()){
                telemetry.addData("ZONE:", zone)
                telemetry.update()
            }

            stop()
        }

    }

    private fun scoreFirstCone(){
        drivetrain.encoderReset()
        lift.reset()
        lift.positionSetter(FIRST_CONE_HEIGHT) // will find out later
        sleep(1000)
        while (drivetrain.frontRight.currentPosition > FIRST_POLE_DISTANCE && opModeIsActive()){
            drivetrain.tankDrive(-0.2,-0.2)
        }
        drivetrain.tankDrive(0.0,0.0)
        claw.toggleGrab()
        sleep(1000)
        lift.positionSetter(0)
        while (drivetrain.frontRight.currentPosition < 0 && opModeIsActive()) {
            drivetrain.tankDrive(0.3,0.3)
        }
        drivetrain.tankDrive(0.0,0.0)
        drivetrain.encoderReset()
    }

    private fun recenterToTargetCone(){
        while (drivetrain.frontRight.currentPosition < RIGHT_STRAFE_AUTO && opModeIsActive()) {
            drivetrain.holonomicDrive(0.0, 0.2, 0.0)
        }
        drivetrain.holonomicDrive(0.0,0.0,0.0)
        drivetrain.tankDrive(0.2,0.3)
        sleep(1000)
        drivetrain.tankDrive(0.0,0.0)
        drivetrain.encoderReset()
    }

    private fun getToTargetCone(){
        while ( drivetrain.frontRight.currentPosition > AUTO_CONE_DISTANCE && opModeIsActive()){
            drivetrain.tankDrive(-0.3,-0.3)
        }
        drivetrain.tankDrive(0.0,0.0)
    }

    private fun scoreTargetCone(){
        claw.toggleGrab()

        val baseAngle = drivetrain.imu.angle.firstAngle
        while(drivetrain.imu.angle.firstAngle < (baseAngle + RIGHT_TARGET_CONE_ANGLE) && opModeIsActive()){
            drivetrain.tankDrive(0.2,-0.2)
        }
        drivetrain.tankDrive(0.0,0.0)
        drivetrain.encoderReset()
        sleep(1000)
        lift.positionSetter(SECOND_POLE_HEIGHT)
        sleep(1000)
        while(drivetrain.frontRight.currentPosition > SECOND_POLE_DISTANCE && opModeIsActive()){
            drivetrain.tankDrive(-0.2,-0.2)
        }
        drivetrain.tankDrive(0.0,0.0)
        drivetrain.encoderReset()
        sleep(1000)
        claw.toggleGrab()
        lift.positionSetter(0)

        while (drivetrain.frontRight.currentPosition < CENTER_PARKING_POS && opModeIsActive()){
            drivetrain.tankDrive(0.2,0.2)
        }
        drivetrain.tankDrive(0.0,0.0)

        val oldAngle = drivetrain.imu.angle.firstAngle
        while (drivetrain.imu.angle.firstAngle < (oldAngle + RIGHT_PARKING_ANGLE) && opModeIsActive()){
            drivetrain.tankDrive(-0.2,0.2)
        }
        drivetrain.tankDrive(0.0,0.0)
        drivetrain.encoderReset()
    }

    private fun park(zone :Int){

        if(zone == 1){
            while(drivetrain.imu.angle.firstAngle < (LEFT_TURN - TURN_OFFSET)  && opModeIsActive()) {
                drivetrain.tankDrive(0.3,-0.3)
            }
            drivetrain.tankDrive(0.0,0.0)
            sleep(1000)
            drivetrain.encoderReset()
            while (drivetrain.frontRight.currentPosition > RIGHT_ZONE_1 && opModeIsActive()){
                drivetrain.tankDrive(-0.3, -0.3)
            }
            drivetrain.tankDrive(0.0,0.0)

         } else if(zone == 3){
             while (drivetrain.imu.angle.firstAngle > (RIGHT_TURN + TURN_OFFSET)&& opModeIsActive()){
                 drivetrain.tankDrive(-0.3,0.3)
             }
            drivetrain.tankDrive(0.0,0.0)
            drivetrain.encoderReset()
            while (drivetrain.frontRight.currentPosition > RIGHT_ZONE_3 && opModeIsActive()){
                drivetrain.tankDrive(-0.3, -0.3)
            }
            drivetrain.tankDrive(0.0,0.0)

            } else { // when Zone is two and it should do nothing

            }
        }
}