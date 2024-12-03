package com.example.justicelawmovil.navigation

enum class Screen { // Names to identify the screens
    LOGIN,
    REGISTER,
    HOME,
    USER_LIST,
    USER_DETAIL,
    PROFILE,
    VER_PERFIL_ABOGADO,
    PERFIL_ABOGADO,
    HISTORIAL,
    CONFIGURACION,
    INFORMACION,
    NOTIFICATION,
    OLVIDO_CONTRASENA,
    OLVIDO_CONTRASENA_EMAIL,
    OLVIDO_CONTRASENA_TELEFN,
    OLVIDO_CONTRASENA_COD_EMAIL,
    OLVIDO_CONTRASENA_COD_TELEFN,
    OLVIDO_CONTRASENA_NUEVA,
    REESTABLECIMIENTO_EXITOSO,
    PERFIL_RESPUESTA_ABOGADO,
    FORO,
    QUIENES_SOMOS,
    CALENDARIO,
    DIVORCIO,
    REGISTER_LAWYER,
}

// Objects to identify where navigate
sealed class NavigationItem(val route: String) {
    object Login: NavigationItem(Screen.LOGIN.name)
    object Register: NavigationItem(Screen.REGISTER.name)
    object Home: NavigationItem(Screen.HOME.name)
    object UserList: NavigationItem(Screen.USER_LIST.name) // Nueva ruta
    object UserDetail: NavigationItem(Screen.USER_DETAIL.name + "/{usuarioId}")
    object Profile: NavigationItem(Screen.PROFILE.name)
    object VerPerfilAbogado: NavigationItem(Screen.VER_PERFIL_ABOGADO.name)
    object PerfilAbogado: NavigationItem(Screen.PERFIL_ABOGADO.name)
    object Historial: NavigationItem(Screen.HISTORIAL.name)
    object Configuracion: NavigationItem(Screen.CONFIGURACION.name)
    object Informacion:NavigationItem(Screen.INFORMACION.name)
    object Notification:NavigationItem(Screen.NOTIFICATION.name)
    object OlvidoContrasena:NavigationItem(Screen.OLVIDO_CONTRASENA.name)
    object OlvidoContrasenaEmail:NavigationItem(Screen.OLVIDO_CONTRASENA_EMAIL.name)
    object OlvidoContrasenaTelefn:NavigationItem(Screen.OLVIDO_CONTRASENA_TELEFN.name)
    object OlvidoContrasenaCodEmail:NavigationItem(Screen.OLVIDO_CONTRASENA_COD_EMAIL.name)
    object OlvidoContrasenaCodTel:NavigationItem(Screen.OLVIDO_CONTRASENA_COD_TELEFN.name)
    object OlvidoContrasenaNueva:NavigationItem(Screen.OLVIDO_CONTRASENA_NUEVA.name)
    object ReestablecimientoExitoso:NavigationItem(Screen.REESTABLECIMIENTO_EXITOSO.name)
    object PerfilRespuestaAbogado:NavigationItem(Screen.PERFIL_RESPUESTA_ABOGADO.name)
    object Foro:NavigationItem(Screen.FORO.name)
    object QuienesSomos:NavigationItem(Screen.QUIENES_SOMOS.name)
    object Calendario:NavigationItem(Screen.CALENDARIO.name)
    object Divorcio:NavigationItem(Screen.DIVORCIO.name)
    object RegisterLawyer:NavigationItem(Screen.REGISTER_LAWYER.name)
}