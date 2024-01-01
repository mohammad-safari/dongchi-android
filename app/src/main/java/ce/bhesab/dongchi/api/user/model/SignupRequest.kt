package ce.bhesab.dongchi.api.user.model;

public data class SignupRequest(
    val username: String,
    val email: String,
    val phone: String,
    val password: String
)
