package com.example.proyectacuenta.data.repositories

import com.example.proyectacuenta.data.databases.dao.CommentDao
import com.example.proyectacuenta.data.mocks.CommentMock
import com.example.proyectacuenta.data.models.Comment
import com.example.proyectacuenta.data.models.StoreInfo
import com.example.proyectacuenta.utils.Constans
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// Se encarga de coordinar a los diferentes dataSource para obtener la informacion que se requiera
// Se encarga de darle al viewModel lo que necesita
// Se puede usar dos data source un local y otro online y operarlos conforme unas reglas de conexion
// Si no se tuviera la data quemada se pasa el de firebase, el DAO, entre otras.
// Lo unico que se va a modificar son los datasource, ya que al viewModel no le interesa de donde viene los datos sino que lleguen por medio del repositorio
class CommentRepository(private val dataSource: CommentMock, private val dataSourceDb: CommentDao,
                        private val dataSourceFirebase: FirebaseFirestore) {

    // Se crea la variable para la base dedatos
    private val db: CollectionReference = dataSourceFirebase.collection(Constans.COMMENT_COLLECTION)

    suspend fun loadComments(): List<Comment> {
        /* Para traer los datos quemados */
        // return dataSource.loadComments()

        /* Para traer los datos de una base de datos*/
        //  return dataSourceDb.getAllComments()

        /* Para traer los datos desde firebase*/
        val snapshot = db.get().await()
        return snapshot.toObjects(Comment::class.java)
    }


    suspend fun insertComments(comments: List<Comment>) {
        val temp = dataSourceDb.getAllComments()
        if(temp.isEmpty()){
            dataSourceDb.insertComments(comments)
        }
    }
}