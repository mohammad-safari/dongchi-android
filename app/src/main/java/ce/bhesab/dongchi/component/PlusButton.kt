package ce.bhesab.dongchi.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
    fun PlusButtonInsert(
    modifier: Modifier = Modifier,
    navController: NavController?,
    onClick: () -> Unit
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .size(100.dp)
                .height(50.dp)
                .padding(16.dp),
            shape = RectangleShape,
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null,  modifier = Modifier.size(50.dp))
        }
    }

    @Composable
    @Preview
    fun PlusButtonPreview() {
        PlusButtonInsert(navController = null) { /* Handle button click */ }

    }