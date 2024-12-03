package com.example.justicelawmovil.screens

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.justicelawmovil.R
import com.example.justicelawmovil.model.UserProfileModel
import com.example.justicelawmovil.navigation.NavigationItem
import com.example.justicelawmovil.screens.HomeScreen
import com.example.justicelawmovil.service.UpdateProfileRequest
import com.example.justicelawmovil.viewModel.CityViewModel
import com.example.justicelawmovil.viewModel.CountryViewModel
import com.example.justicelawmovil.viewModel.MeState
import com.example.justicelawmovil.viewModel.MeViewModel
import com.example.justicelawmovil.viewModel.StateViewModel
import com.example.justicelawmovil.viewModel.UpdateProfileState
import com.example.justicelawmovil.viewModel.UpdateProfileViewModel
import com.example.justicelawmovil.viewModel.UserProfileState
import com.example.justicelawmovil.viewModel.UserProfileViewModel
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController,
                  viewModel: MeViewModel = viewModel(),
                  countryViewModel: CountryViewModel = viewModel(),
                  stateViewModel: StateViewModel = viewModel(),
                  cityViewModel: CityViewModel = viewModel(),
                  userProfileViewModel: UserProfileViewModel = viewModel(),
                  updateProfileViewModel: UpdateProfileViewModel = viewModel(),
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val updateProfileState by updateProfileViewModel.updateProfileState.observeAsState(UpdateProfileState.Idle)


    val meState by viewModel.meState.observeAsState()

    val countries by countryViewModel.countries.observeAsState(emptyList())

    var selectedCountry by remember { mutableStateOf("Seleccionar país") }

    val states by stateViewModel.states.collectAsState()
    var selectedState by remember { mutableStateOf("Seleccionar estado") }

    val cities by cityViewModel.cities.collectAsState()
    var selectedCity by remember { mutableStateOf("Seleccionar ciudad") }

    val helpIcon: Painter = painterResource(id = R.drawable.help)
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val forumIcon: Painter = painterResource(id = R.drawable.forum)
    val quienesSomos: Painter = painterResource(id = R.drawable.quienes_somos)
    val historialIcon: Painter = painterResource(id = R.drawable.historial)
    val profileIcon: Painter = painterResource(id = R.drawable.profile)
    val notificationIcon: Painter = painterResource(id = R.drawable.notifications)
    val settingsIcon: Painter = painterResource(id = R.drawable.settings)
    val menuIcon: Painter = painterResource(id = R.drawable.menu)

    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    var stateDropdownExpanded by remember { mutableStateOf(false) }

    var cityDropdownExpanded by remember { mutableStateOf(false) }

    val userProfileState by userProfileViewModel.userProfileState.observeAsState()

    var cellPhone by remember { mutableStateOf("") }

    var profileImageUri by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> profileImageUri = uri.toString() }

    LaunchedEffect(Unit) {
        viewModel.getUserInfo(context)
        countryViewModel.fetchCountries()
        userProfileViewModel.getProfile(context)

    }


    userProfileState?.let { profileState ->
        when (profileState) {
            is UserProfileState.Loading -> {
            }
            is UserProfileState.Success -> {
                val profile = profileState.user
                selectedCountry = countries.find { it.id == profile.country_id }?.name ?: "Seleccionar país"
                selectedState = states.find { it.id == profile.state_id }?.name ?: "Seleccionar estado"
                selectedCity = cities.find { it.id == profile.city_id }?.name ?: "Seleccionar ciudad"

                cellPhone = profile.cell_phone ?: ""

                var profilePhotoUri = profileState.user.photo
                Log.d("ProfilePhotoURI", "Photo URI: $profilePhotoUri")

                if (!profilePhotoUri.isNullOrEmpty()) {
                    profilePhotoUri = profilePhotoUri.replace("http://", "https://")
                    profileImageUri = profilePhotoUri
                }

                if (profile.country_id != null) {
                    stateViewModel.fetchStatesByCountry(profile.country_id)
                }
                if (profile.state_id != null) {
                    cityViewModel.fetchCitiesByState(profile.state_id)
                }


            }
            is UserProfileState.Error -> {
                // Mostrar mensaje de error si es necesario
            }
        }
    }




    @Composable
    fun DrawerContent(navController: NavController) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp

        Column(
            modifier = Modifier
                .width(screenWidth / 1.5f)
                .fillMaxHeight()
                .background(Color(0xFF003049))
        ) {
            DrawerHeader()

            DrawerMenuItem(icon = homeIcon, label = "Home") {
                navController.navigate(NavigationItem.Home.route)
            }

            DrawerMenuItem(icon = profileIcon, label = "Perfil") {
                navController.navigate(NavigationItem.Profile.route)
            }

            DrawerMenuItem(icon = notificationIcon, label = "Notification") {
                navController.navigate(NavigationItem.Notification.route)
            }

            DrawerMenuItem(icon = historialIcon, label = "Historial") {
                navController.navigate(NavigationItem.Historial.route)
            }

            androidx.compose.material3.Divider(modifier = Modifier.padding(vertical = 8.dp))

            DrawerMenuItem(icon = settingsIcon, label = "Configuración") {
                navController.navigate(NavigationItem.Configuracion.route)
            }

            DrawerMenuItem(icon = quienesSomos, label = "Quienes Somos") {
                navController.navigate(NavigationItem.QuienesSomos.route)
            }


        }
    }

    @Composable
    fun TopBar(drawerState: DrawerState, menuIcon: Painter, scope: CoroutineScope) {
        TopAppBar(
            title = {
                Box(modifier = Modifier.fillMaxSize())
            },
            navigationIcon = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth(Alignment.Start)
                        .padding(vertical = 24.dp)
                ) {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        androidx.compose.material3.Icon(
                            painter = menuIcon,
                            contentDescription = "Menu",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            backgroundColor = Color.White,
            modifier = Modifier.height(90.dp),
            elevation = 0.dp
        )
    }

    @Composable
    fun BottomBar(
        navController: NavController,
        homeIcon: Painter,
        searchIcon: Painter,
        forumIcon: Painter
    ) {
        androidx.compose.material3.BottomAppBar(
            modifier = Modifier
                .padding(16.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(25.dp)),
            containerColor = Color(0xFF003049),
            contentColor = Color.White,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                androidx.compose.material3.IconButton(onClick = {
                    navController.navigate(
                        NavigationItem.Home.route
                    )
                }) {
                    androidx.compose.material3.Icon(
                        painter = homeIcon,
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                androidx.compose.material3.IconButton(onClick = {
                    navController.navigate(
                        NavigationItem.Informacion.route
                    )
                }) {
                    androidx.compose.material3.Icon(
                        painter = searchIcon,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                androidx.compose.material3.IconButton(onClick = {
                    navController.navigate(
                        NavigationItem.Foro.route
                    )
                }) {
                    androidx.compose.material3.Icon(
                        painter = forumIcon,
                        contentDescription = "Foro",
                        tint = Color.White
                    )
                }
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(
            topBar = { TopBar(drawerState = drawerState, menuIcon = menuIcon, scope = scope) },
            bottomBar = { BottomBar(navController = navController, homeIcon = homeIcon, searchIcon = searchIcon, forumIcon = forumIcon) },
            content = { innerPadding ->

                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp)
                        .verticalScroll(scrollState)
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Log.d("ProfileScreen", "ProfileImageUri: $profileImageUri")

                    Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                        Image(
                            painter = rememberImagePainter(
                                data = profileImageUri,
                                builder = {
                                    crossfade(true)
                                    placeholder(R.drawable.profile) // Imagen temporal
                                    error(R.drawable.profile) // Imagen de error
                                    listener(
                                        onSuccess = { _, _ ->
                                            Log.d("ImageLoader", "Image loaded successfully.")
                                        },
                                        onError = { _, result ->
                                            Log.e(
                                                "ImageLoader",
                                                "Error loading image: ${result.throwable}"
                                            )
                                        }
                                    )
                                }
                            ),
                            contentDescription = "Perfil",
                            modifier = Modifier
                                .size(100.dp)
                                .clickable { launcher.launch("image/*") }
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                    }



                    when (meState) {
                        is MeState.Success -> {
                            val user = (meState as MeState.Success).user
                            val fullName = "${user.name} ${user.last_name}"

                            // Mostrar el nombre completo
                            Text(
                                text = fullName,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 21.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                        is MeState.Error -> {
                            Text(
                                text = "Error: ${(meState as MeState.Error).message}",
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        else -> {
                            // Mostrar texto vacío o mensaje de error si no hay datos disponibles

                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 13.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Icono",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Mi perfil", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Nombre completo",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 7.dp).align(Alignment.Start)
                    )
                    when (meState) {
                        is MeState.Success -> {
                            val user = (meState as MeState.Success).user
                            val fullName = "${user.name} ${user.last_name}"
                            TextField(
                                value = fullName,
                                onValueChange = { },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, Color(0xFF003049), shape = RoundedCornerShape(25.dp)),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(16.dp),
                            )
                        }
                        else -> {
                            // Si no hay datos disponibles, mostrar un campo vacío
                            TextField(
                                value = "",
                                onValueChange = { },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, Color(0xFF003049), shape = RoundedCornerShape(25.dp)),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(16.dp),
                            )
                        }
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 7.dp).align(Alignment.Start)
                    )
                    when (meState) {
                        is MeState.Success -> {
                            val user = (meState as MeState.Success).user
                            TextField(
                                value = user.email,
                                onValueChange = { },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, Color(0xFF003049), shape = RoundedCornerShape(25.dp)),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(16.dp),
                            )
                        }
                        else -> {
                            TextField(
                                value = "",
                                onValueChange = { },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, Color(0xFF003049), shape = RoundedCornerShape(25.dp)),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(16.dp),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Numero Telefonico",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 7.dp) .align(Alignment.Start)
                    )
                    TextField(
                        value = cellPhone,
                        onValueChange = { cellPhone = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color(0xFF003049), shape = RoundedCornerShape(25.dp)),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(16.dp),
                    )



                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Pais",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 7.dp) .align(Alignment.Start)
                    )
                    androidx.compose.material3.ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedCountry,
                            onValueChange = { },
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .background(Color.White, shape = RoundedCornerShape(16.dp))
                                .clickable {
                                    expanded = !expanded
                                }
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            countries.forEach { country ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedCountry = country.name
                                        countryViewModel.selectCountry(country.name)
                                        stateViewModel.fetchStatesByCountry(country.id)
                                        expanded = false
                                    },
                                    modifier = Modifier.background(Color.White)
                                ) {
                                    Text(text = country.name)
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Estado",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 7.dp) .align(Alignment.Start)
                    )
                    androidx.compose.material3.ExposedDropdownMenuBox(
                        expanded = stateDropdownExpanded,
                        onExpandedChange = { stateDropdownExpanded = !stateDropdownExpanded }
                    ) {
                        OutlinedTextField(
                            value = selectedState,
                            onValueChange = { },
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = stateDropdownExpanded)
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .background(Color.White, shape = RoundedCornerShape(16.dp))
                                .clickable { stateDropdownExpanded = !stateDropdownExpanded }
                        )
                        ExposedDropdownMenu(
                            expanded = stateDropdownExpanded,
                            onDismissRequest = { stateDropdownExpanded = false }
                        ) {
                            states.forEach { state ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedState = state.name
                                        stateViewModel.selectState(state.name)
                                        cityViewModel.fetchCitiesByState(state.id)
                                        stateDropdownExpanded = false
                                    },
                                    modifier = Modifier.background(Color.White)
                                ) {
                                    Text(text = state.name)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = "Ciudad",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 7.dp) .align(Alignment.Start)
                    )
                    androidx.compose.material3.ExposedDropdownMenuBox(
                        expanded = cityDropdownExpanded,
                        onExpandedChange = { cityDropdownExpanded = !cityDropdownExpanded }
                    ) {
                        OutlinedTextField(
                            value = selectedCity,
                            onValueChange = { },
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityDropdownExpanded)
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                                .background(Color.White, shape = RoundedCornerShape(16.dp))
                                .clickable { cityDropdownExpanded = !cityDropdownExpanded }
                        )
                        ExposedDropdownMenu(
                            expanded = cityDropdownExpanded,
                            onDismissRequest = { cityDropdownExpanded = false }
                        ) {
                            cities.forEach { city ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedCity = city.name
                                        cityViewModel.selectCity(city.name)
                                        cityDropdownExpanded = false
                                    },
                                    modifier = Modifier.background(Color.White)
                                ) {
                                    Text(text = city.name)
                                }
                            }
                        }
                    }



                    Spacer(modifier = Modifier.height(8.dp))




                    Spacer(modifier = Modifier.height(8.dp))


                    when (updateProfileState) {
                        is UpdateProfileState.Loading -> {
                            // Mostrar un indicador de carga
                            Log.d("UpdateProfileState", "Cargando...")
                        }
                        is UpdateProfileState.Success -> {
                            // Mostrar el mensaje de éxito
                            Toast.makeText(context, "Perfil actualizado con éxito", Toast.LENGTH_SHORT).show()
                        }
                        is UpdateProfileState.Error -> {
                            // Mostrar el mensaje de error
                            Toast.makeText(context, "Error: ${(updateProfileState as UpdateProfileState.Error).message}", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            // Estado Idle, puedes no hacer nada o mostrar el contenido normal
                        }
                    }

                    androidx.compose.material3.Button(
                        onClick = {

                            Log.d("Button", "Botón de guardar presionado ifff")
                            val selectedCountryId = countries.firstOrNull { it.name == selectedCountry }?.id
                            val selectedStateId = states.firstOrNull { it.name == selectedState }?.id
                            val selectedCityId = cities.firstOrNull { it.name == selectedCity }?.id

                            // Validación de selección
                            if (selectedCountryId == null || selectedStateId == null || selectedCityId == null) {
                                Toast.makeText(context, "Por favor selecciona todos los campos", Toast.LENGTH_SHORT).show()

                            }else {

                                Log.d("Button", "Botón de guardar presionado else")

                                // Creación del request con los valores seleccionados
                                val request = UpdateProfileRequest(
                                    cell_phone = cellPhone,
                                    country_id = selectedCountryId,
                                    state_id = selectedStateId,
                                    city_id = selectedCityId,
                                    profile_photo = null
                                )

                                Log.d("Button", "Request: cell_phone = $cellPhone, country_id = $selectedCountryId, state_id = $selectedStateId, city_id = $selectedCityId, profile_photo = $profileImageUri")


                                // Llamada al ViewModel para actualizar el perfil
                                updateProfileViewModel.updateUserProfile(context, request)
                            }
                        },
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
                    ) {
                        androidx.compose.material3.Text("Guardar", color = Color.White)
                    }

                }
            }
        )
    }
}


@Composable
fun DrawerHeader() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
            .width(screenWidth / 1.5f)
            .padding(top = 20.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Avatar",
            modifier = Modifier.size(72.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = Modifier.height(12.dp))
        androidx.compose.material3.Text(
            "Alfonso Juan",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        androidx.compose.material3.Text("Ver perfil", fontSize = 14.sp, color = Color.White)
    }
}

@Composable
fun DrawerMenuItem(icon: Painter, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.material3.Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(16.dp))
        androidx.compose.material3.Text(text = label, fontSize = 16.sp, color = Color.White)
    }
}



