package org.firstinspires.ftc.teamcode.controllers

class ControllerManager(vararg _controllers: IController) {
    private val controllers = arrayOf(*_controllers)

    fun updateControllers() {
        controllers.forEach { c -> c.onLoop() }
    }
}