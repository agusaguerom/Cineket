# 🎬 Cineket - Sistema de Reservas de Entradas de Cine

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-green.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)
![Maven](https://img.shields.io/badge/Maven-Build-red.svg)

Cineket es una aplicación de gestión de reservas de entradas para cines, desarrollada con **Spring Boot 4.0.3** y **Java 21**. Permite a los usuarios consultar películas, visualizar funciones disponibles, reservar asientos específicos y administrar sus reservas de manera eficiente.

## 📋 Tabla de Contenidos

- [Características Principales](#características-principales)
- [Arquitectura de la Aplicación](#arquitectura-de-la-aplicación)
- [Modelado de Datos](#modelado-de-datos)
- [Especificación de Endpoints](#especificación-de-endpoints)
- [Configuración](#configuración)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Flujos de Negocio](#flujos-de-negocio)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)

---

## ✨ Características Principales

- ✅ **Gestión de Películas**: Crear, consultar, actualizar y eliminar películas
- ✅ **Administración de Salas**: Gestionar salas de cine con asientos predefinidos
- ✅ **Funciones de Cine**: Programar funciones (película + sala + horario)
- ✅ **Gestión de Asientos**: Visualizar y administrar asientos por sala
- ✅ **Reservas Inteligentes**: 
  - Reservar asientos para funciones específicas
  - Consultar asientos disponibles por función
  - Verificar disponibilidad de asientos
  - Modificar y cancelar reservas
- 🔐 **Persistencia Segura**: Base de datos PostgreSQL con Hibernate/JPA
- 📡 **API REST**: Interfaz RESTful completa con estándares HTTP

---

## 🏗️ Arquitectura de la Aplicación

Cineket sigue una arquitectura **en capas** (Layered Architecture) que promueve la separación de responsabilidades:

```
┌─────────────────────────────────────────────────────────┐
│                    CAPA DE PRESENTACIÓN                  │
│               (Controllers / API REST)                   │
├─────────────────────────────────────────────────────────┤
│                   CAPA DE SERVICIOS                      │
│        (Business Logic / Interfaces de Servicio)        │
├─────────────────────────────────────────────────────────┤
│                 CAPA DE PERSISTENCIA                     │
│            (Repositories / Acceso a Datos)              │
├─────────────────────────────────────────────────────────┤
│                   CAPA DE MODELOS                        │
│                  (Entidades JPA)                         │
├─────────────────────────────────────────────────────────┤
│                    BASE DE DATOS                         │
│                  (PostgreSQL)                            │
└─────────────────────────────────────────────────────────┘
```

### 📊 Componentes por Capa

#### 🎯 **Capa de Presentación (Controllers)**
- `AsientoController` - Gestión de asientos
- `FuncionController` - Gestión de funciones
- `PeliculaController` - Gestión de películas
- `ReservaController` - Gestión de reservas
- `SalaController` - Gestión de salas

#### 🔧 **Capa de Servicios (Services & Interfaces)**

**Servicios Implementados:**
- `PeliculaService` implementa `IPeliculaService`
- `FuncionService` implementa `IFuncionService`
- `SalaService` implementa `ISalaService`
- `AsientoService` implementa `IAsientoService`
- `ReservaService` implementa `IReservaService`

**Responsabilidades:**
- Lógica de negocio principal
- Validaciones de reglas
- Orquestación entre múltiples repositorios
- Manejo de transacciones

#### 💾 **Capa de Persistencia (Repositories)**
- `PeliculaRepository` - CRUD de películas
- `FuncionRepository` - CRUD de funciones
- `SalaRepository` - CRUD de salas
- `AsientoRepository` - CRUD de asientos
- `ReservaRepository` - CRUD y consultas custom de reservas

**Métodos Custom en ReservaRepository:**
```java
// Obtener reservas por función
List<Reserva> findByFuncionId(Long funcionId);

// Obtener asientos disponibles para una función
List<Asiento> findAsientosDisponibles(Long funcionId);

// Verificar si existe una reserva para un asiento en una función
boolean existsByFuncionIdAndAsientoId(Long funcionId, Long asientoId);
```

#### 📦 **Data Transfer Objects (DTOs)**
- `FuncionDto` - Transferencia de datos de funciones con información consolidada
- `ReservaDto` - Transferencia de datos simplificados de reservas

#### 🗂️ **Modelos de Datos (Entidades JPA)**
- `Pelicula` - Película de cine
- `Sala` - Sala de proyección
- `Asiento` - Asiento individual en una sala
- `Funcion` - Función de cine (película en sala a hora específica)
- `Reserva` - Reserva de un asiento para una función

---

## 📊 Modelado de Datos

### Diagrama de Relaciones

```
        PELICULA
            |
            | (1:N) Una película puede tener
            |       múltiples funciones
            |
        FUNCION
            |
            | (1:N) Una función puede tener
            |       múltiples reservas
            |
        RESERVA

   SALA ←→ ASIENTO
    (1:N) Una sala contiene
         múltiples asientos
         
   FUNCION ←→ SALA
    (N:1) Una función ocurre en
         una sala específica
```

**Relaciones Principales:**
- Una **Película** puede tener múltiples **Funciones**
- Una **Función** conecta una **Película** con una **Sala** en fecha/hora específica
- Una **Sala** contiene múltiples **Asientos**
- Una **Reserva** vincula un **Asiento** con una **Función**

### Descripción de Entidades

#### **PELICULA**
Representa una película disponible en el cine. Contiene información básica como el nombre y duración. Una película puede tener varias funciones programadas en diferentes salas y horarios.

**Propósito:** Almacenar el catálogo de películas disponibles para proyectar.

---

#### **SALA**
Representa una sala de proyección del cine con capacidad definida por filas y columnas de asientos. Cada sala es independiente y puede tener múltiples funciones en diferentes horarios.

**Propósito:** Organizar los espacios de proyección disponibles en el cine.

---

#### **ASIENTO**
Representa una posición específica dentro de una sala. Cada asiento pertenece a una única sala y se identifica por su posición (fila y columna).

**Propósito:** Mapear los lugares disponibles que pueden ser reservados para funciones.

---

#### **FUNCION**
Representa una sesión de cine: una película específica proyectándose en una sala específica en una fecha y hora determinada.

**Propósito:** Permitir programar cuándo y dónde se proyecta cada película.

---

#### **RESERVA**
Representa la reserva de un asiento para una función específica. Registra cuál es la persona (o usuario) que reservó un asiento para una proyección.

**Propósito:** Controlar la disponibilidad de asientos y registrar las reservas realizadas.

---

## 📡 Especificación de Endpoints

### 🎥 **PELÍCULAS** (`/api/peliculas`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/peliculas` | Obtener todas las películas | 200 OK (lista) |
| GET | `/api/peliculas/{id}` | Obtener película por ID | 200 OK |
| POST | `/api/peliculas` | Crear nueva película | 201 Created |
| PUT | `/api/peliculas/{id}` | Actualizar película existente | 200 OK |
| DELETE | `/api/peliculas/{id}` | Eliminar película | 204 No Content |

**Campos esperados en POST/PUT:**
- `nombre` (String) - Nombre de la película
- `duracion` (int) - Duración en minutos

---

### 🎪 **SALAS** (`/api/salas`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/salas` | Obtener todas las salas | 200 OK (lista) |
| GET | `/api/salas/{id}` | Obtener sala por ID | 200 OK |
| POST | `/api/salas` | Crear nueva sala | 201 Created |
| PUT | `/api/salas/{id}` | Actualizar sala | 200 OK |
| DELETE | `/api/salas/{id}` | Eliminar sala | 204 No Content |

**Campos esperados en POST/PUT:**
- `nombre` (String) - Nombre o tipo de sala
- `filas` (int) - Número de filas de asientos
- `columnas` (int) - Número de columnas de asientos

---

### 💺 **ASIENTOS** (`/api/asientos`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/asientos` | Obtener todos los asientos | 200 OK (lista) |
| GET | `/api/asientos/sala/{salaId}` | Obtener asientos de una sala | 200 OK (lista) |
| POST | `/api/asientos` | Crear nuevo asiento | 201 Created |
| DELETE | `/api/asientos/{id}` | Eliminar asiento | 204 No Content |

**Campos esperados en POST:**
- `fila` (int) - Número de fila
- `columna` (int) - Número de columna
- `sala` (Object) - Referencia a la sala

---

### 🎞️ **FUNCIONES** (`/api/funciones`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/funciones` | Obtener todas las funciones | 200 OK (lista) |
| GET | `/api/funciones/{id}` | Obtener función por ID | 200 OK |
| POST | `/api/funciones` | Crear nueva función | 201 Created |
| PUT | `/api/funciones/{id}` | Actualizar función | 200 OK |
| DELETE | `/api/funciones/{id}` | Eliminar función | 204 No Content |

**Campos esperados en POST/PUT:**
- `pelicula` (Object) - Referencia a película
- `sala` (Object) - Referencia a sala
- `fechaHora` (LocalDateTime) - Fecha y hora de proyección

---

### 🎟️ **RESERVAS** (`/api/reservas`)

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/reservas` | Obtener todas las reservas | 200 OK (lista) |
| GET | `/api/reservas/{id}` | Obtener reserva por ID | 200 OK |
| POST | `/api/reservas` | Crear nueva reserva | 201 Created |
| PUT | `/api/reservas/{id}` | Actualizar reserva | 200 OK |
| DELETE | `/api/reservas/{id}` | Cancelar reserva | 204 No Content |
| GET | `/api/reservas/funcion/{funcionId}` | Reservas de una función | 200 OK (lista) |
| GET | `/api/reservas/asientos/{funcionId}` | Asientos disponibles | 200 OK (lista) |
| GET | `/api/reservas/exists/{funcionId}/{asientoId}` | Verificar disponibilidad | 200 OK (boolean) |

**Campos esperados en POST/PUT:**
- `funcion` (Object) - Referencia a función
- `asiento` (Object) - Referencia a asiento
- `fechaReserva` (LocalDateTime) - Fecha/hora de la reserva

---



## 🚀 Instalación y Ejecución

### Requisitos Previos
- ✅ Java 21 o superior
- ✅ Maven 3.6 o superior
- ✅ PostgreSQL 12 o superior
- ✅ Git (opcional)

### Pasos de Instalación

#### 1. Clonar o descargar el proyecto
```bash
git clone https://github.com/tu-usuario/cineket.git
cd cineket
```

#### 2. Configurar la base de datos
Editar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cineket
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

#### 3. Crear la base de datos (opcional, Hibernate la crea)
```sql
CREATE DATABASE cineket;
```

#### 4. Compilar el proyecto
```bash
mvn clean compile
```

#### 5. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

O compilar y ejecutar el JAR:
```bash
mvn clean package
java -jar target/cineket-0.0.1-SNAPSHOT.jar
```

#### 6. Verificar que está corriendo
```
La aplicación estará disponible en: http://localhost:8080
```

---

## 📁 Estructura del Proyecto

```
cineket/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/cineket/
│   │   │       ├── CineketApplication.java           # Clase principal de Spring Boot
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── AsientoController.java       # Endpoints para asientos
│   │   │       │   ├── FuncionController.java       # Endpoints para funciones
│   │   │       │   ├── PeliculaController.java      # Endpoints para películas
│   │   │       │   ├── ReservaController.java       # Endpoints para reservas
│   │   │       │   └── SalaController.java          # Endpoints para salas
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── IAsientoService.java         # Interfaz de servicio de asientos
│   │   │       │   ├── IFuncionService.java         # Interfaz de servicio de funciones
│   │   │       │   ├── IPeliculaService.java        # Interfaz de servicio de películas
│   │   │       │   ├── IReservaService.java         # Interfaz de servicio de reservas
│   │   │       │   ├── ISalaService.java            # Interfaz de servicio de salas
│   │   │       │   ├── AsientoService.java          # Implementación de servicio de asientos
│   │   │       │   ├── FuncionService.java          # Implementación de servicio de funciones
│   │   │       │   ├── PeliculaService.java         # Implementación de servicio de películas
│   │   │       │   ├── ReservaService.java          # Implementación de servicio de reservas
│   │   │       │   └── SalaService.java             # Implementación de servicio de salas
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── AsientoRepository.java       # CRUD de asientos
│   │   │       │   ├── FuncionRepository.java       # CRUD de funciones
│   │   │       │   ├── PeliculaRepository.java      # CRUD de películas
│   │   │       │   ├── ReservaRepository.java       # CRUD de reservas (con custom queries)
│   │   │       │   └── SalaRepository.java          # CRUD de salas
│   │   │       │
│   │   │       ├── model/
│   │   │       │   ├── Asiento.java                 # Entidad JPA de asientos
│   │   │       │   ├── Funcion.java                 # Entidad JPA de funciones
│   │   │       │   ├── Pelicula.java                # Entidad JPA de películas
│   │   │       │   ├── Reserva.java                 # Entidad JPA de reservas
│   │   │       │   └── Sala.java                    # Entidad JPA de salas
│   │   │       │
│   │   │       └── dto/
│   │   │           ├── FuncionDto.java              # DTO con datos consolidados de función
│   │   │           └── ReservaDto.java              # DTO simplificado de reserva
│   │   │
│   │   └── resources/
│   │       ├── application.properties               # Configuración de la aplicación
│   │       ├── static/                              # Recursos estáticos (CSS, JS, imágenes)
│   │       └── templates/                           # Plantillas HTML (si aplica)
│   │
│   └── test/
│       └── java/
│           └── com/example/cineket/
│               └── CineketApplicationTests.java    # Tests unitarios
│
├── pom.xml                                          # Configuración de Maven
├── mvnw                                             # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                                         # Maven Wrapper (Windows)
├── README.md                                        # Este archivo
└── HELP.md                                          # Documentación adicional de Spring Boot

```

---

## 🔄 Flujos de Negocio

### 1️⃣ Flujo de Creación de Función

```
┌─────────────────────────────────────────────────────────┐
│ 1. Usuario solicita crear una función (película + sala) │
├─────────────────────────────────────────────────────────┤
│ 2. FuncionController recibe POST /api/funciones         │
├─────────────────────────────────────────────────────────┤
│ 3. FuncionService valida:                              │
│    - Película existe                                    │
│    - Sala existe y tiene asientos                      │
│    - Fecha/Hora es válida                              │
├─────────────────────────────────────────────────────────┤
│ 4. FuncionRepository persiste en BD                    │
├─────────────────────────────────────────────────────────┤
│ 5. Respuesta 201 Created con Funcion creada            │
└─────────────────────────────────────────────────────────┘
```

### 2️⃣ Flujo de Reserva de Asiento

```
┌──────────────────────────────────────────────────────────┐
│ 1. Usuario desea reservar asiento para una función      │
├──────────────────────────────────────────────────────────┤
│ 2. Usuario consulta asientos disponibles:               │
│    GET /api/reservas/asientos/{funcionId}              │
├──────────────────────────────────────────────────────────┤
│ 3. ReservaService.findAsientosDisponibles()            │
│    - Obtiene todos los asientos de la sala              │
│    - Filtra los que NO tienen reserva para la función   │
│    - Retorna lista de asientos disponibles              │
├──────────────────────────────────────────────────────────┤
│ 4. Usuario selecciona asiento y hace reserva:           │
│    POST /api/reservas { funcion, asiento, fechaReserva}│
├──────────────────────────────────────────────────────────┤
│ 5. ReservaService valida:                              │
│    - Asiento no está ya reservado (exists check)       │
│    - Función existe                                     │
│    - Asiento pertenece a la sala de la función         │
├──────────────────────────────────────────────────────────┤
│ 6. ReservaRepository persiste la reserva                │
├──────────────────────────────────────────────────────────┤
│ 7. Respuesta 201 Created con Reserva creada             │
└──────────────────────────────────────────────────────────┘
```

### 3️⃣ Flujo de Cancelación de Reserva

```
┌───────────────────────────────────────────────────────┐
│ 1. Usuario desea cancelar reserva:                   │
│    DELETE /api/reservas/{reservaId}                 │
├───────────────────────────────────────────────────────┤
│ 2. ReservaService.deleteReserva(id)                 │
├───────────────────────────────────────────────────────┤
│ 3. ReservaRepository elimina de BD                  │
├───────────────────────────────────────────────────────┤
│ 4. Respuesta 204 No Content                         │
│    (Asiento vuelve a estar disponible)              │
└───────────────────────────────────────────────────────┘
```

### 4️⃣ Flujo de Administración de Salas y Asientos

```
┌──────────────────────────────────────────────────────────┐
│ 1. Administrador crea sala:                             │
│    POST /api/salas { nombre, filas, columnas }         │
├──────────────────────────────────────────────────────────┤
│ 2. SalaService persiste sala                           │
├──────────────────────────────────────────────────────────┤
│ 3. Administrador genera asientos para sala:             │
│    POST /api/asientos (múltiples)                      │
│    Crea filas × columnas asientos                      │
├──────────────────────────────────────────────────────────┤
│ 4. AsientoService persiste cada asiento                │
├──────────────────────────────────────────────────────────┤
│ 5. Sala lista para funciones                            │
└──────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tecnologías Utilizadas

### Backend
| Tecnología | Versión | Propósito |
|-----------|---------|----------|
| **Java** | 21 | Lenguaje de programación |
| **Spring Boot** | 4.0.3 | Framework principal |
| **Spring Data JPA** | 4.0.3 | ORM y acceso a datos |
| **Spring Web MVC** | 4.0.3 | Controladores REST |
| **Hibernate** | 6.x | ORM implementación de JPA |
| **PostgreSQL** | 12+ | Base de datos relacional |
| **Jakarta Persistence** | 3.1 | Especificación JPA |

### Build & Herramientas
| Herramienta | Propósito |
|-----------|----------|
| **Maven** | Gestor de dependencias y build |
| **Maven Wrapper** | Maven sin instalación previa |

---

## 📝 Notas de Desarrollo

### Mejoras Futuras Sugeridas

1. **Autenticación y Autorización**
   - Implementar Spring Security
   - JWT para autenticación stateless
   - Roles: ADMIN, USER, EMPLOYEE

2. **Validación Mejorada**
   - Usar Bean Validation (Jakarta Validation)
   - Anotaciones @Valid en DTOs
   - Manejo centralizado de excepciones

3. **Documentación API**
   - Integrar Springdoc OpenAPI (Swagger)
   - Generar documentación automática

4. **Performance**
   - Implementar caché con Redis
   - Paginación en listados
   - Lazy loading optimization

5. **Pruebas**
   - Pruebas unitarias con JUnit 5
   - Pruebas de integración
   - Mockito para mocks

6. **Seguridad**
   - HTTPS obligatorio
   - CORS configurado
   - Validación de entrada completa

7. **Transacciones**
   - Manejar excepciones de concurrencia
   - Implementar optimistic locking

---

## 📞 Soporte

Para más información sobre Spring Boot, visita:
- [Documentación oficial Spring Boot](https://spring.io/projects/spring-boot)
- [Guía de referencia JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

**Última actualización:** 3 de Marzo de 2026  
**Versión:** 0.0.1-SNAPSHOT




