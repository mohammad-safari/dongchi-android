package ce.bhesab.dongchi.model.shares

data class Participant(
    val id: String,
    val alias: String,
    val name: String,
    val share: Share,
)
