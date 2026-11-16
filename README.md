# Kata 4 - Cliente para consumir un servicio HTTP

## Objetivo de la entrega
Construir un cliente HTTP en Java con arquitectura limpia que consuma un servicio web de cálculo de series de Fibonacci, deserialice la respuesta JSON en objetos de dominio (`FibonacciResponse`) y gestione de forma controlada errores de red o códigos HTTP inválidos.

## Estructura de la entrega y clases principales
* **`architecture.domain.Series` / `FibonacciResponse`**: Modelos de datos inmutables (Records).
* **`architecture.ports.FibonacciClient`**: Puerto de servicio que define el contrato de consulta HTTP devolviendo un `Optional`.
* **`architecture.ports.Input` / `Output` / `SeriesSerializer`**: Puertos para desacoplar la lógica de negocio del servidor y la serialización.
* **`architecture.usecases.CalculateFibonacciSeriesUseCase`**: Caso de uso encargado de generar la serie matemática.
* **`infrastructure.adapters.HttpFibonacciClient`**: Adaptador HTTP implementado con `java.net.http.HttpClient` y `Gson` que traduce errores de conexión o estados distintos de 200 en `Optional.empty()`.
* **`infrastructure.adapters.ContextInput` / `GsonSeriesSerializer`**: Adaptadores de entrada y salida para Javalin y Gson.
* **`infrastructure.server.FibonacciServer`**: Encapsulación del servidor web Javalin en el puerto 8080.
* **`Main`**: Punto de entrada que configura la URL base, arranca el servidor local, ejecuta las pruebas del cliente y detiene el servicio.

## Cómo compilar y ejecutar
1. Clonar el repositorio: `git clone https://github.com/Matin-Marefat-Halan/kata4.git`
2. Compilar el proyecto con Maven: `mvn clean compile` (o recargando Maven desde IntelliJ IDEA).
3. Ejecutar la clase `software.ulpgc.katas.Main` (el servidor Javalin se levanta y detiene automáticamente durante la ejecución).

## Dependencias y versión de JDK
* **JDK**: OpenJDK 27
* **Gestor de dependencias**: Maven
* **Librerías**:
    * Javalin (`io.javalin:javalin:7.2.4`)
    * Gson (`com.google.code.gson:gson:2.15.0`)
    * SLF4J Simple (`org.slf4j:slf4j-simple:2.0.20`)

## Proceso de Repetición Deliberada
La kata se ha desarrollado en 3 iteraciones mediante ramas independientes antes de integrarse en `master`:
* **`develop1`**: Implementación base de la arquitectura limpia, servidor Javalin en el endpoint `/fibonacci` y consulta exitosa de 10 términos.
* **`develop2`**: Variación en la consulta (15 términos) e incorporación de manejo controlado ante servicio no disponible (puerto inactivo).
* **`develop3`**: Validación de errores de protocolo HTTP (ruta inexistente con respuesta 404) y consolidación de la presentación.

## Evidencias de verificación
Durante la ejecución de `Main` se verifican tres escenarios:
1. **Petición exitosa (`GET /fibonacci?n=8`)**: Devuelve código `200 OK` y muestra `[0, 1, 1, 2, 3, 5, 8, 13]`.
2. **Servicio no disponible (`http://localhost:9999`)**: Captura el fallo de conexión y muestra un mensaje controlado sin detener abruptamente la aplicación.
3. **Endpoint inexistente (`http://localhost:8080/api/v2`)**: Detecta el código HTTP `404` y retorna `Optional.empty()` mostrando un aviso comprensible.

## Vídeo explicativo
https://drive.google.com/file/d/1Siz9SI19Zaew4ChYzJzB4TDVc_-cfP_W/view?usp=sharing