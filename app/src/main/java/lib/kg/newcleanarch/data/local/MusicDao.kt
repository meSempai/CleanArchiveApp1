package lib.kg.newcleanarch.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import lib.kg.newcleanarch.data.models.MusicEntity

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMusic(musicEntity: MusicEntity)

    @Query("SELECT * FROM music")
    suspend fun getMusics(): List<MusicEntity>

    @Query("SELECT * FROM music ORDER BY perfomer ASC")
    suspend fun getMusicByPerfomer(): List<MusicEntity>

    @Query("SELECT * FROM music ORDER BY CAST(duration " + "AS INTEGER) ASC")
    suspend fun getMusicByDuration(): List<MusicEntity>

    @Update
    suspend fun updateMusic(musicEntity: MusicEntity)

    @Delete
    suspend fun deleteMusic(musicEntity: MusicEntity)
}