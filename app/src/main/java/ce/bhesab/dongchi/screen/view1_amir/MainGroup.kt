package ce.bhesab.dongchi.screen.view1_amir

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ce.bhesab.dongchi.component.PlusButtonInsert
import ce.bhesab.dongchi.model.viewGroup.Group

class MainGroup {

    @Composable
    fun ViewGroups() {
        GroupList(groups = DataSource().loadGroups())
    }

    @Composable
    fun GroupList(groups: List<Group>, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(modifier = modifier) {
                items(groups) { group ->
                    GroupLine(
                        group = group,
                        modifier = modifier.padding(8.dp)
                    )
                }
            }

            PlusButtonInsert (modifier = modifier.align(Alignment.BottomEnd)){
                //onClick code
            }


        }
    }


    @Composable
    fun GroupLine(group: Group, modifier: Modifier = Modifier) {
        Card(modifier = modifier) {
            Row {
                Image(
                    painter = painterResource(group.imageGroupId),
                    contentDescription = stringResource(group.stringGroupDataId),
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = stringResource(id = group.name),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Text(
                        text = LocalContext.current.getString(group.stringGroupDataId),
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.End),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewGroup() {
        ViewGroups()
    }

}


