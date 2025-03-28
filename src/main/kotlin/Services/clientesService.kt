package Services

import Database

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