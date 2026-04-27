package br.com.fiap.flore.repository

import androidx.compose.ui.graphics.Color
import br.com.fiap.flore.R
import br.com.fiap.flore.model.Category

fun getAllCategories() = listOf<Category>(
    Category(
        id = 1,
        name = "Vestidos",
        image = R.drawable.vestido_img,
        background = Color(0xFFABF2E9)
    ),
    Category(
        id = 2,
        name = "Calças",
        image = R.drawable.calca_img,
        background = Color(0xFFF4D6C0)
    ),
    Category(
        id = 3,
        name = "Camisetas",
        image = R.drawable.camiseta_img,
        background = Color(0xFFB1D58B)
    ),
    Category(
        id = 4,
        name = "Saias",
        image = R.drawable.saia_img,
        background = Color(0xFF9ACAE0)
    ),
)

fun getCategoryById(id: Int) = getAllCategories()
    .find { category ->
        category.id == id
    }