package com.example.proyectacuenta.data.mocks

import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.StoreInfo

class StoreInfoMock {

    /*  Si es solo una tienda
   fun loadInfo(): StoreInfo {
       return StoreInfo(
           "1",
           "Mi tienda Agil",
           "Carlos Alberto Jaramillo",
           "3187936659",
           "cjaramilloportilla@gmail.com",
           "https://web-gdl.com/wp-content/uploads/store01-800x675.png",
           "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
           "Valle del Cauca",
           "Cali",
           "Diagonal No. 91 4-20",
       )
   }*/

    // Va a tener una funcion para traer los datos quemados
    // Va a tener una lista de comentarios
    // La logica de este CommentMock es devolver una lista de comentarios
    fun loadStore(): List<StoreInfo> {
        return listOf(
            // Devuelve una lista de tiendas
            StoreInfo(
                "1",
                "Mi tienda Agil",
                "Carlos Alberto Jaramillo",
                "3187936659",
                "cjaramilloportilla@gmail.com",
                "https://web-gdl.com/wp-content/uploads/store01-800x675.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Valle del Cauca",
                "Cali",
                "Diagonal 91 No. 4-20"
            ),
            StoreInfo(
                "2",
                "Justo y Bueno",
                "Sara Valentina Jiménez Castro",
                "3156940356",
                "SaraJimenez@gmail.com",
                "https://bulevar.com.co/wp-content/uploads/2020/12/LOGO-JUSTO-Y-BUENO.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Bogota D.C",
                "Bogota",
                "Carrera 4B 90-02"
            ),
            StoreInfo(
                "3",
                "D1",
                "Cristian Felipe Ovalles",
                "3152860496",
                "crisovalles@gmail.com",
                "https://upload.wikimedia.org/wikipedia/commons/a/a8/Tiendas_D1_logo.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Tolima",
                "Ibague",
                "Calle 15 # 40 - 75"
            ),
            StoreInfo(
                "4",
                "Olimpica",
                "Isabel Cristina Barragán Lopez",
                "3174592651",
                "isabelbarragan@gmail.com",
                "https://scotiabankfiles.azureedge.net/scotiabank-colombia/CodensaAssets/catalogue/images/promociones/logos-aliados/LOGO.NEW-02-olimpica.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Antioquia",
                "Medellín",
                "Carrera 45 # 73 - 34"
            ),
            StoreInfo(
                "5",
                "Éxito",
                "Michael Leonardo Suarez Jacome",
                "3046024658",
                "michaelsuarez2@gmail.com",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Almacenes_exito_logo.svg/2055px-Almacenes_exito_logo.svg.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Quindío",
                "Armenia",
                "Calle 14 # 10 -21"
            ),
            StoreInfo(
                "6",
                "Jumbo",
                "Valeria Estupiñán Castro",
                "3214968156",
                "valeriacastro2695@outlook.com",
                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Logo_Jumbo_Cencosud.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Risaralda",
                "Pereira",
                "Diagonal 7 b #48 b sur"
            ),
            StoreInfo(
                "7",
                "Carulla",
                "Jean Paul Martínez Ruiz",
                "3204652987",
                "Jeanmartinez65@hotmail.com",
                "https://upload.wikimedia.org/wikipedia/commons/9/9c/Carulla_logo.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Bolívar",
                "Cartagena",
                "Calle 17 # 95-76 SUR"
            ),
            StoreInfo(
                "8",
                "Colsubsidio",
                "Martha Patricia Lizarazo Gomez",
                "3142569856",
                "marthalizarazo@gmail.com",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Colsubsidio_logo.svg/2560px-Colsubsidio_logo.svg.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Huila",
                "Neiva",
                "Carrera 6 # 15-09"
            ),
            StoreInfo(
                "9",
                "Cafam",
                "Marisol Arias Pineda",
                "3104659286",
                "marisolpineda1996@gmail.com",
                "https://www.cafam.com.co/images/logo-personas.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Magdalena",
                "Santa Marta",
                "Calle 21 b No. 4-20"
            ),
            StoreInfo(
                "10",
                "Mi tienda Agil",
                "Laura Tatiana García Perez",
                "3187936659",
                "garciaperez65@yahoo.es",
                "https://scotiabankfiles.azureedge.net/scotiabank-colombia/CodensaAssets/catalogue/images/promociones/logos-aliados/ALKOSTO_1.png",
                "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage.",
                "Valle del Cauca",
                "Cali",
                "Diagonal 91 No. 4-20"
            )
        )
    }
}