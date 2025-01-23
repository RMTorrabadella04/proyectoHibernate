# Proyecto Hibernate - Refugio de Animales Perdidos

## Descripción
Este proyecto implementa una aplicación de consola en Java utilizando la tecnología Hibernate para gestionar un refugio de animales perdidos. El sistema permite registrar nuevos animales, buscar animales por especie, actualizar su estado (recién abandonado, en el refugio o próximamente en acogida), y registrar los datos de la familia que los acoge.

## Estructura de Paquetes
El proyecto está organizado en los siguientes paquetes:
- **entities**: Contiene las entidades del modelo de datos (Animales, Familia, Adopción).
- **dao**: Contiene las clases de acceso a datos (consultas a la base de datos).
- **service**: Contiene la lógica de negocio (registrar animales, actualizar estado, etc.).
- **tests**: Contiene las pruebas unitarias de las clases más críticas.
- **hibernate**: Configuración de Hibernate y utilidades para la sesión.

## Funcionalidades
### 1. Registrar nuevos animales
Los animales pueden ser registrados en el sistema, incluyendo su nombre, especie, edad y descripción de cómo se perdieron. Los tipos de animales disponibles incluyen: Perros, Gatos, Pájaros, Cerdos vietnamitas, Serpientes, Camaleones y Arañas.

### 2. Buscar animales por especie
Se pueden consultar los animales que pertenecen a una especie específica.

### 3. Actualizar el estado de los animales
Cada animal tiene un estado que puede ser uno de los siguientes:
- **RecienAbandonado**
- **TiempoEnElRefugio**
- **ProximamenteEnAcogida**

### 4. Insertar datos de la familia que acoge al animal
Se pueden registrar las familias que acogen a los animales, con los siguientes datos:
- Nombre de la familia
- Edad
- Ciudad

### 5. CRUD (Crear, Leer, Actualizar, Eliminar)
Se implementan operaciones CRUD para manejar los animales, las adopciones y las familias.

## Diagrama de la Base de Datos
La estructura de la base de datos está compuesta por las siguientes tablas:

1. **animales**
   - `id` (Primary Key)
   - `nombre`
   - `especie`
   - `edad`
   - `descripcion_perdida`
   - `estado_animal`

2. **familia**
   - `id` (Primary Key)
   - `nombre`
   - `edad`
   - `ciudad`

3. **adopcion**
   - `id_adopcion` (Primary Key)
   - `id_familia` (Foreign Key)
   - `id_animal` (Foreign Key)


