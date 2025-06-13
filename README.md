# TP5 - Docker Full Stack con PostgreSQL

## ¿Qué es este proyecto?

Un sistema web completo que usa *Docker* para ejecutar automáticamente:
- *Frontend*: Página web hecha en React
- *Backend*: API hecha en Spring Boot  
- *Base de datos*: PostgreSQL para guardar información
- *Administrador*: PgAdmin para ver la base de datos

## Cómo usar el proyecto

### 1. Requisitos
- Tener Docker instalado en tu computadora
- Descargar este proyecto

### 2. Ejecutar todo
bash
# Abrir terminal en la carpeta del proyecto
docker-compose up --build


### 3. Abrir en el navegador
- *Página principal*: http://localhost:3000
- *API*: http://localhost:8080  
- *Administrador de BD*: http://localhost:8081

## Qué hace cada servicio

| Servicio | Puerto | Para qué sirve |
|----------|--------|----------------|
| *React* | 3000 | La página web que ves |
| *Spring Boot* | 8080 | Procesa los datos y se conecta a la BD |
| *PostgreSQL* | 5432 | Guarda la información |
| *PgAdmin* | 8081 | Ver y administrar la base de datos |

## Cómo funciona

1. *Escribes tu nombre* en la página web
2. *Haces clic en "Saludar"*
3. *Se guarda automáticamente* en PostgreSQL
4. *Aparece en la lista* de saludos guardados

## Comandos útiles de Docker

bash
# Ejecutar todo
docker-compose up --build

# Parar todo
docker-compose down

# Ver qué está corriendo
docker ps

# Ver mensajes de error
docker-compose logs


## Estructura del proyecto


proyecto/
├── backend/          # Código de Spring Boot
├── frontend/         # Código de React
├── docker-compose.yml # Configuración de Docker
└── README.md         # Este archivo


## Si algo no funciona

### Problema: No se abre la página
- Esperar 2-3 minutos después de ejecutar docker-compose up
- Verificar que no haya errores en la terminal

### Problema: Error de puerto ocupado
bash
# Parar Docker y volver a ejecutar
docker-compose down
docker-compose up --build


### Problema: No se conecta a la base de datos
- Verificar que PostgreSQL esté corriendo: docker ps
- Reiniciar todo: docker-compose down y docker-compose up --build

## Tecnologías usadas

- *Docker*: Para ejecutar todo automáticamente
- *React*: Para la página web
- *Spring Boot*: Para el servidor
- *PostgreSQL*: Para guardar datos
- *Maven*: Para compilar Java
