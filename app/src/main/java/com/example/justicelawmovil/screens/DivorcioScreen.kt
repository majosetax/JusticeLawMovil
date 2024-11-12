package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.justicelawmovil.R
import com.example.justicelawmovil.navigation.NavigationItem
import kotlinx.coroutines.launch

@Composable
fun DivorcioScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuIcon: Painter = painterResource(id = R.drawable.menu)
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val forumIcon: Painter = painterResource(id = R.drawable.forum)
    val profileIcon: Painter = painterResource(id = R.drawable.profile)
    val notificationIcon: Painter = painterResource(id = R.drawable.notifications)
    val settingsIcon: Painter = painterResource(id = R.drawable.settings)
    val quienesSomos: Painter = painterResource(id = R.drawable.quienes_somos)
    val historialIcon: Painter = painterResource(id = R.drawable.historial)
    val helpIcon: Painter = painterResource(id = R.drawable.help)

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
            DrawerMenuItem(icon = notificationIcon, label = "Notificaciones") {
                navController.navigate(NavigationItem.Profile.route)
            }
            DrawerMenuItem(icon = historialIcon, label = "Historial") {
                navController.navigate(NavigationItem.Historial.route)
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            DrawerMenuItem(icon = settingsIcon, label = "Configuración") {
                navController.navigate(NavigationItem.Configuracion.route)
            }
            DrawerMenuItem(icon = quienesSomos, label = "Quienes Somos") {
                navController.navigate(NavigationItem.Configuracion.route)
            }
            DrawerMenuItem(icon = helpIcon, label = "Ayuda") {
                navController.navigate(NavigationItem.Profile.route)
            }
        }
    }

    @Composable
    fun DrawerHeader() {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        Column(
            modifier = Modifier
                .width(screenWidth / 1.5f)
                .padding(top = 20.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Avatar",
                modifier = Modifier.size(72.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("Alfonso Juan", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            Text("Ver perfil", fontSize = 14.sp, color = Color.White)
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
            Icon(painter = icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.White)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontSize = 16.sp, color = Color.White)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(painter = menuIcon, contentDescription = "Menu", modifier = Modifier.size(24.dp))
                        }
                    },
                    backgroundColor = Color.White,
                    modifier = Modifier.height(90.dp),
                    elevation = 0.dp
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    containerColor = Color(0xFF003049),
                    contentColor = Color.White
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        IconButton(onClick = { navController.navigate(NavigationItem.Home.route) }) {
                            Icon(painter = homeIcon, contentDescription = "Home", tint = Color.White)
                        }
                        IconButton(onClick = { /* Acción de búsqueda */ }) {
                            Icon(painter = searchIcon, contentDescription = "Search", tint = Color.White)
                        }
                        IconButton(onClick = { /* Acción del foro */ }) {
                            Icon(painter = forumIcon, contentDescription = "Foro", tint = Color.White)
                        }
                    }
                }
            },
            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    ArticleDetailScreen()
                }
            }
        )
    }
}

@Composable
fun ArticleDetailScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Guía sobre Divorcio",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "14 agosto 2024",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            ClickableText(
                text = AnnotatedString("Categoría: Familiar"),
                onClick = { /* Acción al hacer clic en la categoría */ },
                style = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = Color.Blue
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.divorcio),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "El divorcio es un proceso legal que pone fin a un matrimonio, disolviendo el vínculo civil entre los cónyuges. Este procedimiento implica la separación formal y definitiva de las partes, permitiéndoles volver a casarse si lo desean. El divorcio puede ser de mutuo acuerdo, donde ambas partes consensúan los términos de la separación, o contencioso, cuando una de las partes no está de acuerdo o no acepta los términos propuestos. Además de la disolución del matrimonio, el divorcio también abarca aspectos como la custodia de los hijos, la división de bienes y la manutención",
                fontSize = 16.sp,
                color = Color.Black,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Agrega más contenido aquí si es necesario
            Text(
                text = "Presentación de un divorcio\n" +
                        "La presentación de un divorcio implica varios pasos clave. Primero, se recomienda consultar con un abogado para comprender los derechos y obligaciones. Luego, se prepara una demanda de divorcio que incluye la información básica del matrimonio, el motivo del divorcio, y las solicitudes específicas, como la custodia de los hijos y la división de bienes. Esta demanda se presenta en el tribunal correspondiente, y se notifica al otro cónyuge. Si ambas partes están de acuerdo, negocian los términos; de lo contrario, el caso puede ir a juicio. Finalmente, el juez emite un decreto que formaliza la disolución del matrimonio.\n" +
                        "\n" +
                        "Requisitos y Proceso\n" +
                        "Para presentar una solicitud de divorcio, es necesario cumplir con ciertos requisitos básicos. Primero, uno de los cónyuges debe cumplir con el requisito de residencia, es decir, haber vivido en la jurisdicción donde se presentará la solicitud por un período de tiempo específico, que varía según la región. Segundo, se debe presentar una demanda de divorcio que incluya información detallada sobre el matrimonio, como la fecha y lugar de la boda, así como los nombres de ambos cónyuges. Tercero, es necesario establecer una causa legal para el divorcio, que puede ser de mutuo acuerdo, diferencias irreconciliables, abandono, adulterio, entre otros motivos reconocidos por la ley. Además, se deben cumplir con las obligaciones financieras correspondientes, como el pago de las tarifas de presentación en el tribunal. Finalmente, es importante notificar formalmente al otro cónyuge sobre la presentación de la demanda de divorcio, garantizando que ambas partes estén al tanto del proceso legal en curso.\n" +
                        "\n" +
                        "Sentencia y Revisión\n" +
                        "Sentencia de Divorcio: La sentencia es la decisión final del juez sobre el divorcio. Una vez que se han presentado todos los documentos, se han escuchado las pruebas y se han considerado los argumentos de ambas partes, el juez emite una sentencia que pone fin oficialmente al matrimonio. Esta sentencia incluye las disposiciones sobre la custodia de los hijos, la manutención conyugal, la división de bienes y cualquier otra cuestión relevante. En casos donde ambas partes han llegado a un acuerdo, la sentencia reflejará los términos acordados. Si no hubo acuerdo, el juez tomará decisiones sobre los aspectos en disputa. Revisión del Divorcio: Después de emitida la sentencia, cualquiera de las partes puede solicitar una revisión si cree que hubo un error en la decisión o si surgen nuevas circunstancias que lo ameriten. Esta revisión puede llevarse a cabo ante el mismo tribunal que emitió la sentencia original o ante una instancia superior. El objetivo es evaluar si la sentencia fue justa y acorde a la ley. Durante la revisión, el tribunal puede confirmar, modificar o incluso anular la sentencia original, dependiendo de los argumentos presentados.\n" +
                        "\n" +
                        "Tipos de divorcio:\n" +
                        "Divorcio de Mutuo Acuerdo\n" +
                        "Ocurre cuando ambos cónyuges están de acuerdo en divorciarse y consensúan los términos de la separación, como la custodia de los hijos, la división de bienes y la manutención. Es generalmente un proceso más rápido y menos costoso, ya que se evita un juicio prolongado.\n" +
                        "Divorcio Contencioso\n" +
                        "Se da cuando uno de los cónyuges no está de acuerdo con el divorcio o no acepta los términos propuestos por el otro. Este tipo de divorcio suele ser más complicado y largo, ya que puede implicar un juicio donde el juez tomará decisiones sobre los términos del divorcio.\n" +
                        "El nombramiento del tutor generalmente recae en un familiar cercano del menor, como un tío, abuelo o hermano mayor. Sin embargo, si no hay familiares adecuados disponibles o si la situación lo requiere, el juez puede designar a una persona que considere idónea para el rol de tutor. Esta designación se realiza tras una evaluación detallada de la situación del menor para asegurar que el tutor elegido sea el más adecuado para proteger sus intereses.\n" +
                        "Divorcio por Causales\n" +
                        "En este tipo de divorcio, una de las partes solicita la disolución del matrimonio basándose en una causa específica reconocida por la ley, como el adulterio, la violencia doméstica, el abandono, entre otros. Es necesario probar la causa alegada ante el tribunal, lo que puede requerir evidencia y testigos.\n" +
                        "Es un tipo de divorcio donde no es necesario demostrar la culpa de uno de los cónyuges. Basta con que uno de los cónyuges declare que el matrimonio es irreparable. Es un proceso más sencillo en términos de pruebas, ya que no requiere demostrar un motivo específico más allá de la irreconciliabilidad de las diferencias.",
            )
        }
        item {
            RecommendedSection()
        }
    }
}
@Composable
fun RecommendedSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Text(
            text = "Recomendados",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // Botones de filtros
            Button(
                onClick = { /* Acción para "Último" */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4C553)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
            ) {
                Text(text = "Último", color = Color.White,fontWeight = FontWeight.Bold, fontSize = 10.sp)
            }
            Button(
                onClick = { /* Acción para "Más Recomendado" */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF003049)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .width(160.dp)
                    .height(40.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = "Más Recomendado", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 10.sp)
            }
            Button(
                onClick = { /* Acción para "Más Visto" */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF003049)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .width(110.dp)
                    .height(40.dp)
            ){
                Text(text = "Más Visto", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 10.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de recomendaciones
        val recommendations = listOf(
            RecommendationItem(
                title = "Cómo enfrentar problemas de inquilino",
                description = "Consejos legales para resolver problemas con inquilinos.",
                imageRes = R.drawable.inquilinos
            ),
            RecommendationItem(
                title = "Cómo redactar un testamento",
                description = "Guía paso a paso para redactar un testamento válido.",
                imageRes = R.drawable.testamento
            ),
            RecommendationItem(
                title = "Derechos del trabajador",
                description = "Conoce tus derechos laborales y cómo defenderlos.",
                imageRes = R.drawable.trabajador
            ),
            RecommendationItem(
                title = "Pensión Alimenticia",
                description = "Información sobre cómo solicitar pensión alimenticia.",
                imageRes = R.drawable.pension
            )
        )

        recommendations.forEach { recommendation ->
            RecommendedItem(recommendation)
        }
    }
}


data class RecommendationItem(
    val title: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun RecommendedItem(recommendation: RecommendationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = recommendation.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = recommendation.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = recommendation.description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }

    Divider(color = Color.LightGray)
}

@Preview(showBackground = true)
@Composable
fun PreviewDivorcio() {
    DivorcioScreen(navController = rememberNavController())
}
