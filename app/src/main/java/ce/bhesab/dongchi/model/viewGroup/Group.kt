package ce.bhesab.dongchi.model.viewGroup

import androidx.annotation.DrawableRes

data class Group(
    val stringGroupDataId : String,
    @DrawableRes val imageGroupId : Int,
    val name : String
)
