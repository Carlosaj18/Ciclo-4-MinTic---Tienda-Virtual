package com.example.proyectacuenta.ui.activities

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.Product
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.databinding.ActivitySplashBinding
import com.example.proyectacuenta.ui.viewmodels.LoginTenderoViewModel
import com.example.proyectacuenta.ui.viewmodels.LoginViewModel
import com.example.proyectacuenta.ui.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

// Se debe modificar en el manifest que esta actividad sera el lucher
class SplashActivity : AppCompatActivity() {

    // Se crea el binding para poder conectar el layout con la actividad
    private lateinit var binding: ActivitySplashBinding

    // Se crea el viewModel
    private val splashViewModel: SplashViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private val loginTenderoViewModel: LoginTenderoViewModel by viewModel()

    // El metodo onCreate me dirve para inicializar la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Crea dos metodos para inicializarlos
        init()
        events()
    }

    // Se van a hacer unas inicializaciones
    private fun init(){
        // Le digo que inserte los datos que tiene el viewModel
        splashViewModel.insert(
            // Lista de stories
            listOf(
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
            ),
            listOf(
                // Devuelve una lista de comentarios
                Comment("1", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Carlos Jaramillo", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-26", "Buen servicio"),
                Comment("2", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Andres Portilla", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-27", "Buen servicio") ,
                Comment("3", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Humberto Garcia", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-28", "Buen servicio") ,
                Comment("4", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Estela Maria Sosa", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-29", "Buen servicio") ,
                Comment("5", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...", "Valentina Martinez", "https://w7.pngwing.com/pngs/524/696/png-transparent-computer-icons-user-symbol-symbol-miscellaneous-black-computer-icons.png", "2021-11-30", "Buen servicio"),
            ),
            listOf(
                // Devuelve una lista de productos
                Product("1", "Tomate", "El tomate es un alimento tradicional con un sabor único y que aporta importantes beneficios a la salud.", "data:image/jpeg;base64,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", "$2.500", "Verduras"),
                Product("2", "Cebolla", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://fuenteelpino.com/wp-content/uploads/2016/08/producto-grande-cebolladulce.png", "$3.000", "Verdura"),
                Product("3", "Pepino", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://selecto.com.pa/tienda/wp-content/uploads/2020/12/pepino.png", "$4.000", "Verduras"),
                Product("4", "Espinacas", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://piramideinformativa.com/wp-content/uploads/2014/03/espinacas.png", "$5.000", "Verduras"),
                Product("5", "Kale", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://i0.wp.com/trucosdemamas.com/wp-content/uploads/2018/07/Kale-PNG-HD.png?fit=1200%2C1026&ssl=1", "$6.000", "Verduras"),
                Product("6", "Brócoli", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://oleveg.com/wp-content/uploads/2018/09/brocoli_peq_2.png", "$7.000", "Verduras"),
                Product("7", "Guisantes", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://renovacion.prove.es/wp-content/uploads/2018/10/Guisante-Vaina-Prove.png", "$8.000", "Frutas"),
                Product("8", "Remolacha", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://pngimg.com/uploads/beet/beet_PNG28.png", "$9.000", "Verduras"),
                Product("9", "Zanahorias", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://okka.com.co/wp-content/uploads/2021/02/zanahoria.png", "$10.000", "Verduras"),
                Product("10", "Pimientos", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://www.sembrar100.com/wp-content/uploads/Pimiento-morron.png", "$11.000", "Verduras"),
                Product("11", "Manzana", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://www.frutality.es/wp-content/uploads/manzana-royal.png", "$1.000", "Frutas"),
                Product("12", "Pera", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://www.mercadoslpineda.co/2802-thickbox_default/pera-unidad.jpg", "$800", "Frutas"),
                Product("13", "Uvas", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c14a.png", "$3.500", "Frutas"),
                Product("14", "Fresas", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c1a1.png", "$2.500", "Frutas"),
                Product("15", "kiwi", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", " https://static.wixstatic.com/media/e1c504_13d6d269e02d4ccea3cc4b3be833226d~mv2.png/v1/fit/w_940%2Ch_788%2Cal_c/file.png", "$1.700", "Frutas"),
                Product("16", "Sandia", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://www.grupolacana.com/wp-content/uploads/2017/10/sandia_pr_sinfondo.png", "$3.300", "Frutas") ,
                Product("17", "Mango", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", "https://sicarfarms.com/wp-content/uploads/2021/01/Mango.png", "$1.100", "Frutas"),
                Product("18", "Arandanos", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", " https://static.wixstatic.com/media/e1c504_c159bfce859c466cabefdeea834678e1~mv2.png/v1/fit/w_940%2Ch_788%2Cal_c/file.png", "$4.800", "Frutas"),
                Product("19", "Mandarina", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", " https://estiloperuano.com/wp-content/uploads/2020/05/mandarina-600x600.png", "$800", "Frutas"),
                Product("20", "Coco", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.", " https://mixfruti.com.co/wp-content/uploads/2021/09/Coco-grande2.png", "$3.500", "Frutas"),
                )
        )
        loginViewModel.loggedIn()

        // Si tuviera imagenes se le indica las imagenes que usara el splash
        // binding.splashAnimation.imageAssetsFolder = "image"

        // Le indicamos que ejecute la animacion
        binding.splashAnimation.playAnimation()
    }

    // Escucha cuando la animacion ha terminado
    private fun events() {
        // Se crea un listener para esuchar el evento
        // Se crea un objeto AnimatorListener
        binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener{

            // Tipos de metodo del AnimatorListener
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p8: Animator?) {
                // Se observa si esta logeado
                // FALTA DETERMINAR COMO IDENTIFICO SI ES USER O TENDERO PARA DIRECCIONARLO A UNA VISTA PARTICULAR

                loginViewModel.user.observe( this@SplashActivity, Observer { user ->
                    if(user == null) {
                        // Se crea una intencion para dirigirnos de una actividad (splashActivity al MainActivity)
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        // Para iniciar una intencion explicita se la pasamos al metodo startActivity para indicarle que inicie el componente respectivo
                        startActivity(intent)
                        // Se le indica que se debe destruir la actividad para que no se vuelva a activar
                    } else {
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)
                        // Se le indica que se debe destruir la actividad para que no se vuelva a activar
                    }
                    finish()
                })
                loginTenderoViewModel.tendero.observe(this@SplashActivity, Observer { tendero ->
                    if(tendero == null){
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        val intent = Intent(applicationContext, HomeTenderoActivity::class.java)
                        startActivity(intent)
                    }
                })

            }

            override fun onAnimationCancel(p8: Animator?) {

            }

            override fun onAnimationRepeat(p8: Animator?) {

            }
        })
    }
}