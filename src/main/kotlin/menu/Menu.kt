package org.example.menu

import org.example.console.Console
import org.example.repository.QualificationRepository

class Menu(private val qualificationRepository: QualificationRepository, private val qualifications: MutableMap<String, MutableMap<String, String>>, private val console: Console) {

    fun executeMenu(op:Int) {
        when (op) {
            1 -> execute1()
            2 -> execute2()
            3 -> execute3()
            4 -> execute4()
        }
        if (op != 5) {
            console.enter()
            console.clearScreen()
        }
    }

    private fun execute1() {
        console.showStudents(qualifications)
    }
    private fun execute2() {
        qualificationRepository.qualificationFinal(qualifications)
        console.showStudentsFinal(qualifications)
    }

    private fun execute3() {
        val approved = qualificationRepository.classifyStudents(qualifications)
        console.showApproved(approved.first)
    }

    private fun execute4() {
        val suspended = qualificationRepository.classifyStudents(qualifications)
        console.showSuspended(suspended.second)
    }

}