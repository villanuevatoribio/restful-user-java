# Api app-user (Creación de usuarios)

Este proyecto es una API RESTful para la creación de usuarios utilizando autorización con JWT.

Se trata de un servicio que expone dos endpoints: `/api/v1/auth` y `/api/v1/user`.

Se ha implementado con Spring Boot, Spring Data JPA, Spring Security, JWT, H2, Lombok y OpenApi.

## Pre-Requisitos
1. Java 17
2. Plugin Lombok (en su IDE Intellij IDEA o STS)
3Maven


## Ejecutar API RESTful de manera local

1. Clonar el repositorio de manera local y ubicarse en la carpeta raíz, por comandos era : ```git clone ""```

2. Ejecutar un `clean` e `install` con maven, ya se por comandos o desde un IDE. Esto permitirá que se generen las clases del contrato con  OpenApi, por comandos sería: ```mvn clean install```

3. Inicializar el servicio, esto varía según el IDE a utilizar, por comandos de maven sería: ```mvn spring-boot:run```

## Ejecución de Pruebas

### Postman

Se ha creado un `collection` en postman para facilitar las pruebas. Dentro de la carpeta `data` se encuentran los archivos json que se deben importar a la herramienta Postman.

### Caso Exitoso

1. Primero debemos autenticarnos para obtener un JWT.

Ejecutar un POST a la uri `/api/v1/auth` con el siguiente body (json) para obtener un token JWT:

```
{
    "email": "wvillanueva@dominio.cl",
    "password": "Willy2410%"
}
```


2. Ahora vamos a crear un usuario.

Ejecutar un POST a la uri `/api/v1/user` con el header:

```
Authorization: Bearer <jwt>
```

**Nota: Reemplazar `<jwt>` por el token obteniendo en el paso anterior.**


También agregar el siguiente body (json):

```
{
    "name": "Luis Villanueva",
    "email": "Lvillanueva@dominio.cl",
    "password": "Luis1234%",
    "phones": [
        {
            "number": "555555555",
            "cityCode": "1",
            "countryCode": "51"
        },
        {
            "number": "666666666",
            "cityCode": "1",
            "countryCode": "51"
        }
    ]
}
```

**Nota: Para validar la creación del usuario se puede ingresar a la consola de H2. Se debe pegar la url: `http://localhost:9090/h2-console` en el navegador cambiar y cambiar los siguientes valores:**

* JDBC URL = `"jdbc:h2:mem:usuarios`
* User name = `nisum`
* Password = `123456`

### Casos de Error

1. **Forbidden**. Cuando se intenta ejecutar `/api/v1/user` sin un token válido.
2. **Unauthorized**. Cuando se ingresa mal email y/o password en `/api/v1/auth`. También cuando el usuario no está activo. Se tiene cargado un usuario inactivo de ejemplo:
```
{
    "email": "lvillanueva@dominio.cl",
    "password": "Luis1234"
}
```
3. **Conflict**. Cuando se intenta enviar un email que ya existe al crear un usuario con la uri `/api/v1/user`.
4. **Bad Request**. Cuando el email y/o password no tienen el formato correcto en `/api/v1/auth`

## Swagger!
**Para validar que está ejecutándose, se puede pegar la siguiente url en el navegador `http://localhost:9090/swagger-ui/index.html` para que se muestre el contrato de la API RESTful`**


### Arquitectura

Se ha creado un `diagrama de solucion` con el nombre de diagrama_api_user.pgn dentro de la carpeta `data`.