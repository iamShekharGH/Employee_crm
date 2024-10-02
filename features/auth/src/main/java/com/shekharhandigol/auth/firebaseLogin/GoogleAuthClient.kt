package com.shekharhandigol.auth.firebaseLogin

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.shekharhandigol.auth.R
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthClient(
    private val context: Context,
    private val signInClient: SignInClient
) {
    private val auth = Firebase.auth

    private lateinit var googleSignInClient: GoogleSignInClient

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = signInClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePicUrl = photoUrl?.toString()
                    )
                },
                errorMsg = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e

            SignInResult(
                data = null,
                errorMsg = e.message
            )
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePicUrl = photoUrl?.toString()
        )
    }

    suspend fun signOut() {
        try {
            signInClient.signOut().await()
            auth.signOut()

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }


    suspend fun signIn(): IntentSender? {
        val result = try {
            signInClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }

        return result?.pendingIntent?.intentSender
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            ).setAutoSelectEnabled(true)
            .build()
    }
}