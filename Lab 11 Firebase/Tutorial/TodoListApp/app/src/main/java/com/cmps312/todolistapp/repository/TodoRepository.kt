package com.cmps312.todolistapp.repository

import com.cmps312.todolistapp.model.Project
import com.cmps312.todolistapp.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

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

    suspend fun deleteProject(project: Project) {
        val todos = getTodoListByProject(project.id)
        todos.forEach { deleteTodo(it) }
        projectsRef
            .document(project.id)
            .delete()
    }

    fun observeProjects(): Flow<List<Project>> = callbackFlow {
        val snapshotListener = projectsRef.addSnapshotListener { values, err ->
            if (err != null)
                return@addSnapshotListener

            val projects = values!!.toObjects(Project::class.java)
            trySend(projects)
        }
        awaitClose { snapshotListener.remove() }
    }

    fun observeTodos(pid: String): Flow<List<Todo>> = callbackFlow {
        val snapshotListener = todosRef.whereEqualTo("pid", pid)
            .addSnapshotListener { values, err ->
                if (err != null)
                    return@addSnapshotListener
                val todos = values!!.toObjects(Todo::class.java)
                trySend(todos)
            }
        awaitClose { snapshotListener.remove() }
    }

    suspend fun getTodo(id: String) = todosRef
        .document(id)
        .get()
        .await()
        .toObject(Todo::class.java)

    suspend fun addTodo(todo: Todo) = todosRef.add(todo).await().id
    fun updateTodo(todo: Todo) = todosRef.document(todo.id).set(todo)
    fun deleteTodo(todo: Todo) = todosRef.document(todo.id).delete()
    suspend fun getTodoListByProject(pid: String) = todosRef
        .whereEqualTo("pid", pid)
        .get().await()
        .toObjects(Todo::class.java)
}