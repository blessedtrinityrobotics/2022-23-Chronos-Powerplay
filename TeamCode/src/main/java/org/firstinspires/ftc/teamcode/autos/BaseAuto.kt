package org.firstinspires.ftc.teamcode.autos

import com.acmerobotics.dashboard.FtcDashboard
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


abstract class BaseAuto: LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var claw: Claw
    lateinit var lift: Lift
    lateinit var webcam : OpenCvWebcam
    lateinit var pipeline : SignalSleevePipeline
    var zone = 0

    abstract fun auto()



    fun setUp() {
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
    }

    fun encoderDrive(power: Double, distanceInInches: Double){
        drivetrain.encoderReset()
        if (distanceInInches < 0){
            while (drivetrain.frontRight.currentPosition < (distanceInInches  * ((-550)/(4*Math.PI))) && opModeIsActive()){
                drivetrain.tankDrive(power,power)
            }
        } else if (distanceInInches > 0) {
            while (drivetrain.frontRight.currentPosition > (distanceInInches  * ((-550)/(4*Math.PI))) && opModeIsActive()){
                drivetrain.tankDrive(-power,-power)
            }
        }
        drivetrain.tankDrive(0.0,0.0)
        drivetrain.encoderReset()
    }

    fun timeByPower(power: Double, time: Long){
        drivetrain.tankDrive(-power,-power)
        sleep(time)
        drivetrain.tankDrive(0.0,0.0)

    }
    
    fun imuTurn(power: Double, angleInPi: Double){
        drivetrain.encoderReset()
        val oldAngle = drivetrain.imu.angle.firstAngle

        if(angleInPi < 0){
            while (drivetrain.imu.angle.firstAngle > (oldAngle + angleInPi) && opModeIsActive()){
                drivetrain.tankDrive(-power,power)
            }
            drivetrain.tankDrive(0.0,0.0)
        } else if(angleInPi > 0){
            while (drivetrain.imu.angle.firstAngle < (oldAngle + angleInPi) && opModeIsActive()){
                drivetrain.tankDrive(power,-power)
            }
            drivetrain.tankDrive(0.0,0.0)
        }
        drivetrain.encoderReset()
    }

    fun holonomicDriveEncoder(power: Double, distanceInTicks: Double){
        drivetrain.encoderReset()
        if (distanceInTicks < 0){
            while (drivetrain.frontRight.currentPosition > distanceInTicks && opModeIsActive()){
                drivetrain.holonomicDrive(0.0, -power, 0.0)
            }
        } else if(distanceInTicks > 0){
            while (drivetrain.frontRight.currentPosition < distanceInTicks && opModeIsActive()) {
                drivetrain.holonomicDrive(0.0, power, 0.0)
            }
        }
        drivetrain.holonomicDrive(0.0,0.0,0.0)
        drivetrain.encoderReset()
    }

    private fun park(zone :Int){
        drivetrain.encoderReset()
        encoderDrive(0.2, 7.0)

        if(zone == 1){
            while(drivetrain.imu.angle.firstAngle <  (LEFT_TURN - TURN_OFFSET)  && opModeIsActive()) {
                drivetrain.tankDrive(0.3,-0.3)
            }
            drivetrain.tankDrive(0.0,0.0)
            sleep(1000)
            drivetrain.encoderReset()
            while (drivetrain.frontRight.currentPosition > LEFT_ZONE_1 && opModeIsActive()){
                drivetrain.tankDrive(-0.3, -0.3)
            }
            drivetrain.tankDrive(0.0,0.0)

        } else if(zone == 3){
            while (drivetrain.imu.angle.firstAngle > (RIGHT_TURN + TURN_OFFSET) && opModeIsActive()){
                drivetrain.tankDrive(-0.3,0.3)
            }
            drivetrain.tankDrive(0.0,0.0)
            drivetrain.encoderReset()
            while (drivetrain.frontRight.currentPosition > LEFT_ZONE_3 && opModeIsActive()){
                drivetrain.tankDrive(-0.3, -0.3)
            }
            drivetrain.tankDrive(0.0,0.0)

        } else { // when Zone is two and it should do nothing

        }

    }


    override fun runOpMode(){
        setUp()
        auto()
        park(zone)
        stop()
    }

}