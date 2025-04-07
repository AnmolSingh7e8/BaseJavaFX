package Services

import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage
import java.text.SimpleDateFormat
import java.util.Date

class GUIApp2 : Application() {
    override fun start(stage: Stage) {
        val tableSelector = ComboBox<String>().apply {
            items.addAll("Clientes", "Oficinas", "Pedidos", "Productos", "Repventas")
            value = "Clientes"
        }
        val operationSelector = ComboBox<String>().apply {
            items.addAll("Insertar", "Actualizar", "Eliminar", "Listar")
            value = "Insertar"
        }

        val num_clie = TextField().apply { promptText = "ID Cliente" }
        val empresaField = TextField().apply { promptText = "Empresa" }
        val repClieField = TextField().apply { promptText = "Representante Cliente" }
        val limiteCreditoField = TextField().apply { promptText = "Límite de Crédito" }

        val oficina = TextField().apply { promptText = "ID Oficina" }
        val ciudadField = TextField().apply { promptText = "Ciudad" }
        val regionField = TextField().apply { promptText = "Región" }
        val dirField = TextField().apply { promptText = "Dirección" }
        val objetivoField = TextField().apply { promptText = "Objetivo" }
        val ventasField = TextField().apply { promptText = "Ventas" }

        val id_pedido = TextField().apply { promptText = "ID Pedido" }
        val fechaPedidoField = TextField().apply { promptText = "Fecha Pedido" }
        val clieField = TextField().apply { promptText = "Cliente" }
        val repField = TextField().apply { promptText = "Representante" }
        val fabField = TextField().apply { promptText = "Fabricante" }
        val productoField = TextField().apply { promptText = "Producto" }
        val cantField = TextField().apply { promptText = "Cantidad" }
        val importeField = TextField().apply { promptText = "Importe" }

        val id_fab = TextField().apply { promptText = "ID Fabricante" }
        val id_prod = TextField().apply { promptText = "ID Producto" }
        val descripcionField = TextField().apply { promptText = "Descripción" }
        val precioField = TextField().apply { promptText = "Precio" }
        val existenciasField = TextField().apply { promptText = "Existencias" }

        val num_empleado = TextField().apply { promptText = "ID Empleado" }
        val nameField = TextField().apply { promptText = "Nombre" }
        val edadField = TextField().apply { promptText = "Edad" }
        val oficinasRepField = TextField().apply { promptText = "Oficinas Representadas" }
        val tituloField = TextField().apply { promptText = "Título" }
        val contratoField = TextField().apply { promptText = "Contrato" }
        val directorField = TextField().apply { promptText = "Director" }
        val cuotaField = TextField().apply { promptText = "Cuota" }

        val output = Label()
        val executeButton = Button("Ejecutar operación")

        val container = VBox(10.0)
        container.padding = Insets(20.0)

        fun updateForm(selectedOperation: String, selectedTable: String) {
            container.children.setAll(tableSelector, operationSelector)
            when (selectedTable) {
                "Clientes" -> {
                    when (selectedOperation) {
                        "Insertar" -> container.children.addAll(
                            num_clie,
                            empresaField,
                            repClieField,
                            limiteCreditoField,
                            executeButton,
                            output
                        )

                        "Actualizar" -> container.children.addAll(
                            num_clie,
                            empresaField,
                            repClieField,
                            limiteCreditoField,
                            executeButton,
                            output
                        )

                        "Eliminar" -> container.children.addAll(num_clie, executeButton, output)
                        "Listar" -> container.children.addAll(executeButton, output)
                    }
                }

                "Oficinas" -> {
                    when (selectedOperation) {
                        "Insertar" -> container.children.addAll(
                            oficina,
                            ciudadField,
                            regionField,
                            dirField,
                            objetivoField,
                            ventasField,
                            executeButton,
                            output
                        )

                        "Actualizar" -> container.children.addAll(
                            oficina,
                            ciudadField,
                            regionField,
                            dirField,
                            objetivoField,
                            ventasField,
                            executeButton,
                            output
                        )

                        "Eliminar" -> container.children.addAll(oficina, executeButton, output)
                        "Listar" -> container.children.addAll(executeButton, output)
                    }
                }

                "Pedidos" -> {
                    when (selectedOperation) {
                        "Insertar" -> container.children.addAll(
                            id_pedido,
                            fechaPedidoField,
                            clieField,
                            repField,
                            fabField,
                            productoField,
                            cantField,
                            executeButton,
                            output
                        )

                        "Actualizar" -> container.children.addAll(
                            id_pedido,
                            fechaPedidoField,
                            clieField,
                            repField,
                            fabField,
                            productoField,
                            cantField,
                            executeButton,
                            output
                        )

                        "Eliminar" -> container.children.addAll(id_pedido, executeButton, output)
                        "Listar" -> container.children.addAll(executeButton, output)
                    }
                }

                "Productos" -> {
                    when (selectedOperation) {
                        "Insertar" -> container.children.addAll(
                            id_fab,
                            id_prod,
                            descripcionField,
                            precioField,
                            existenciasField,
                            executeButton,
                            output
                        )

                        "Actualizar" -> container.children.addAll(
                            id_fab,
                            id_prod,
                            descripcionField,
                            precioField,
                            existenciasField,
                            executeButton,
                            output
                        )

                        "Eliminar" -> container.children.addAll(id_prod, executeButton, output)
                        "Listar" -> container.children.addAll(executeButton, output)
                    }
                }

                "Repventas" -> {
                    when (selectedOperation) {
                        "Insertar" -> container.children.addAll(
                            num_empleado,
                            nameField,
                            edadField,
                            oficinasRepField,
                            tituloField,
                            contratoField,
                            directorField,
                            cuotaField,
                            ventasField,
                            executeButton,
                            output
                        )

                        "Actualizar" -> container.children.addAll(
                            num_empleado,
                            nameField,
                            edadField,
                            oficinasRepField,
                            tituloField,
                            contratoField,
                            directorField,
                            cuotaField,
                            ventasField,
                            executeButton,
                            output
                        )

                        "Eliminar" -> container.children.addAll(num_empleado, executeButton, output)
                        "Listar" -> container.children.addAll(executeButton, output)
                    }
                }
            }
        }

        executeButton.setOnAction {
            try {
                when (operationSelector.value) {
                    "Insertar" -> {
                        when (tableSelector.value) {
                            "Clientes" -> {
                                val numClie = num_clie.text.toInt()
                                val empresa = empresaField.text
                                val repClie = repClieField.text.toInt()
                                val limiteCredito = limiteCreditoField.text.toInt()
                                insertClient(numClie, empresa, repClie, limiteCredito)
                            }

                            "Oficinas" -> {
                                val oficinaId = oficina.text.toInt()
                                val ciudad = ciudadField.text
                                val region = regionField.text
                                val direccion = dirField.text.toInt()
                                val objetivo = objetivoField.text.toDouble()
                                val ventas = ventasField.text.toDouble()
                                insertOficina(oficinaId, ciudad, region, direccion, objetivo, ventas, output)
                            }

                            "Pedidos" -> {
                                val idPedido = id_pedido.text.toInt()
                                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                                val fechaPedido = dateFormat.parse(fechaPedidoField.text)
                                val clie = clieField.text.toInt()
                                val rep = repField.text.toInt()
                                val fab = fabField.text
                                val producto = productoField.text
                                val cant = cantField.text.toInt()
                                insertPedido(idPedido, fechaPedido, clie, rep, fab, producto, cant, output)
                            }

                            "Productos" -> {
                                val idFab = id_fab.text.toInt()
                                val idProd = id_prod.text.toInt()
                                val descripcion = descripcionField.text
                                val precio = precioField.text.toDouble()
                                val existencias = existenciasField.text.toInt()
                                insertProducto(idFab, idProd, descripcion, precio, existencias)
                            }

                            "Repventas" -> {
                                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                                val numEmpleado = num_empleado.text.toInt()
                                val nombre = nameField.text
                                val edad = edadField.text.toInt()
                                val oficinasRep = oficinasRepField.text.toInt()
                                val titulo = tituloField.text
                                val contrato = dateFormat.parse(contratoField.text)
                                val director = directorField.text
                                val cuota = cuotaField.text.toDouble()
                                val ventas = ventasField.text.toDouble()
                                insertRepventas(numEmpleado, nombre, edad, oficinasRep, titulo, contrato, director, cuota, ventas)
                            }
                        }
                        output.text = "✅ Insertado correctamente en ${tableSelector.value}."
                    }

                    "Actualizar" -> {
                        when (tableSelector.value) {
                            "Clientes" -> {
                                val numClie = num_clie.text.toInt()
                                val empresa = empresaField.text
                                val repClie = repClieField.text.toInt()
                                val limiteCredito = limiteCreditoField.text.toInt()
                                updateClient(numClie, empresa, repClie, limiteCredito)
                            }

                            "Oficinas" -> {
                                val oficinaId = oficina.text.toInt()
                                val ciudad = ciudadField.text
                                val region = regionField.text
                                val direccion = dirField.text.toInt()
                                val objetivo = objetivoField.text.toDouble()
                                val ventas = ventasField.text.toDouble()
                                updateOficina(oficinaId, ciudad, region, direccion, objetivo, ventas)
                            }

                            "Pedidos" -> {
                                val idPedido = id_pedido.text.toInt()
                                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                                val fechaPedido = dateFormat.parse(fechaPedidoField.text)
                                val clie = clieField.text.toInt()
                                val rep = repField.text.toInt()
                                val fab = fabField.text
                                val producto = productoField.text
                                val cant = cantField.text.toInt()
                                val importe = importeField.text.toDouble()
                                updatePedido(idPedido, fechaPedido, clie, rep, fab, producto, cant, importe)
                            }

                            "Productos" -> {
                                val idFab = id_fab.text.toInt()
                                val idProd = id_prod.text.toInt()
                                val descripcion = descripcionField.text
                                val precio = precioField.text.toDouble()
                                val existencias = existenciasField.text.toInt()
                                updateProducto(idFab, idProd, descripcion, precio, existencias)
                            }

                            "Repventas" -> {
                                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                                val numEmpleado = num_empleado.text.toInt()
                                val nombre = nameField.text
                                val edad = edadField.text.toInt()
                                val oficinasRep = oficinasRepField.text.toInt()
                                val titulo = tituloField.text
                                val contrato = dateFormat.parse(contratoField.text)
                                val director = directorField.text
                                val cuota = cuotaField.text.toDouble()
                                val ventas = ventasField.text.toDouble()
                                updateRepventas(numEmpleado, nombre, edad, oficinasRep, titulo, contrato, director, cuota, ventas)
                            }
                        }
                        output.text = "✅ Actualizado correctamente en ${tableSelector.value}."
                    }

                    "Eliminar" -> {
                        when (tableSelector.value) {
                            "Clientes" -> deleteClient(num_clie.text.toInt())
                            "Oficinas" -> deleteOficina(oficina.text.toInt())
                            "Pedidos" -> deletePedido(id_pedido.text.toInt())
                            "Productos" -> deleteProducto(id_fab.text.toInt(), id_prod.text.toInt())
                            "Repventas" -> deleteRepventas(num_empleado.text.toInt())
                        }
                        output.text = "✅ Eliminado correctamente en ${tableSelector.value}."
                    }

                    "Listar" -> {
                        when (tableSelector.value) {
                            "Clientes" -> listClients()
                            "Oficinas" -> listOficinas()
                            "Pedidos" -> listPedidos()
                            "Productos" -> listProductos()
                            "Repventas" -> listRepventas()
                        }
                        output.text = "📋 Listado de ${tableSelector.value}."
                    }
                }
            } catch (e: NumberFormatException) {
                output.style = "-fx-text-fill: red;"
                output.text = "❌ Error: Entrada no válida. Por favor, ingrese valores numéricos donde corresponda."
            }
        }

        operationSelector.setOnAction {
            updateForm(operationSelector.value, tableSelector.value)
        }
        tableSelector.setOnAction {
            updateForm(operationSelector.value, tableSelector.value)
        }

        updateForm(operationSelector.value, tableSelector.value)

        stage.scene = Scene(container, 420.0, 400.0)
        stage.title = "Gestión de Tablas (CRUD)"
        stage.show()
    }
}