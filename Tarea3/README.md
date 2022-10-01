## Alumnos 

Los alumnos que desarrollaron los el proyecto:
   -Cesar Joaquin Delgado Diaz.
   -Carlos Mateo Ayala Rumich.

## Explicacion del programa 
Para poder ejecutar el programa solo se necesita tener un IDE con las librerias de JSON y las librerias de java basicas disponibles en minGW.
El programa "main" se encuentra en la rama llamada rama_joaquin. Las otras ramas son pruebas realizadas asi como tambien el master.
No utilizamos base de datos alguna. Almacenamos los datos en dos archivos, el primero llamado "log.txt" en donde guardamos la informacion de las operaciones realizadas en el servidor con sus datos correspondientes.El otro archivo llamado "consumos.txt"  guardamos los consumos que va teniendo cada cliente.
Optamos por realizar el TCP con hilos, al ejecutar nuestro servidor este esperara la conexion y realizara el trabajo creando y asignandole un hilo al cliente.
Cuando ejecutamos el cliente, pedimos a este que nos diga que operacion quiere realizar(la conexion es automatica).
Para almacenar un consumo el cliente ingresa dicho consumo y nuestro servidor guarda el consumo con su numero de NIS correspondiente en el archivo consumos.txt.
La opcion de realizar desconexion realiza un cambio en el estado de nuestro cliente y luego procede a realizar la desconexion. Esto para poder listar los clientes inactivos.
La opcion 3 y 4 del menu listan los clientes activos e inactivos respectivamente, dependiendo si tiene un valor 1(activo) o 0(inactivo).
Por ultimo tenemos la opcion de listar consumos, que simplemente muestra al cliente los consumos realizados por ese NIS hasta ese momento.
