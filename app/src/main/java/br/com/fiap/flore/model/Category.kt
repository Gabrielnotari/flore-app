package br.com.fiap.flore.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import br.com.fiap.flore.R

data class Category(
    val id: Int = 0,
    val name: String = "",
    @DrawableRes val image: Int? = R.drawable.nophoto_img,
    val background: Color = Color.Gray
)
