package ce.bhesab.dongchi.component
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ce.bhesab.dongchi.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlusButtonInsert(
    modifier: Modifier = Modifier,
    navController: NavController?
) {
    var nameGroup by remember {
        mutableStateOf("")
    }
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var areButtonsVisible by remember { mutableStateOf(false) }

    Column (modifier = modifier){
        if (areButtonsVisible) {
                InvisibleButton(modifier = modifier.align(Alignment.End).padding(bottom = 10.dp),text = stringResource(R.string.create)) {
                    isSheetOpen = true
                }
                InvisibleButton(modifier = modifier.align(Alignment.End).padding(bottom = 5.dp), text = stringResource(R.string.join)) {
                    navController?.navigate("join")
                }

        }



        Button(
            onClick = {
                areButtonsVisible = !areButtonsVisible
            },
            modifier = modifier
                .size(70.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(MaterialTheme.colorScheme.primary),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }

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


@Composable
fun InvisibleButton(modifier: Modifier ,text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Composable
@Preview
fun PlusButtonPreview() {
    PlusButtonInsert(navController = null)

}