package ce.bhesab.dongchi.api.models.user

import com.fasterxml.jackson.annotation.JsonProperty

data class ProfileResponse(
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("credit") val credit: Long,
    @JsonProperty("debt") val debt: Long
)
