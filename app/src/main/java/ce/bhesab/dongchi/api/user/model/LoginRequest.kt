package ce.bhesab.dongchi.api.user.model

public data class LoginRequest(
    val username: String,
    val email: String?,
    val phone: String?,
    val password: String
)