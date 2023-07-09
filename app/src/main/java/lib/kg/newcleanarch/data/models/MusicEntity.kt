package lib.kg.newcleanarch.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class MusicEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val perfomer: String,
    val duration: String,
)
