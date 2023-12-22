package ce.bhesab.dongchi.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.components.LanguageDropdown
import com.github.paolorotolo.appintro.model.SliderPagerBuilder

@Composable
fun Intro(navController: NavController?) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LanguageDropdown()
        LazyRow() {
            items(
                listOf(
                    listOf(
                        R.string.slide_one_top_text, R.string.slide_one_down_text, R.drawable.logo
                    ), listOf(
                        R.string.slide_two_top_text, R.string.slide_two_down_text, R.drawable.groups
                    ), listOf(
                        R.string.slide_three_top_text,
                        R.string.slide_three_down_text,
                        R.drawable.events
                    ), listOf(
                        R.string.slide_four_top_text,
                        R.string.slide_four_down_text,
                        R.drawable.balances
                    )
                )
            ) {
                Column(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = it[0]),
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Image(
                        modifier = Modifier
                            .defaultMinSize(minHeight = dimensionResource(id = R.dimen.min_slide_height))
                            .padding(vertical = dimensionResource(id = R.dimen.card_padding)),
                        painter = painterResource(id = it[2]),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = it[1]),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
        Button(onClick = { navController?.navigate("signup") }) {
            Text(text = stringResource(R.string.skip))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntroPreview() {
    Intro(null)
}