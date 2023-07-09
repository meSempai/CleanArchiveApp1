package lib.kg.newcleanarch.domain.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lib.kg.newcleanarch.data.local.MusicDao
import lib.kg.newcleanarch.data.local.MusicDataBase
import lib.kg.newcleanarch.data.repositories.MusicRepositoryImpl
import lib.kg.newcleanarch.domain.repositories.MusicRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MusicModule {

    @Singleton
    @Provides
    fun provideMusicDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        MusicDataBase::class.java,
        "music"
    ).build()

    @Provides
    fun provideMusicDao(musicDataBase: MusicDataBase) = musicDataBase.musicDao()

    @Provides
    fun provideMusicRepository(musicDao: MusicDao): MusicRepository {
        return MusicRepositoryImpl(musicDao = musicDao)
    }
}