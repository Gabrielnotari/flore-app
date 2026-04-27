package br.com.fiap.flore.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.flore.R
import br.com.fiap.flore.model.Peca
import br.com.fiap.flore.navigation.Destination
import br.com.fiap.flore.repository.getAllPecas
import br.com.fiap.flore.repository.getCategoryById
import br.com.fiap.flore.repository.getPecasByCategory
import br.com.fiap.flore.ui.theme.FloreTheme

@Composable
fun CategoryPecaScreen(categoryId: Int?, navController: NavHostController?) {

    val pecasByCategory = getPecasByCategory(
        id = categoryId!!
    )

    var categoryName = ""

    when (pecasByCategory.size) {
        0 -> {
            categoryName = getCategoryById(categoryId)!!.name
        }

        else -> {
            categoryName = pecasByCategory[0].category.name
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme
                    .colorScheme.background
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        println("navega...")
                        navController!!.navigate(
                            route = Destination.HomeScreen.createRoute("")
                        )
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    text = categoryName,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults
                        .colors(
                            unfocusedBorderColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color.LightGray,
                        ),
                    trailingIcon = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = ""
                            )
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(R.string.procure_pecas))
                    }
                )
                LazyColumn(
                    contentPadding = PaddingValues(
                        horizontal = 16.dp, vertical = 16.dp
                    ),
                    verticalArrangement = Arrangement
                        .spacedBy(8.dp)
                ) {
                    if (pecasByCategory.isNotEmpty()) {
                        items(pecasByCategory) { peca ->
                            CategoryPeca(peca)
                        }
                    } else {
                        item {
                            Text(
                                text = "Não tem peças nessa categoria.",
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) { }
        }
    }
}

@Composable
fun CategoryPeca(peca: Peca) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme
                .colorScheme.primary
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(peca.image!!),
                contentDescription = "",
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(4f)
                    .fillMaxSize()
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = peca.nome,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = peca.descricao,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    lineHeight = 15.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .weight(1f)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Label,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = peca.estadoPeca.description,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.MonetizationOn,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "${peca.valor} R$ ",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoryPecaPreview() {
    FloreTheme() {
        CategoryPeca(getAllPecas()[0])
    }
}

@Preview
@Composable
private fun CategoryPecaScreenPreview() {
    FloreTheme(){
        CategoryPecaScreen(2,null)
    }
}