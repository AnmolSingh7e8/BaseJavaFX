package Services

import Database
import javafx.scene.control.Label
import java.util.Date
import java.sql.Date as SqlDate


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