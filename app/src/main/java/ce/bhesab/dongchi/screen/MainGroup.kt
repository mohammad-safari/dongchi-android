package ce.bhesab.dongchi.screen

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Group
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.api.user.model.Group
import ce.bhesab.dongchi.component.BottomNavigationBar
import ce.bhesab.dongchi.component.PlusButtonInsert
import ce.bhesab.dongchi.viewmodel.ShowGroupViewModel

@Composable
fun ViewGroups(navController: NavController?, context: Context, currentGroup: MutableState<String>) {
    val showGroupViewModel = remember { ShowGroupViewModel(context) }
    showGroupViewModel.getGroups()
    val showGroupList = remember { mutableStateOf(false) }
    LaunchedEffect(showGroupViewModel.groupData.value) {
        showGroupList.value = showGroupViewModel.groupData.value != null
    }
    if (showGroupList.value) {
        showGroupViewModel.groupData.value?.let {
            GroupList(
                groups = it,
                navController = navController,
                currentGroup = currentGroup
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupList(groups: List<Group>, modifier: Modifier = Modifier, navController: NavController?, currentGroup:MutableState<String>) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),

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
                    navController,
                    currentGroup,
                    modifier = modifier.padding(8.dp)
                )
            }
        }

        PlusButtonInsert(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 92.dp, end = 10.dp),
            navController
        ) {
            isSheetOpen = true
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
                            label = { Text(stringResource(R.string.groupName)) },
                            leadingIcon = {
                                Icon(Icons.Default.Group, contentDescription = null)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupLine(
    group: Group,
    navController: NavController?,
    currentGroup: MutableState<String>,
    modifier: Modifier = Modifier
) {
    val decodedBytes =
        if (group.groupImage != null) Base64.decode(group.groupImage, Base64.DEFAULT) else null
    val photo = decodedBytes?.let { BitmapFactory.decodeByteArray(decodedBytes, 0, it.size) }

    Card(modifier = modifier
        .background(Color.White)
        .clickable {}, onClick = {
        currentGroup.value = group.id.toString()
        navController?.navigate("group") }) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        ) {
            if (photo != null) {
                Image(
                    bitmap = photo.asImageBitmap(),
                    contentDescription = group.description,
                    modifier = Modifier
                        .size(width = 100.dp, height = 100.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = group.description,
                    modifier = Modifier
                        .size(width = 100.dp, height = 100.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(
                    text = group.groupName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Text(
                    text = group.description,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.End),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true, locale = "fa")
//@Composable
//fun PreviewGroup() {
//    DongchiTheme {
//        ViewGroups(null)
//    }
//}


