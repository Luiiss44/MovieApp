# MovieApp

## Descripción general
MovieApp es una aplicación móvil para Android que permite a los usuarios registrarse e iniciar sesión mediante correo electrónico y contraseña. Una vez autenticados, pueden explorar un catálogo de películas en una interfaz moderna e intuitiva. El proyecto utiliza componentes de Jetpack como Navigation, View Binding y Material Design, integrando datos reales a través de una API de películas.

## Funcionalidades principales
- **Registro de usuario:** Creación de cuentas con validación de campos.
- **Inicio de sesión:** Autenticación segura con feedback para el usuario.
- **Consumo de API externa (TMDb):** Se obtienen datos reales de películas mediante peticiones HTTP.
- **Listado de películas:** Interfaz visual con `RecyclerView` para explorar títulos.
- **Detalle de película:** Pantalla individual con información ampliada.
- **Favoritos:** Posibilidad de marcar películas como favoritas y consultarlas aparte.
- **Diseño adaptable:** Uso de Material Design y temas personalizados.
- **Toolbar dinámico:** Con navegación contextual y títulos personalizados.

## Tecnologías utilizadas
- **Lenguaje:** Kotlin
- **Arquitectura:** Android Jetpack (Navigation Component, ViewModel, LiveData)
- **UI:** Material Components, View Binding, Toolbar, RecyclerView
- **Backend:** Firebase Authentication (Email/Password)
- **Datos:** API de [The Movie Database (TMDb)](https://www.themoviedb.org/)
- **Build System:** Gradle (Kotlin DSL)
- **IDE:** Android Studio

## Instalación y ejecución local
1. **Clonar el repositorio:**  
   ```bash
   git clone https://github.com/Luiiss44/MovieApp.git
   ```

2. **Abrir en Android Studio:**  
   Selecciona `File > Open` y abre la carpeta del proyecto.

3. **Configurar Firebase:**  
   - Crea un proyecto en [Firebase Console](https://console.firebase.google.com/)
   - Habilita el método de autenticación por Email/Password.
   - Descarga el archivo `google-services.json` y colócalo en el directorio `/app`.

4. **Configurar API TMDb:**  
   - Regístrate en [TMDb](https://www.themoviedb.org/documentation/api) y obtén una API Key.
   - Añádela al proyecto en el lugar correspondiente (generalmente en `gradle.properties` o como constante segura en código).

5. **Sincronizar y compilar:**  
   Asegúrate de sincronizar dependencias de Gradle y ejecuta la app en un emulador o dispositivo físico.

## Mejoras futuras
- **Cierre de sesión y recuperación de contraseña** mediante Firebase.
- **Paginación en la lista de películas** para mejorar el rendimiento.
- **Soporte multilenguaje** para una audiencia global.
- **Filtros y búsqueda avanzada** dentro del catálogo.
- **Modo oscuro y personalización del tema.**
- **Pruebas unitarias y de interfaz** para mejorar la calidad del software.
- **Publicación oficial en Google Play.**

## Créditos y licencia
Desarrollado por [@Luiiss44](https://github.com/Luiiss44).  
Este proyecto no incluye licencia explícita. Para uso, distribución o colaboración, por favor contacta al autor.
