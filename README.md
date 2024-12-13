# devsu-prueba

1. Clonar el repositorio en una ruta de su preferencia.
2. Abrir y compilar los proyectos (prueba, account) en su IDE de preferencia.
3. Verificar que los proyectos se hayan compilado sin errores, esto para asegurarnos que los jar se han generado de forma satisfactoria.
4. abrir una ventana de comandos "CMD", en la ruta origen del repositorio, donde se encuentra localizado el archivo "docker-compose".
5. ejecutar en la ventana de comandos: docker-compose up --build
6. Verificar que se hayan creado las respectivas imágenes (microservicio-1, microservicio-2, mcr.microsoft.com/mssql/server) y el respectivo cotenedor con las imágenes.
7. El archivo "api-devsu.postman_collection" contiene el collection para ejecutar los end-points de los microservicios.
8. Importar el archivo  "api-devsu.postman_collection" en su Postman y ejecutar el collection.
9. El script con el equema de la base se encuentra en la ruta raíz del repositorio con el nombre: "schema.sql"
10. Para esta prueba se crearon dos microservicios que se comunican de forma sincrónica. a través de WebClient con WebFlux.