package ce.bhesab.dongchi.model.shares

data class Share(
    val currency: String,
    val amount: String,
    val type: ShareType,
    val unit: String?,
    val descr: String?,
)