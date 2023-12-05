package ce.bhesab.dongchi.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ce.bhesab.dongchi.R

@Composable
fun BottomNavigationBar(navController: NavController?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(85.dp)
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //  "Groups"
        BottomNavigationItem(
            icon = Icons.Outlined.Home,
            text =  stringResource(R.string.dashboard),
            onClick = {navController?.navigate("dashboard")}
        )

        //  "Friends"
        BottomNavigationItem(
            icon = Icons.Outlined.Person,
            text = stringResource(R.string.groups),
            onClick = {navController?.navigate("groups")}
        )

        //  "Account"
        BottomNavigationItem(
            icon = Icons.Outlined.AccountBox,
            text =  stringResource(R.string.account),
            onClick = {navController?.navigate("group")}
        )
    }
}

@Composable
fun BottomNavigationItem(icon: ImageVector, text : String , onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxHeight()
            .width(110.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            modifier = modifier
                .size(40.dp)
                .clip(MaterialTheme.shapes.large),
            tint = Color.White,
            contentDescription = null,
            )

        Spacer(modifier = modifier.height(4.dp))

        Text(
            text = text,
            modifier = Modifier
                .size(60.dp),
            Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true, locale = "fa")
fun BottomNavigationBarPreview() {
    BottomNavigationBar(null)
}
