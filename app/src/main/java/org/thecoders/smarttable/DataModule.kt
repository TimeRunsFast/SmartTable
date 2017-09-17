package org.thecoders.smarttable

import android.content.Context
import dagger.Module
import dagger.Provides
import org.thecoders.smarttable.data.AppDatabase
import javax.inject.Singleton

/**
 * Created by frenz on 22.06.2017.
 */

@Module
class DataModule {

    @Singleton @Provides fun provideAppDatabase(context: Context) = AppDatabase.getPersistenceDatabase(context)

}