package lib.kg.newcleanarch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import lib.kg.newcleanarch.data.models.MusicEntity

@Database(entities = [MusicEntity::class], version = 1, exportSchema = false)
abstract class MusicDataBase : RoomDatabase() {

    abstract fun musicDao(): MusicDao
}