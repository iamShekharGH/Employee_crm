package com.shekharhandigol

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.shekharhandigol.storage.AppUserInformation

val Context.settingsDataStore: DataStore<AppUserInformation> by dataStore(
    fileName = "settings.pb",
    serializer = AppUserInformationSerializer
)

