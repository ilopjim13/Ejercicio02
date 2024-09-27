package org.example.console

class Console {

    fun showMenu() {
        showMessage("CALIFICACIONES", true)
        showMessage("1. Mostrar Calificaciones", true)
        showMessage("2. Mostrar Nota Final", true)
        showMessage("3. Mostrar Aprobados", true)
        showMessage("4. Mostrar Suspensos", true)
        showMessage("5. Salir", true)
    }


    fun showStudents(map: MutableMap<String, MutableMap<String, String>>) {
        println("\nESTUDIANTES:")
        map.forEach { (k, v) ->
            println("$k, ${v["Nombre"]} -> Asistencia: ${v["Asistencia"]}, Parcial 1: ${v["Parcial1"]}, Parcial 2: ${v["Parcial2"]}${if(v["Ordinario1"] != "") ", Ordinario 1: ${v["Ordinario1"]}" else ""}${if(v["Ordinario2"] != "") ", Ordinario 2: ${v["Ordinario2"]}" else ""}${if(v["Practicas"] != "") ", Practicas: ${v["Practicas"]}" else ""}${if(v["OrdinarioPracticas"] != "") ", Ordinario Practicas: ${v["OrdinarioPracticas"]}" else ""}")
        }
    }

    fun showStudentsFinal(map: MutableMap<String, MutableMap<String, String>>) {
        println("\nNOTA FINAL:")
        map.forEach { (k,v) ->
            println("$k, ${v["Nombre"]} -> Asistencia: ${v["Asistencia"]}, Parcial 1: ${v["Parcial1"]}, Parcial 2: ${v["Parcial2"]}${if(v["Ordinario1"] != "") ", Ordinario 1: ${v["Ordinario1"]}" else ""}${if(v["Ordinario2"] != "") ", Ordinario 2: ${v["Ordinario2"]}" else ""}${if(v["Practicas"] != "") ", Practicas: ${v["Practicas"]}" else ""}${if(v["OrdinarioPracticas"] != "") ", Ordinario Practicas: ${v["OrdinarioPracticas"]}" else ""}, Nota Final: ${v["NotaFinal"]}")
        }
    }

    fun showApproved(approved: MutableMap<String, MutableMap<String, String>>) {
        println("\nAPROBADOS:")
        approved.forEach { (k,v) ->
            println("$k, ${v["Nombre"]} -> Asistencia: ${v["Asistencia"]}, Parcial 1: ${v["Parcial1"]}, Parcial 2: ${v["Parcial2"]}${if(v["Ordinario1"] != "") ", Ordinario 1: ${v["Ordinario1"]}" else ""}${if(v["Ordinario2"] != "") ", Ordinario 2: ${v["Ordinario2"]}" else ""}${if(v["Practicas"] != "") ", Practicas: ${v["Practicas"]}" else ""}${if(v["OrdinarioPracticas"] != "") ", Ordinario Practicas: ${v["OrdinarioPracticas"]}" else ""}, Nota Final: ${v["NotaFinal"]}")
        }
    }

    fun showSuspended(suspended: MutableMap<String, MutableMap<String, String>>) {
        println("\nSUSPENSOS:")
        suspended.forEach { (k,v) ->
            println("$k, ${v["Nombre"]} -> Asistencia: ${v["Asistencia"]}, Parcial 1: ${v["Parcial1"]}, Parcial 2: ${v["Parcial2"]}${if(v["Ordinario1"] != "") ", Ordinario 1: ${v["Ordinario1"]}" else ""}${if(v["Ordinario2"] != "") ", Ordinario 2: ${v["Ordinario2"]}" else ""}${if(v["Practicas"] != "") ", Practicas: ${v["Practicas"]}" else ""}${if(v["OrdinarioPracticas"] != "") ", Ordinario Practicas: ${v["OrdinarioPracticas"]}" else ""}, Nota Final: ${v["NotaFinal"]}")
        }
    }

    private fun showMessage(message:String, br:Boolean = false) {
        if (br) println(message)
        else print(message)
    }

    fun askNumber(ops:Int): Int {
        showMessage(">> Selecciona una opcion: ")
        var opcion = -1
        do {
            try {
                opcion = readln().toInt()
                if (opcion !in (1..ops)) {
                    showMessage("**ERROR** Debe de ser una opcion valida.", true)
                    showMessage(">> Selecciona una opcion: ")
                }
            } catch (e: NumberFormatException) {
                showMessage("**ERROR** Debe de ser una opcion valida.", true)
                showMessage(">> Selecciona una opcion: ")
            }
        } while(opcion !in (1..ops))
        return opcion
    }

    fun enter() {
        print(">> Dale <ENTER> para continuar...")
        readln()
        println()
    }

    fun clearScreen() {
        repeat(50) { println() }
    }

}