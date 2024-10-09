package com.shekharhandigol.crm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.shekharhandigol.EmployeeNavDrawer
import com.shekharhandigol.common.Destinations
import com.shekharhandigol.theme.EmployeeCRMTheme
import dagger.hilt.android.AndroidEntryPoint

private const val RC_SIGN_IN: Int = 7777

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()

        setContent {

            EmployeeCRMTheme {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                val openDrawer: () -> suspend () -> Unit = { suspend { drawerState.open() } }
                val navController = rememberNavController()
                EmployeeNavDrawer(
                    drawerState = drawerState,
                    navController = navController,
                    navigateToAttendance = {
                        navController.navigate(Destinations.AttendanceSummaryModule)
                    },
                    navigateToSalary = {
                        navController.navigate(Destinations.SalarySummaryModule)
                    },
                    closeDrawer = {}) {
                    EmployeeCrmAppNavHost(navController,openDrawer) { signIn(createSignInRequest()) }
                }


            }
        }
    }

    private fun createSignInRequest() = GoogleSignIn.getClient(
        this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()
    )

    private fun signIn(googleSignInClient: GoogleSignInClient) {
        val signInIntent = googleSignInClient.signInIntent
        this@MainActivity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            task.addOnCompleteListener(this) { task ->
                handleSignInResult(task)
            }
            task.addOnFailureListener { reason ->
                reason.printStackTrace()
            }
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account = task.result!!
            // Signed in successfully, update UI or navigate to next screen
            val name = account.displayName
            val email = account.email
            val idToken = account.idToken
            Log.d("TAG", "signInResult:failed code=$name $email $idToken ")
            // Use the information to perform authentication with your backend server
        } else {
            // Sign-in failed, update UI accordingly
            Log.d("TAG", "signInResult:failed code=${task.exception?.message}")
        }
    }
}
