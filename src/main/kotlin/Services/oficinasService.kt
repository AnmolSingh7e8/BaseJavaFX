package Services

    import Database
    import javafx.scene.control.Label

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