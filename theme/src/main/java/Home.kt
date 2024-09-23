import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shekharhandigol.theme.BothPreview
import com.shekharhandigol.theme.EmployeeCRMTheme


@Composable
fun HomeScreen() {
    Temp()
}


@Composable
fun Temp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@BothPreview
@Composable
fun GreetingPreview() {
    EmployeeCRMTheme {
        Greeting("Android")
    }
}