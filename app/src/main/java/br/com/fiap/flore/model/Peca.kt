package br.com.fiap.flore.model

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Peca(
    val id: Int = 0,
    val category: Category,
    val user: User,
    val estadoPeca: EstadoPeca,
    val nome: String = "",
    val descricao: String = "",
    val valor: Int = 0,
    val dataAnuncio: LocalDate = LocalDate.now(),
    @DrawableRes val image: Int
)
