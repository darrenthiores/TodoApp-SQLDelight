package com.darrenthiores.core.data.local

import com.darrenthiores.core.TodoDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import todoappcompose.tododb.TodoEntity

class TodoDataSourceImpl(
    db: TodoDatabase
): TodoDataSource {
    private val queries = db.todoEntityQueries

    override suspend fun insertTodo(todo: TodoEntity) {
        withContext(Dispatchers.Default) {
            queries.insert(
                todo.id,
                todo.title,
                todo.description,
                todo.done
            )
        }
    }

    override suspend fun deleteTodo(id: Long) {
        withContext(Dispatchers.Default) {
            queries.deleteTodoById(id)
        }
    }

    override fun getTodos(): Flow<List<TodoEntity>> =
        queries.getTodos()
            .asFlow()
            .mapToList()

    override fun getCompletedTodos(): Flow<List<TodoEntity>> =
        queries.getCompletedTodos()
            .asFlow()
            .mapToList()

    override fun getUnCompletedTodos(): Flow<List<TodoEntity>> =
        queries.getUncompletedTodos()
            .asFlow()
            .mapToList()
}