## Monedero

### Contexto

Este repositorio contiene el código de un _monedero virtual_, al que podemos agregarle y quitarle dinero, a través
de los métodos `Monedero.sacar` y `Monedero.poner`, respectivamente.
Pero hay algunos problemas: por un lado el código no está muy bien testeado, y por el otro, hay numeros _code smells_.

### Solución

TypeTest: Creamos la interfaz Movimiento e implementamos Deposito y Extraccion para sacaer el 'if' que chequea e
boobleano.

LongParameterList: En lugar de crear otra instacia de Movimiento, le paso la misma por parámetro.

DuplicatedCode: Validación de monto positivo para sacar o extraer del monedero.

Se tienen dos instancias de movimeintos. Se usa la misma ahora.

Se iniciliza dos veces la cuenta; movimos todas las inicializaciones en el contrsuctor.

Sacamos el setMovimientos de cuenta, no está especficado y podría traer inconsitencias.

Se usa el método `movimiento.fueExtraido(fecha)` de la interfaz Movimiento para filtrar y sumar extracciones de una
fecha dada.

Se parametriza el `límite de extraccion diario`.
