package org.example

import java.io.BufferedReader
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Path

// Una función que reciba el fichero de calificaciones y devuelva una lista de diccionarios,
// donde cada diccionario contiene la información de los exámenes y la asistencia de un alumno.
// La lista tiene que estar ordenada por apellidos.
//
// Una función que reciba una lista de diccionarios como la que devuelve la función anterior
// y añada a cada diccionario un nuevo par con la nota final del curso. El peso de cada parcial
// de teoría en la nota final es de un 30% mientras que el peso del examen de prácticas es de un 40%.
//
// Una función que reciba una lista de diccionarios como la que devuelve la función anterior
// y devuelva dos listas, una con los alumnos aprobados y otra con los alumnos suspensos.
// Para aprobar el curso, la asistencia tiene que ser mayor o igual que el 75%,
// la nota de los exámenes parciales y de prácticas mayor o igual que 4 y la nota final mayor o igual que 5.

class QualificationRepository(val fileDir: Path) {

    fun qualificationList():MutableMap<String, MutableMap<String, String>> {
        val list: MutableMap<String, MutableMap<String, String>> = mutableMapOf()

        val br:BufferedReader = Files.newBufferedReader(fileDir)

        var cont = 0

        br.use { flujo ->
            flujo.forEachLine { lines ->
                if (cont > 0) {
                    val alumn = lines.split(";")
                    list[alumn[0]] = mutableMapOf("Nombre" to alumn[1], "Asistencia" to alumn[2], "Parcial1" to alumn[3], "Parcial2" to alumn[4], "Ordinario1" to alumn[5], "Ordinario2" to alumn[6], "Practicas" to alumn[7], "OrdinarioPracticas" to alumn[8])
                }
                cont++
            }
        }
        return list.toSortedMap()
    }

    fun qualificationFinal(qualifications:MutableMap<String, MutableMap<String, String>> )  {

        qualifications.forEach { (k, v) ->
            val p1 = v["Parcial1"]?.toInt()
            val p2 = v["Parcial2"]?.toInt()
            val pract = v["Practica"]?.toInt()



            v["Nota Final"] = "Pepe"
        }
    }

}