package com.example.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

/*herolist, herotopappbar, herocard*/

@Composable
fun HeroList() {
    val heroes = HeroesRepository.heroes

    LazyColumn {
        items(heroes) { hero ->
            HeroCard(hero = hero,modifier=Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }
    }
}


@Composable
fun HeroCard(hero: Hero,modifier:Modifier=Modifier) {
    Card(
        //efecto que hace que la tarjeta esté un poco levantada
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)))
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {

                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )

        },
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroAppContent() {
        Scaffold(
            topBar = { HeroTopAppBar() }
        ) { contentPadding ->
            LazyColumn(contentPadding = contentPadding) {
                items(HeroesRepository.heroes) { hero ->
                    HeroCard(
                        hero = hero,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
}

@Preview
@Composable
fun HeroListPreview() {
    SuperheroesTheme (darkTheme = false){
        HeroAppContent()
    }
}
@Preview
@Composable
fun DarkHeroListPreview() {
    SuperheroesTheme (darkTheme = true){
        HeroAppContent()
    }
}




