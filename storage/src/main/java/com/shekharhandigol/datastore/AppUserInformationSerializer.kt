package com.shekharhandigol.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.shekharhandigol.storage.AppUserInformation
import com.shekharhandigol.storage.AuthToken
import java.io.InputStream
import java.io.OutputStream

object AppUserInformationSerializer : Serializer<AppUserInformation> {
    override val defaultValue: AppUserInformation
        get() = AppUserInformation.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppUserInformation {
        return try {
            AppUserInformation.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AppUserInformation, output: OutputStream) {
        t.writeTo(output)
    }
}
object AuthTokenSerializer : Serializer<AuthToken> {
    override val defaultValue: AuthToken
        get() = AuthToken.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AuthToken {
        return try {
            AuthToken.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AuthToken, output: OutputStream) {
        t.writeTo(output)
    }
}