# denunciaservice

API REST para registrar denuncias de inseguridad ciudadana ante serenazgo.

## Requisitos
- Java 17+
- MySQL local en `localhost:3306` (usuario `root` por defecto)

## Ejecutar
Define la contraseña de MySQL como variable de entorno y ejecuta la aplicación:

- Windows (PowerShell): `$env:DB_PASSWORD="tu_clave"` y luego `mvn spring-boot:run`
- IntelliJ: Run Configuration > Environment variables > `DB_PASSWORD=tu_clave`

La base de datos `denunciaservice` se crea automáticamente y la tabla `denuncias` se genera con JPA.

## Endpoints
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/denuncias/` | Registra una denuncia (estado = 1) |
| GET | `/denuncias/?offset=0&limit=5` | Lista paginada |
| GET | `/denuncias/{id}` | Detalle de una denuncia |
| GET | `/denuncias/ciudadano/{dni}` | Denuncias de un ciudadano |
| PUT | `/denuncias/{id}` | Marca como atendida (estado 1 -> 0) |

Ejemplo de cuerpo para POST:
```json
{
  "dni": "12345678",
  "fecha": "2026-10-28T09:00:00",
  "tipo": "ROBO",
  "direccion": "Av. Principal 123",
  "descripcion": "Robo de celular en la vía pública"
}
```
