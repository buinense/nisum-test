
# nisum-test
Test para postulación cargo develop

## Utilización
- No necesita script de creación de BD, lo hace automático en memoria.
- Para entrar a swagger ingresar a la siguiente url copn el proyecto levantado: http://localhost:8081/swagger-ui/index.html
- El patrón para las password es:
       - Entre 8 y 16 caracteres.
       - Una mayúscula.
       - Una minúscula.
       - Un caracter especial.
  
### Json (body) y endpoint
- http://localhost:8081/api/usuarios/registro (POST)
  ```
  {
      "name": "Juan Rodriguez Z",
      "email": "juan.ro@rodriguez.org",
      "password": "Hun@ter2",
      "phones": [
          {
          "number": "741256",
          "citycode": "7",
          "contrycode": "57"
          },
          {
          "number": "856423",
          "citycode": "8",
          "contrycode": "93"
          }        
      ]
  }
  ```

  ### Información técnica
  - Java 17.
  - maven.
  

