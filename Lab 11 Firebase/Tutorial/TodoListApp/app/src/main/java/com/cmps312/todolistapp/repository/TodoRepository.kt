package com.cmps312.todolistapp.repository

import com.cmps312.todolistapp.model.Project
import com.cmps312.todolistapp.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TodoRepository {
    //    create the firebase database
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
//     a reference to the collections

    private val projectsCollectionRef by lazy { db.collection("projects") }
    private val todosCollectionRef by lazy { db.collection("todos") }

    fun observeProjects(): Flow<List<Project>> = callbackFlow {
        val snapshot = projectsCollectionRef.addSnapshotListener { value, err ->
            if (err != null)
                return@addSnapshotListener
            if (value != null) {
                val projects = value.toObjects(Project::class.java)
                this.trySend(projects)
            }
        }
        awaitClose { snapshot.remove() }
    }

    fun addProject(project: Project) = projectsCollectionRef.add(project)
    fun updateProject(project: Project) = projectsCollectionRef.document(project.id).set(project)
    suspend fun deleteProject(project: Project) {
        val todos = getTodoListByProject(project.id)
        todos.forEach { deleteTodo(it) }
        projectsCollectionRef.document(project.id).delete()
    }

    fun observeTodos(pid: String): Flow<List<Todo>> = callbackFlow {
        val snapshot = todosCollectionRef
            .whereEqualTo("pid", pid)
            .addSnapshotListener { value, err ->
                if (err != null) return@addSnapshotListener
                if (value != null) {
                    val todos = value.toObjects(Todo::class.java)
                    this.trySend(todos)
                }

            }
        awaitClose {
            snapshot.remove()
        }
    }

    suspend fun getTodo(id: String) =
        todosCollectionRef
            .document(id)
            .get().await().toObject(Todo::class.java)

    fun addTodo(todo: Todo) = todosCollectionRef.add(todo)

    fun updateTodo(todo: Todo) = todosCollectionRef.document(todo.id).set(todo)
    fun deleteTodo(todo: Todo) = todosCollectionRef.document(todo.id).delete()
    private suspend fun getTodoListByProject(pid: String) = todosCollectionRef
        .whereEqualTo("pid", pid)
        .get()
        .await()
        .toObjects(Todo::class.java)
}
