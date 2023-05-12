package pt.ipg.provas

import java.util.Date

data class Provas(
    var nome: String,
    var localidade: String,
    var tipo: String,
    var data: Date,
    var id_Percursos: Int,
    var id: Long = -1
) {
}