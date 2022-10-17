package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.Orientation

class IMU(private val hardwareMap: HardwareMap) {
    var imu: BNO055IMU = hardwareMap.get(BNO055IMU::class.java,"imu")
    var params = BNO055IMU.Parameters()

    var angle = Orientation()
        get() = (imu.angularOrientation)

    init {
        params.angleUnit = BNO055IMU.AngleUnit.RADIANS
        imu.initialize(params)
    }
}