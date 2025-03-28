package Services

import Database

fun insertOficina(oficina: Int, ciudad: String, region: String, dir: String, objetivo: Double, ventas: Double) {
    val sql = "INSERT INTO oficinas (oficina, ciudad, region, dir, objetivo, ventas) VALUES (?, ?, ?, ?, ?, ?)"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setInt(1, oficina)
            it.setString(2, ciudad)
            it.setString(3, region)
            it.setString(4, dir)
            it.setDouble(5, objetivo)
            it.setDouble(6, ventas)
            it.executeUpdate()
            println("Oficina registrada correctamente.")
        }
    }
}

fun listOficinas() {
    val sql = "SELECT oficina, ciudad, region, dir, objetivo, ventas FROM oficinas"
    Database.getConnection()?.use { conn ->
        conn.createStatement().use {
            val rs = it.executeQuery(sql)
            while (rs.next()) {
                println("Oficina #${rs.getInt("oficina")} - Ciudad: ${rs.getString("ciudad")} - Región: ${rs.getString("region")} - Dirección: ${rs.getString("dir")} - Objetivo: $${rs.getDouble("objetivo")} - Ventas: $${rs.getDouble("ventas")}")
            }
        }
    }
}

fun updateOficina(oficina: Int, ciudad: String, region: String, dir: String, objetivo: Double, ventas: Double) {
    val sql = "UPDATE oficinas SET ciudad = ?, region = ?, dir = ?, objetivo = ?, ventas = ? WHERE oficina = ?"
    Database.getConnection()?.use { conn ->
        conn.prepareStatement(sql).use {
            it.setString(1, ciudad)
            it.setString(2, region)
            it.setString(3, dir)
            it.setDouble(4, objetivo)
            it.setDouble(5, ventas)
            it.setInt(6, oficina)
            it.executeUpdate()
            println("Oficina actualizada correctamente.")
        }
    }
}

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