package org.example

import org.example.console.Console
import org.example.menu.Menu
import org.example.repository.QualificationRepository
import kotlin.io.path.Path

// El fichero calificaciones.csv contiene las calificaciones de un curso.
// Durante el curso se realizaron dos exámenes parciales de teoría y un examen de prácticas.
// Los alumnos que tuvieron menos de 4 en alguno de estos exámenes pudieron repetirlo
// en la recuperación al final del curso (convocatoria ordinaria). Escribir un programa
// que contenga las siguientes funciones:
//


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val fileDir = Path("src/main/resources/calificaciones.csv")
    val console = Console()
    val qualificationRepository = QualificationRepository(fileDir)
    val qualifications = qualificationRepository.qualificationList()
    val menu = Menu(qualificationRepository, qualifications, console)

    do {
        console.showMenu()
        val op = console.askNumber(5)
        menu.executeMenu(op)

    } while (op != 5)


}