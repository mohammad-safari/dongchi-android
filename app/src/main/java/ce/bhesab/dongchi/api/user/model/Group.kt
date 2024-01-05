package ce.bhesab.dongchi.api.user.model

data class Group (
    val id: Long,
    val groupName: String,
    val description: String,
    val groupImage: String,
    val otherMembers: List<MemberRetrievalModel>
)