# Proyecto CRUD en Kotlin con JavaFX

Este proyecto es una aplicación de escritorio en Kotlin que permite gestionar clientes a través de operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una base de datos PostgreSQL.

## Requisitos

- IntelliJ IDEA
- JDK 17+
- PostgreSQL ejecutándose en localhost:
  - Debes especificar los detalles de conexión en `Database.kt`
    - Base de datos: ds2
    - Usuario: ds2
    - Contraseña: ds2

## Cómo Ejecutar

1. Abre el proyecto en IntelliJ IDEA (`Archivo > Abrir...`).
2. Espera a que Gradle se sincronice.
3. Abre `Main.kt` y haz clic en ▶️ para ejecutar la aplicación.

## Funcionalidad

### Servicios de Clientes

- **Insertar Cliente**
  - Inserta un nuevo cliente en la base de datos.
```kotlin
fun insertClient(numClie: Int, empresa: String, repClie: Int, limiteCredito: Int) {
    val sql = "INSERT INTO clientes (num_clie, empresa, rep_clie, limite_credito) VALUES (?, ?, ?, ?)"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setInt(1, numClie)
            it.setString(2, empresa)
            it.setInt(3, repClie)
            it.setInt(4, limiteCredito)
            it.executeUpdate()
            println("Cliente registrado correctamente.")
        }
    }
}
```
- **Listar Clientes**
  - Lista clientes en bloques de 10.
```kotlin
fun listClients() {
    val sql = "SELECT num_clie, empresa, rep_clie, limite_credito FROM clientes"
    Database.getConnection()?.use { conn ->
        conn.createStatement().use {
            val rs = it.executeQuery(sql)
            while (rs.next()) {
                println("Cliente #${rs.getInt("num_clie")} - Empresa: ${rs.getString("empresa")} - Representante: ${rs.getInt("rep_clie")} - Límite de Crédito: $${rs.getDouble("limite_credito")}")
            }
        }
    }
}
```
- **Actualizar Email del Cliente**
  - Actualiza el email de un cliente existente.
```kotlin
fun updateClient(numClie: Int, empresa: String, repClie: Int, limiteCredito: Int) {
    val sql = "UPDATE clientes SET empresa = ?, rep_clie = ?, limite_credito = ? WHERE num_clie = ?"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setString(1, empresa)
            it.setInt(2, repClie)
            it.setInt(3, limiteCredito)
            it.setInt(4, numClie)
            it.executeUpdate()
            println("Cliente actualizado correctamente.")
        }
    }
}
```
- **Eliminar Cliente**
  - Elimina un cliente de la base de datos.
```kotlin
fun deleteClient(numClie: Int) {
    val sql = "DELETE FROM clientes WHERE num_clie = ?"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setInt(1, numClie)
            it.executeUpdate()
            println("Cliente eliminado.")
        }
    }
}
```

### Servicios de Ventas de Representantes

- **Insertar Ventas de Representante**
  - Inserta un nuevo registro de ventas de representante en la base de datos.
```kotlin
fun insertRepventas(numEmpl: Int, nombre: String, edad: Int, oficinaRep: Int, titulo: String, contrato: java.util.Date, director: String, cuota: Double, ventas: Double) {
    val sql =
        "INSERT INTO repventas (num_empl, nombre, edad, oficina_rep, titulo, contrato, director, cuota, ventas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setInt(1, numEmpl)
            it.setString(2, nombre)
            it.setInt(3, edad)
            it.setInt(4, oficinaRep)
            it.setString(5, titulo)
            it.setDate(6, Date(contrato.time))
            it.setString(7, director)
            it.setDouble(8, cuota)
            it.setDouble(9, ventas)
            it.executeUpdate()
            println("Representante registrado correctamente.")
        }
    }
}
```
- **Listar Ventas de Representante**
  - Lista todos los registros de ventas de representantes.
```kotlin
fun listRepventas() {
  val sql = "SELECT num_empl, nombre, edad, oficina_rep, titulo, contrato, director, cuota, ventas FROM repventas"
  Database.getConnection()?.use { conn ->
    conn.createStatement().use {
      val rs = it.executeQuery(sql)
      while (rs.next()) {
        println(
          "Representante #${rs.getInt("num_empl")} - Nombre: ${rs.getString("nombre")} - Edad: ${
            rs.getInt(
              "edad"
            )
          } - Oficinas: ${rs.getInt("oficina_rep")} - Título: ${rs.getString("titulo")} - Contrato: ${
            rs.getDate(
              "contrato"
            )
          } - Director: ${rs.getString("director")} - Cuota: $${rs.getDouble("cuota")} - Ventas: $${
            rs.getDouble(
              "ventas"
            )
          }"
        )
      }
    }
  }
}
```
- **Actualizar Ventas de Representante**
  - Actualiza un registro de ventas de representante existente.
```kotlin
fun updateRepventas(
  numEmpl: Int,
  nombre: String,
  edad: Int,
  oficinaRep: Int,
  titulo: String,
  contrato: java.util.Date,
  director: String,
  cuota: Double,
  ventas: Double
) {
  val sql =
    "UPDATE repventas SET nombre = ?, edad = ?, oficina_rep = ?, titulo = ?, contrato = ?, director = ?, cuota = ?, ventas = ? WHERE num_empl = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setString(1, nombre)
      it.setInt(2, edad)
      it.setInt(3, oficinaRep)
      it.setString(4, titulo)
      it.setDate(5, Date(contrato.time))
      it.setString(6, director)
      it.setDouble(7, cuota)
      it.setDouble(8, ventas)
      it.setInt(9, numEmpl)
      it.executeUpdate()
      println("Representante actualizado correctamente.")
    }
  }fun listClients() {
  val sql = "SELECT num_clie, empresa, rep_clie, limite_credito FROM clientes"
  Database.getConnection()?.use { conn ->
    conn.createStatement().use {
      val rs = it.executeQuery(sql)
      while (rs.next()) {
        println("Cliente #${rs.getInt("num_clie")} - Empresa: ${rs.getString("empresa")} - Representante: ${rs.getInt("rep_clie")} - Límite de Crédito: $${rs.getDouble("limite_credito")}")
      }
    }
  }
}
}
```
- **Eliminar Ventas de Representante**
  - Elimina un registro de ventas de representante de la base de datos.
```kotlin
fun deleteRepventas(numEmpl: Int) {
  val sql = "DELETE FROM repventas WHERE num_empl = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setInt(1, numEmpl)
      it.executeUpdate()
      println("Representante eliminado.")
    }
  }
}
```

### Servicios de Pedidos

- **Insertar Pedido**
  - Inserta un nuevo pedido en la base de datos.
```kotlin
fun insertPedido(numPedido: Int, fechaPedido: Date, clie: Int, rep: Int, fab: String, producto: String, cant: Int, output: Label) {
  val checkClientSql = "SELECT COUNT(*) FROM clientes WHERE num_clie = ?"
  val checkRepSql = "SELECT COUNT(*) FROM repventas WHERE num_empl = ?"
  val checkProductSql = "SELECT precio FROM productos WHERE id_producto = ?" // Ensure the column name matches your table
  val insertSql = "INSERT INTO pedidos (num_pedido, fecha_pedido, clie, rep, fab, producto, cant, importe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

  Database.getConnection()?.use { conn ->
    // Check if client exists
    conn.prepareStatement(checkClientSql).use { checkStmt ->
      checkStmt.setInt(1, clie)
      val rs = checkStmt.executeQuery()
      if (rs.next() && rs.getInt(1) == 0) {
        output.style = "-fx-text-fill: red;"
        output.text = "❌ Error: El cliente no existe."
        return@use
      }
    }

    // Check if representative exists
    conn.prepareStatement(checkRepSql).use { checkStmt ->
      checkStmt.setInt(1, rep)
      val rs = checkStmt.executeQuery()
      if (rs.next() && rs.getInt(1) == 0) {
        output.style = "-fx-text-fill: red;"
        output.text = "❌ Error: El representante no existe."
        return@use
      }
    }

    // Check if product exists and get its price
    var productPrice: Double? = null
    conn.prepareStatement(checkProductSql).use { checkStmt ->
      checkStmt.setString(1, producto)
      val rs = checkStmt.executeQuery()
      if (rs.next()) {
        productPrice = rs.getDouble("precio")
      } else {
        output.style = "-fx-text-fill: red;"
        output.text = "❌ Error: El producto no existe."
        return@use
      }
    }

    // Calculate the total amount
    val importe = productPrice!! * cant

    // Insert the new order
    conn.prepareStatement(insertSql).use { insertStmt ->
      insertStmt.setInt(1, numPedido)
      insertStmt.setDate(2, SqlDate(fechaPedido.time))
      insertStmt.setInt(3, clie)
      insertStmt.setInt(4, rep)
      insertStmt.setString(5, fab)
      insertStmt.setString(6, producto)
      insertStmt.setInt(7, cant)
      insertStmt.setDouble(8, importe)
      insertStmt.executeUpdate()
      output.style = "-fx-text-fill: green;"
      output.text = "✅ Pedido registrado correctamente."
    }
  }
}
```
- **Listar Pedidos**
  - Lista todos los pedidos.
```kotlin
fun listPedidos() {
  val sql = "SELECT num_pedido, fecha_pedido, clie, rep, fab, producto, cant, importe FROM pedidos"
  Database.getConnection()?.use { conn ->
    conn.createStatement().use {
      val rs = it.executeQuery(sql)
      while (rs.next()) {
        println("Pedido #${rs.getInt("num_pedido")} - Fecha: ${rs.getString("fecha_pedido")} - Cliente: ${rs.getInt("clie")} - Representante: ${rs.getInt("rep")} - Fabricante: ${rs.getString("fab")} - Producto: ${rs.getString("producto")} - Cantidad: ${rs.getInt("cant")} - Importe: $${rs.getDouble("importe")}")
      }
    }
  }
}
```
- **Actualizar Pedido**
  - Actualiza un pedido existente.
```kotlin
fun updatePedido(numPedido: Int, fechaPedido: Date, clie: Int, rep: Int, fab: String, producto: String, cant: Int, importe: Double) {
  val sql = "UPDATE pedidos SET fecha_pedido = ?, clie = ?, rep = ?, fab = ?, producto = ?, cant = ?, importe = ? WHERE num_pedido = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setDate(1, SqlDate(fechaPedido.time))
      it.setInt(2, clie)
      it.setInt(3, rep)
      it.setString(4, fab)
      it.setString(5, producto)
      it.setInt(6, cant)
      it.setDouble(7, importe)
      it.setInt(8, numPedido)
      it.executeUpdate()
      println("Pedido actualizado correctamente.")
    }
  }
}
```
- **Eliminar Pedido**
  - Elimina un pedido de la base de datos.
```kotlin
fun deletePedido(numPedido: Int) {
  val sql = "DELETE FROM pedidos WHERE num_pedido = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setInt(1, numPedido)
      it.executeUpdate()
      println("Pedido eliminado.")
    }
  }
}
```

### Servicios de Oficinas

- **Insertar Oficina**
  - Inserta una nueva oficina en la base de datos.
```kotlin
fun insertOficina(oficina: Int, ciudad: String, region: String, dir: Int, objetivo: Double, ventas: Double, output: Label) {
  val checkSql = "SELECT COUNT(*) FROM repventas WHERE num_empl = ?"
  val insertSql = "INSERT INTO oficinas (oficina, ciudad, region, dir, objetivo, ventas) VALUES (?, ?, ?, ?, ?, ?)"

  Database.getConnection()?.use { conn ->
    conn.prepareStatement(checkSql).use { checkStmt ->
      checkStmt.setInt(1, dir)
      val rs = checkStmt.executeQuery()
      if (rs.next() && rs.getInt(1) == 0) {
        output.style = "-fx-text-fill: red;"
        output.text = "❌ Error: El dir no existe en la tabla de repventas."
        return@use
      }
    }

    conn.prepareStatement(insertSql).use { insertStmt ->
      insertStmt.setInt(1, oficina)
      insertStmt.setString(2, ciudad)
      insertStmt.setString(3, region)
      insertStmt.setInt(4, dir)
      insertStmt.setDouble(5, objetivo)
      insertStmt.setDouble(6, ventas)
      insertStmt.executeUpdate()
      output.style = "-fx-text-fill: green;"
      output.text = "✅ Oficina registrada correctamente."
    }
  }
}
```
- **Listar Oficinas**
  - Lista todas las oficinas.
```kotlin
 fun listOficinas() {
  val sql = "SELECT oficina, ciudad, region, dir, objetivo, ventas FROM oficinas"
  Database.getConnection()?.use { conn ->
    conn.createStatement().use {
      val rs = it.executeQuery(sql)
      while (rs.next()) {
        println("Oficina #${rs.getInt("oficina")} - Ciudad: ${rs.getString("ciudad")} - Región: ${rs.getString("region")} - Dirección: ${rs.getInt("dir")} - Objetivo: $${rs.getDouble("objetivo")} - Ventas: $${rs.getDouble("ventas")}")
      }
    }
  }
}
```
- **Actualizar Oficina**
  - Actualiza una oficina existente.
```kotlin
fun updateOficina(oficina: Int, ciudad: String, region: String, dir: Int, objetivo: Double, ventas: Double) {
  val sql = "UPDATE oficinas SET ciudad = ?, region = ?, dir = ?, objetivo = ?, ventas = ? WHERE oficina = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setString(1, ciudad)
      it.setString(2, region)
      it.setInt(3, dir)
      it.setDouble(4, objetivo)
      it.setDouble(5, ventas)
      it.setInt(6, oficina)
      it.executeUpdate()
      println("Oficina actualizada correctamente.")
    }
  }
}
```
- **Eliminar Oficina**
  - Elimina una oficina de la base de datos.
```kotlin
fun deleteOficina(oficina: Int) {
  val sql = "DELETE FROM oficinas WHERE oficina = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setInt(1, oficina)
      it.executeUpdate()
      println("Oficina eliminada.")
    }
  }
}
```

### Servicios de Productos

- **Insertar Producto**
  - Inserta un nuevo producto en la base de datos.
```kotlin
fun insertProducto(idFab: Int, idProducto: Int, descripcion: String, precio: Double, existencias: Int) {
  val sql = "INSERT INTO productos (id_fab, id_producto, descripcion, precio, existencias) VALUES (?, ?, ?, ?, ?)"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setInt(1, idFab)
      it.setInt(2, idProducto)
      it.setString(3, descripcion)
      it.setDouble(4, precio)
      it.setInt(5, existencias)
      it.executeUpdate()
      println("Producto registrado correctamente.")
    }
  }
}
```
- **Listar Productos**
  - Lista todos los productos.
```kotlin
fun listProductos() {
  val sql = "SELECT id_fab, id_producto, descripcion, precio, existencias FROM productos"
  Database.getConnection()?.use { conn ->
    conn.createStatement().use {
      val rs = it.executeQuery(sql)
      while (rs.next()) {
        try {
          val idFab = rs.getInt("id_fab")
          val idProducto = rs.getInt("id_producto")
          val descripcion = rs.getString("descripcion")
          val precio = rs.getDouble("precio")
          val existencias = rs.getInt("existencias")
          println("Producto #$idProducto - Fabricante: $idFab - Descripción: $descripcion - Precio: $$precio - Existencias: $existencias")
        } catch (e: NumberFormatException) {
          println("Error: Invalid integer value in the database.")
        }
      }
    }
  }
}
```
- **Actualizar Producto**
  - Actualiza un producto existente.
```kotlin
fun updateProducto(idFab: Int, idProducto: Int, descripcion: String, precio: Double, existencias: Int) {
  val sql = "UPDATE productos SET descripcion = ?, precio = ?, existencias = ? WHERE id_fab = ? AND id_producto = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setString(1, descripcion)
      it.setDouble(2, precio)
      it.setInt(3, existencias)
      it.setInt(4, idFab)
      it.setInt(5, idProducto)
      it.executeUpdate()
      println("Producto actualizado correctamente.")
    }
  }
}
```
- **Eliminar Producto**
  - Elimina un producto de la base de datos.
```kotlin
fun deleteProducto(idFab: Int, idProducto: Int) {
  val sql = "DELETE FROM productos WHERE id_fab = ? AND id_producto = ?"
  Database.getConnection()?.use { conn ->
    conn.prepareStatement(sql).use {
      it.setInt(1, idFab)
      it.setInt(2, idProducto)
      it.executeUpdate()
      println("Producto eliminado.")
    }
  }
}
```

## Configuración de la Base de Datos

Asegúrate de configurar la conexión a tu base de datos PostgreSQL en `Database.kt` con los siguientes detalles:

```kotlin
object Database {
    private const val url = "jdbc:postgresql://localhost:5432/ds2"
    private const val user = "ds2"
    private const val password = "ds2"

    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(url, user, password)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }
}