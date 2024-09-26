package org.example.repository

import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Path

// Una función que reciba el fichero de calificaciones y devuelva una lista de diccionarios,
// donde cada diccionario contiene la información de los exámenes y la asistencia de un alumno.
// La lista tiene que estar ordenada por apellidos.




class QualificationRepository(private val fileDir: Path) {

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


    // Una función que reciba una lista de diccionarios como la que devuelve la función anterior
    // y añada a cada diccionario un nuevo par con la nota final del curso. El peso de cada parcial
    // de teoría en la nota final es de un 30% mientras que el peso del examen de prácticas es de un 40%.
    fun qualificationFinal(qualifications:MutableMap<String, MutableMap<String, String>>)  {

        qualifications.forEach { (k, v) ->
            var p1 =  if(v["Parcial1"] != "") v["Parcial1"]?.replace(",", ".")?.toDouble() else 0.0
            var p2 =  if(v["Parcial2"] != "") v["Parcial2"]?.replace(",", ".")?.toDouble() else 0.0
            var pract = if(v["Practicas"] != "") v["Practicas"]?.replace(",", ".")?.toDouble() else 0.0

            if (v["Ordinario1"] != "") p1 = v["Ordinario1"]?.replace(",", ".")?.toDouble()
            if (v["Ordinario2"] != "") p2 = v["Ordinario2"]?.replace(",", ".")?.toDouble()
            if (v["OrdinarioPracticas"] != "") pract = v["OrdinarioPracticas"]?.replace(",", ".")?.toDouble()


            if (p1 == null) p1 = 0.0
            if (p2 == null) p2 = 0.0
            if (pract == null) pract = 0.0

            val nFinal = (p1 * 0.3) + (p2 * 0.3) + (pract * 0.4)

            v["NotaFinal"] = nFinal.toString()
        }
    }

    // Una función que reciba una lista de diccionarios como la que devuelve la función anterior
    // y devuelva dos listas, una con los alumnos aprobados y otra con los alumnos suspensos.
    // Para aprobar el curso, la asistencia tiene que ser mayor o igual que el 75%,
    // la nota de los exámenes parciales y de prácticas mayor o igual que 4 y la nota final mayor o igual que 5.

    fun classifyStudents (qualifications:MutableMap<String, MutableMap<String, String>>) :Pair<MutableMap<String, MutableMap<String, String>>, MutableMap<String, MutableMap<String, String>>> {
        val approved :MutableMap<String, MutableMap<String, String>> = mutableMapOf()
        val suspended:MutableMap<String, MutableMap<String, String>> = mutableMapOf()

        qualifications.forEach { (k, v) ->

            var att = if (v["Asistencia"] != "") v["Asistencia"]?.replace("%", "")?.toInt() else 0
            var p1 =  if(v["Parcial1"] != "") v["Parcial1"]?.replace(",", ".")?.toDouble() else 0.0
            var p2 =  if(v["Parcial2"] != "") v["Parcial2"]?.replace(",", ".")?.toDouble() else 0.0
            var pract = if(v["Practicas"] != "") v["Practicas"]?.replace(",", ".")?.toDouble() else 0.0
            var nFinal = if(v["NotaFinal"] != "") v["NotaFinal"]?.replace(",", ".")?.toDouble() else 0.0

            if (v["Ordinario1"] != "") p1 = v["Ordinario1"]?.replace(",", ".")?.toDouble()
            if (v["Ordinario2"] != "") p2 = v["Ordinario2"]?.replace(",", ".")?.toDouble()
            if (v["OrdinarioPracticas"] != "") pract = v["OrdinarioPracticas"]?.replace(",", ".")?.toDouble()

            if (att == null) att = 0
            if (p1 == null) p1 = 0.0
            if (p2 == null) p2 = 0.0
            if (pract == null) pract = 0.0
            if (nFinal == null) nFinal = 0.0

            if (att > 75 && p1 >= 4 && p2 >= 4 && pract >= 4 && nFinal >= 5) approved[k] = v
            else suspended[k] = v

        }

        return Pair(approved, suspended)
    }

}