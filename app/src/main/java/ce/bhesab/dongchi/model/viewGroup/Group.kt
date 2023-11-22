package ce.bhesab.dongchi.model.viewGroup

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Group(
    @StringRes val stringGroupDataId : Int,
    @DrawableRes val imageGroupId : Int,
    @StringRes val name : Int
)
