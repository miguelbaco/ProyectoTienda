# Proyecto tienda
Proyecto a medias entre 
### Miguel Ángel Baco Morillo
### José Domingo Garrido Galve 
###### 1º DAW

*Para compilar y ejecutar el programa necesitas el jar de sqlite, para descargarlo pincha [Aquí](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.27.2).*
O bien lo puedes obtener mejor mediante las dependencias de maven (está en las dependencias necesarias)

Dependencias necesarias:
```bash
- $ mvn dependency:copy-dependencies package
```

Compilación:
```bash
- Ubuntu: $ mvn clean package
- Windows: $ mvn clean package
```

Ejecución:
```bash
- Ubuntu: $ java -cp target/proyecto3-0.1.0.BUILD-SNAPSHOT.jar:target/dependency/*:. Tienda
- Windows: $ java -cp target/proyecto3-0.1.0.BUILD-SNAPSHOT.jar;target/dependency/*;. Tienda
```

## Para la base de datos:
En el proyecto, existe un fichero inicial que se llama compra.db, que ya lleva introducida la base de datos y la tabla.

**¿Que hacer en caso de no tener dicho fichero?**  
Si no existe el fichero en el programa, este debe ser creado sobre el directorio java con ayuda del jar de sqlite y llamado compra.db.  
En Linux:
```bash
$ javac -cp .:sqlite-jdbc-3.27.2.jar:. JDBC.java  
$ java -cp .:sqlite-jdbc-3.27.2.jar:. JDBC
```
En Windows:
```bash
javac -cp .;sqlite-jdbc-3.27.2.jar;. JDBC.java  
java -cp .;sqlite-jdbc-3.27.2.jar;. JDBC
```
Una vez hecho esto se creará el fichero compra.db y listo para su uso.

## ¿Qué se ha usado?
Para este trabajo usamos JDBC (Java Data Base Connectivity) para almacenar e interactuar con una base de datos y con JavaFX como interfaz gráfica con la que interactua el usuario de una manera mas visual y clara.

## ¿Para qué sirve?
Este programa puede ser utilizado perfectamente por una tienda, para llevar la disponibilidad de todos sus productos, para añadir o quitar de stock y ademas para procesar las compras de los clientes guardando sus productos, precios y fecha de compra. Para añadir, puedes consultar la base de datos con los registros de todas las compras realizadas en la tienda.

El código logra adecuarse a las distintas funciones que dispone, llegando a cambiar entre funciones rápidamente con los cambios de productos guardados y datos introducidos a la base de datos al momento.

Al consultar una compra, o al mostrar la factura al cliente, saldrá reflejado en ella el nombre del cliente, los productos comprados con su peso (cantidad) y el precio de esa cantidad, junto con la fecha de compra y con el ID de compra.
