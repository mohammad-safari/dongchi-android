package ce.bhesab.dongchi.screen.view1_amir

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.components.BottomNavigationBar
import ce.bhesab.dongchi.components.PlusButtonInsert
import ce.bhesab.dongchi.model.viewGroup.Group

@Composable
fun ViewGroups(navController: NavController?) {
    GroupList(groups = DataSource().loadGroups(), navController = navController)
}

@Composable
fun GroupList(groups: List<Group>, modifier: Modifier = Modifier, navController: NavController?) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 80.dp)
                .fillMaxWidth()
        ) {
            items(groups) { group ->
                GroupLine(
                    group = group,
                    modifier = modifier.padding(8.dp)
                )
            }
        }

        PlusButtonInsert(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp),
            navController
        ) {
            //onClick code
        }

        BottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            navController = navController
        )


    }
}


@Composable
fun GroupLine(group: Group, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(group.imageGroupId),
                contentDescription = group.stringGroupDataId,
                modifier = Modifier
                    .size(width = 100.dp, height = 100.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = group.name,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = group.stringGroupDataId,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.End),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, locale = "fa")
@Composable
fun PreviewGroup() {
    ViewGroups(null)
}


