import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.javidev.eltiempo.data.dataSource.DataOrException
import com.javidev.eltiempo.data.model.Unit
import com.javidev.eltiempo.data.model.Weather
import com.javidev.eltiempo.data.model.WeatherItem
import com.javidev.eltiempo.presentation.navigation.DestinoScreen
import com.javidev.eltiempo.presentation.screens.common.HumidityWindPressureRow
import com.javidev.eltiempo.presentation.screens.common.SunsetSunRiseRow
import com.javidev.eltiempo.presentation.screens.common.WeatherDetailRow
import com.javidev.eltiempo.presentation.screens.common.WeatherStateImage
import com.javidev.eltiempo.presentation.screens.customComposables.WeatherAppBar
import com.javidev.eltiempo.presentation.screens.mainScreen.MainViewModel
import com.javidev.eltiempo.presentation.screens.settingsScreen.SettingsViewModel
import com.javidev.eltiempo.util.formate.formatDate
import com.javidev.eltiempo.util.formate.formatDecimals

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
) {
    val curCity: String = if (city!!.isBlank()) "Seattle" else city
    val unitFromDb = settingsViewModel.unitList.collectAsState().value

    var unit by remember {
        mutableStateOf("imperial")
    }
    var isImperial by remember {
        mutableStateOf(false)
    }

    // todo probando un if

    if (unitFromDb.isNullOrEmpty()){

    }

    ShowMain(
        unitFromDb = unitFromDb,
        unit = unit,
        isImperial = isImperial,
        mainViewModel = mainViewModel,
        curCity = curCity,
        navController = navController
    )



}

@Composable
private fun ShowMain(
    unitFromDb: List<Unit>,
    unit: String,
    isImperial: Boolean,
    mainViewModel: MainViewModel,
    curCity: String,
    navController: NavController
) {
    var unit1 = unit
    var isImperial1 = isImperial

    if (!unitFromDb.isNullOrEmpty()) {
        unit1 = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial1 = unit1 == "imperial"

        val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)
        ) {
            value = mainViewModel.getWeatherData(city = curCity, units = unit)
        }.value

        if (weatherData.loading == true) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }

        } else if (weatherData.data != null) {

            MainScaffold(
                weather = weatherData.data!!, navController,
                isImperial = isImperial1
            )

        }

    }else  Text(text = "LA DB ESTA VACIA",Modifier.size(600.dp))

}

@Composable
fun MainScaffold(
    weather: Weather, navController: NavController, isImperial: Boolean
) {

    Scaffold(topBar = {
        WeatherAppBar(title = weather.city.name + " ,${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.navigate(DestinoScreen.SearchScreen.name)


            },
            elevation = 5.dp){
            Log.d("TAG", "MainScaffold: Button Clicked")
        }

    }) {
        MainContent(data = weather, isImperial = isImperial)

    }
}

@Composable
fun MainContent(data: Weather, isImperial: Boolean) {
    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = formatDate(weatherItem.dt), // Wed Nov 30
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp))

        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {

            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = data.list[0], isImperial = isImperial)
        Divider()
        SunsetSunRiseRow(weather = data.list[0])
        Text("This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold)

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)){
                items(items =  data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)

                }

            }

        }

    }

}