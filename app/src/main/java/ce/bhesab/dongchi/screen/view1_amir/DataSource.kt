package ce.bhesab.dongchi.screen.view1_amir

import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.model.viewGroup.Group

class DataSource {
    fun loadGroups(): List<Group> {
        return listOf(
            Group(R.string.nothing, R.drawable.basketball, R.string.basketballTeam),
            Group(R.string.nothing, R.drawable.sharafi, R.string.sharafi),
            Group(R.string.nothing, R.drawable.compony, R.string.company),
            Group(R.string.nothing, R.drawable.download, R.string.university),
            Group(R.string.nothing, R.drawable.famiy, R.string.family),
            Group(R.string.nothing, R.drawable.friends, R.string.friends)
        )
    }
}