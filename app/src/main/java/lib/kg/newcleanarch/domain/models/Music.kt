package lib.kg.newcleanarch.domain.models

import java.io.Serializable

data class Music(
    val id: Int,
    val name: String,
    val perfomer: String,
    val duration: String
) : Serializable
