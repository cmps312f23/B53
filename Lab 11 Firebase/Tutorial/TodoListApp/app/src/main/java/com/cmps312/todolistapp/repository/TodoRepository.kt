package com.cmps312.todolistapp.repository

import com.cmps312.todolistapp.model.Project
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TodoRepository {

    //    declare the database
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    //    Get a reference to the projects and todos collections to not write them again and again
    private val projectsRef = db.collection("projects")
    private val todosRef = db.collection("todos")


    fun addProject(project: Project) = projectsRef.add(project)
    fun updateProject(project: Project) = projectsRef
        .document(project.id)
        .set(project)

    fun deleteProject(project: Project) = projectsRef
        .document(project.id)
        .delete()

    fun observeProjects(): Flow<List<Project>> = callbackFlow {
        val snapshotListener = projectsRef.addSnapshotListener { values, err ->
            if (err != null)
                return@addSnapshotListener

            val projects = values!!.toObjects(Project::class.java)
            trySend(projects)
        }
        awaitClose { snapshotListener.remove() }
    }

//    fun observeTodos(pid: String): Flow<List<Todo>>
//    suspend fun getTodo(id: String):
//    suspend fun addTodo(todo: Todo)
//    fun updateTodo(todo: Todo)
//    fun deleteTodo(todo: Todo)
//    suspend fun getTodoListByProject(pid: String)
}