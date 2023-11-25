package ce.bhesab.dongchi.screen.view1_amir

import ce.bhesab.dongchi.R
import ce.bhesab.dongchi.model.viewGroup.Group

class DataSource {
    fun loadGroups(): List<Group> {
        return listOf(
            Group("هیچ حسابی ندارید", R.drawable.basketball, "تیم بسکتبال"),
            Group("هیچ حسابی ندارید", R.drawable.sharafi, "خوابگاه شرفی"),
            Group("هیچ حسابی ندارید", R.drawable.compony, "شرکت"),
            Group("هیچ حسابی ندارید", R.drawable.download, "دانشکده"),
            Group("هیچ حسابی ندارید", R.drawable.famiy, "خانواده"),
            Group("هیچ حسابی ندارید", R.drawable.friends, "رفقا")
        )
    }
}