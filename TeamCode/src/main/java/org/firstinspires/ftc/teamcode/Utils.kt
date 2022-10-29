package org.firstinspires.ftc.teamcode

import kotlin.math.pow
import kotlin.math.sign

/**
 * Meant make input more responsive with joysticks
 * Like, when the values are low, they get even lower, but 1 squared is 1, so at the top end its fine
 */
fun smoothInput(value: Double): Double {
    return value.pow(2.0) * sign(value)
}
