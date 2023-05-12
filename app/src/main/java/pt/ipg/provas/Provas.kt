package pt.ipg.provas

import java.util.Date

data class Provas(
    var nome: String,
    var id: Long = -1,
    var localidade: String,
    var tipo: String,
    var data: Date,
    var id_Percursos: Int
) {
}