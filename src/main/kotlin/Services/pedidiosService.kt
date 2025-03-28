package Services

import Database

fun insertPedido(numPedido: Int, fechaPedido: String, clie: Int, rep: Int, fab: String, producto: String, cant: Int, importe: Double) {
    val sql = "INSERT INTO pedidos (num_pedido, fecha_pedido, clie, rep, fab, producto, cant, importe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setInt(1, numPedido)
            it.setString(2, fechaPedido)
            it.setInt(3, clie)
            it.setInt(4, rep)
            it.setString(5, fab)
            it.setString(6, producto)
            it.setInt(7, cant)
            it.setDouble(8, importe)
            it.executeUpdate()
            println("Pedido registrado correctamente.")
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

fun updatePedido(numPedido: Int, fechaPedido: String, clie: Int, rep: Int, fab: String, producto: String, cant: Int, importe: Double) {
    val sql = "UPDATE pedidos SET fecha_pedido = ?, clie = ?, rep = ?, fab = ?, producto = ?, cant = ?, importe = ? WHERE num_pedido = ?"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setString(1, fechaPedido)
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