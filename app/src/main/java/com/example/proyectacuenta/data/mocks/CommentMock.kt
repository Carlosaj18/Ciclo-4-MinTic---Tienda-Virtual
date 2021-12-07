package com.example.proyectacuenta.data.mocks

import com.example.proyectacuenta.data.models.Comment

// Los mock es informacion quemada en memoria
class CommentMock {

    // Va a tener una funcion para traer los datos quemados
    // Va a tener una lista de comentarios
    // La logica de este CommentMock es devolver una lista de comentarios
    fun loadComments(): List<Comment> {
        return listOf(
            // Devuelve una lista de comentarios
            Comment("1", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Carlos Jaramillo", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-26", "Buen servicio"),
            Comment("2", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Andres Portilla", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-27", "Buen servicio") ,
            Comment("3", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Humberto Garcia", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-28", "Buen servicio") ,
            Comment("4", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Estela Maria Sosa", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-29", "Buen servicio") ,
            Comment("5", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Valentina Martinez", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-30", "Buen servicio")

        )
    }
}