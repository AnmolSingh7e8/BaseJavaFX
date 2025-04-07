package Services

import Database
import java.sql.Date

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
    }
}

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