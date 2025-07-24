| ID de Prueba | Descripción | Datos de Entrada | Resultado Esperado |
|--------------|--------------|------------------|--------------------|
| PU-CCB-001 | Cálculo con valores positivos normales (Happy Path) | CF = 1000, CV = 500, NB = 100, %Merma = 0.10 | 16.5 |
| PU-CCB-002 | Cálculo con %Merma igual a cero (sin desperdicio) | CF = 1000, CV = 500, NB = 100, %Merma = 0 | 15.0 |
| PU-CCB-003 | Cálculo con NB (Número de Bandejas) igual a cero | CF = 1000, CV = 500, NB = 0, %Merma = 0.10| Error (división por cero). La función debe lanzar una excepción o devolver un valor de error. |
| PU-CCB-004 | Cálculo con costos (CF y CV) en cero | CF = 0, CV = 0, NB = 100, %Merma = 0.10 | 0 |
| PU-CCB-005 | Cálculo con valores negativos para los costos | CF = -100, CV = -50, NB = 100, %Merma = 0.10 | Error (valores negativos no son lógicos). La función debe manejar este caso como inválido. |
| PU-CCB-006 | Cálculo con %Merma negativa | CF = 1000, CV = 500, NB = 100, %Merma = -0.10 | Error (merma negativa no tiene sentido). La función debe manejar este caso como inválido. |
| PU-CCB-007 | Prueba de precisión con valores decimales | CF = 100.50, CV = 50.75, NB = 75, %Merma = 0.05 | 2.09666... (se debe verificar que el resultado se aproxime correctamente). |