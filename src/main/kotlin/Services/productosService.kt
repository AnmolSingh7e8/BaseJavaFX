package Services

import Database

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