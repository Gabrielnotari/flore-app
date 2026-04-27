package br.com.fiap.flore.repository

import br.com.fiap.flore.R
import br.com.fiap.flore.model.Category
import br.com.fiap.flore.model.EstadoPeca
import br.com.fiap.flore.model.Peca
import br.com.fiap.flore.model.User
import java.time.LocalDate

fun getAllPecas() = listOf<Peca>(
    Peca(
        id = 1,
        category = Category(id = 1, name = "Vestidos"),
        user = User(id = 2, name = "Maria"),
        estadoPeca = EstadoPeca.NOVO,
        nome = "Vestido Azul",
        descricao = "Vestido com desenho de flores azuis",
        valor = 150,
        dataAnuncio = LocalDate.now(),
        image = R.drawable.vestido_fem
    ),
    Peca(
        id = 2,
        category = Category(id = 2, name = "Calças"),
        user = User(id = 3, name = "Joana"),
        estadoPeca = EstadoPeca.NOVO,
        nome = "Calça Jeans",
        descricao = "Calça Jeans Azul",
        valor = 100,
        dataAnuncio = LocalDate.now(),
        image = R.drawable.calca_jeans
    ),
    Peca(
        id = 3,
        category = Category(id = 2, name = "Calças"),
        user = User(id = 3, name = "Joana"),
        estadoPeca = EstadoPeca.USADO,
        nome = "Calça Moletom",
        descricao = "Calça Moletom",
        valor = 100,
        dataAnuncio = LocalDate.now(),
        image = R.drawable.calca_moletom
    )
)

fun getPecasByCategory(id: Int) =  getAllPecas()
    .filter { peca ->
        peca.category.id == id
    }