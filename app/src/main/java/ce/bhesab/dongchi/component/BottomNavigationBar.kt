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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(onGroupsClick: () -> Unit, onFriendsClick: () -> Unit, onAccountClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(85.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //  "Groups"
        BottomNavigationItem(
            icon = Icons.Default.Home,
            text =  "Groups",
            onClick = onGroupsClick
        )

        //  "Friends"
        BottomNavigationItem(
            icon = Icons.Default.Person,
            text = "Friends",
            onClick = onFriendsClick
        )

        //  "Account"
        BottomNavigationItem(
            icon = Icons.Default.AccountBox,
            text =  "Account",
            onClick = onAccountClick
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
                .align(Alignment.CenterHorizontally)
                .size(60.dp),
            Color.White,
            fontSize = 15.sp,
        )
    }
}

@Composable
@Preview
fun BottomNavigationBarPreview() {
    BottomNavigationBar({}, {}, {})
}
