package org.example

import kotlin.io.path.Path

//El fichero calificaciones.csv contiene las calificaciones de un curso.
// Durante el curso se realizaron dos exámenes parciales de teoría y un examen de prácticas.
// Los alumnos que tuvieron menos de 4 en alguno de estos exámenes pudieron repetirlo
// en la recuperación al final del curso (convocatoria ordinaria). Escribir un programa
// que contenga las siguientes funciones:
//


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val fileDir = Path("src/main/resources/calificaciones.csv")
    val qualificationRepository = QualificationRepository(fileDir)

    val qualifications = qualificationRepository.qualificationList()

    println(qualifications)

    qualificationRepository.qualificationFinal(qualifications)

    println(qualifications)

}