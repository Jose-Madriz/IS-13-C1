## Login Test cases

| Caso de Prueba | Descripción | Cédula de Identidad | Contraseña | Resultado Esperado |
| ---------------|-------------|---------------------|------------|--------------------|
| CP-001 | Inicio de sesión exitoso | 12345678 | contraseña_valida | El usuario debe ser redirigido a la página principal. |
| CP-002 | Cédula incorrecta | 12345679 | contraseña_valida | Se debe mostrar un mensaje de error: "Cédula o contraseña incorrecta". |
| CP-003 | Contraseña incorrecta | 12345678 | contraseña_invalida | Se debe mostrar un mensaje de error: "Cédula o contraseña incorrecta". |
| CP-004 | Ambos campos incorrectos | 12345679 | contraseña_invalida | Se debe mostrar un mensaje de error: "Cédula o contraseña incorrecta". |
| CP-005 | Cédula con letras | 1234A567 | contraseña_valida | La aplicación debe rechazar la entrada y mostrar un mensaje de validación: "La cédula debe contener solo números". |
| CP-006 | Cédula con caracteres especiales | 12.345.678 | contraseña_valida | La aplicación debe rechazar la entrada y mostrar un mensaje de validación: "La cédula debe contener solo números". |
| CP-007 | Campo de cédula vacío | | contraseña_valida | La aplicación debe mostrar un mensaje de validación: "El campo Cédula no puede estar vacío". |
| CP-008 | Campo de contraseña vacío | 12345678 | | La aplicación debe mostrar un mensaje de validación: "El campo Contraseña no puede estar vacío". |
| CP-009 | Ambos campos vacíos | | | La aplicación debe mostrar mensajes de validación para ambos campos. |
| CP-010 | Contraseña con menos de 8 caracteres | 12345678 | short | La aplicación debe mostrar un mensaje de validación: "La contraseña debe tener al menos 8 caracteres". |
| CP-011 | Contraseña con exactamente 8 caracteres | 12345678 | 12345678 | Este caso debería comportarse como el CP-001, con inicio de sesión exitoso. |
