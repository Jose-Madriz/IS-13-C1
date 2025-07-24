## Registro Test cases

|   Caso de Prueba | Descripción | Cédula de Identidad | Contraseña | Resultado Esperado |
| ---------------|-------------|---------------------|------------|--------------------|
| CP-001 | Registro exitoso | 12345678 | contraseña_segura | La cuenta debe ser creada y el usuario debe ser redirigido a la página de confirmación. |
| CP-002 | Cédula ya registrada | 98765432 | contraseña_segura | Se debe mostrar un mensaje de error: "Esta cédula ya está registrada. Por favor, inicia sesión o recupera tu cuenta". |
| CP-003 | Cédula con letras | 1234A567 | contraseña_segura | La aplicación debe rechazar la entrada y mostrar un mensaje de validación: "La cédula debe contener solo números". |
| CP-004 | Cédula con caracteres especiales | 12.345.678 | contraseña_segura | La aplicación debe rechazar la entrada y mostrar un mensaje de validación: "La cédula debe contener solo números". |
| CP-005 | Contraseña con menos de 8 caracteres | 12345678 | short | La aplicación debe mostrar un mensaje de validación: "La contraseña debe tener al menos 8 caracteres". |
| CP-006 | Campo de cédula vacío | | contraseña_segura | La aplicación debe mostrar un mensaje de validación: "El campo Cédula no puede estar vacío". |
| CP-007 | Campo de contraseña vacío | 12345678 | | La aplicación debe mostrar un mensaje de validación: "El campo Contraseña no puede estar vacío". |
| CP-008 | Ambos campos vacíos | | | La aplicación debe mostrar mensajes de validación para ambos campos. |