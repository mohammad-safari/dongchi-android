package ce.bhesab.dongchi.screen.view1_amir

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.components.BottomNavigationBar
import ce.bhesab.dongchi.components.PlusButtonInsert
import ce.bhesab.dongchi.model.viewGroup.Group

@Composable
fun ViewGroups(navController: NavController?) {
    GroupList(groups = DataSource().loadGroups(), navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupList(groups: List<Group>, modifier: Modifier = Modifier, navController: NavController?) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        var nameGroup by remember {
            mutableStateOf("")
        }
        val sheetState = rememberModalBottomSheetState()
        var isSheetOpen by rememberSaveable {
            mutableStateOf(false)
        }


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
            isSheetOpen = true
            //onClick code
        }

        BottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            navController = navController
        )


        if (isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isSheetOpen = false
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = nameGroup,
                            onValueChange = { nameGroup = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            label = { Text(stringResource(R.string.code)) },
                            leadingIcon = {
                                Icon(Icons.Default.Code, contentDescription = null)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = stringResource(id = R.string.create))
                        }
                    }

                }
            }
        }
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


