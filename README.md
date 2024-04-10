# Sistema de fidelización de clientes

Implementación de un sistema de fidelización de clientes que hace seguimiento de los puntos otorgados por operaciones de pago.


**Colaboradores**

* Antonio Insfrán
* Alejandra Lezcano
* Pablo Caballero
* José Figueredo
* Aldo Gonzalez

## Requisitos
* Java 8 (JDK 1.8)
* Maven
* PostgreSQL 12.18

## Herramientas
*IDE de desarrollo (opcional, pero recomendado) como IntelliJ IDEA.
*PgAdmin 4 (opcional) o ejecutar todo desde la linea de comandos.

## Configuracion
*Clonar el repositorio
```git clone https://github.com/lezcanoale/tp1-backend.git```

*Usar el script sql ```tp1-backend.sql``` para importar el esquema de la bd (el usuario debe ser.


## Ejecutar
#### 1. Compilar el proyecto 

```mvn clean install```
#### 2. Ejecutar el postgresql para hacer correr la bd (en el puerto: 5633, con usuario: postgres, pass:1234).


#### 3.  Ejecutar la aplicación ya sea por algun IDE o por comandos mvn:

```mvn spring-boot:run```

