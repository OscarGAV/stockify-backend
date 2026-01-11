# Stockify API

API REST para gestión de inventario construida con Spring Boot y utilizando arquitectura hexagonal, DDD y CQRS

## Tecnologías

- Java 17
- Spring Boot 3.5.7
- Spring Security + JWT
- PostgreSQL
- Swagger/OpenAPI
- Lombok

## Documentación API

Una vez ejecutada la aplicación, accede al enlace de la documentación API Rest:

**Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Bounded Contexts

- Shared -> Genérico (Código que puede ser 100% Reutilizable)
- IAM -> Genérico (Código que puede ser 100% reutilizable)
- Product Management -> Core

# Consideraciones Importantes

Product Management si bien es core bussiness (un bounded context principal del negocio) también puede ser tomado como base para desarrollar otros bounded context similares que utilicen métodos CRUD

- CRUD = Create, Read, Update, Delete
