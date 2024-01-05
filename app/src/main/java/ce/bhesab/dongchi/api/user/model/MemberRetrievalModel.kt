package ce.bhesab.dongchi.api.user.model

data class MemberRetrievalModel(
    val id: Long,
    val username: String,
    val email: String,
    val phone: String
)