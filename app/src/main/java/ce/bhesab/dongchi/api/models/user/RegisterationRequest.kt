package ce.bhesab.dongchi.api.models.user

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterationRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String
)
