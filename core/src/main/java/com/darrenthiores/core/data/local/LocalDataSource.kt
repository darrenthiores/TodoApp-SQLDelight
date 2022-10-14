package com.darrenthiores.core.data.local

import kotlinx.coroutines.flow.Flow
import todoappcompose.tododb.TodoEntity

class LocalDataSource(
    private val todoDataSource: TodoDataSource
) {

    suspend fun insert(todo: TodoEntity) {
        todoDataSource.insertTodo(todo)
    }

    suspend fun checklistTodo(todo: TodoEntity, state: Boolean) {
        todoDataSource.insertTodo(todo.copy(done = state))
    }

    suspend fun updateTodo(todo: TodoEntity, title: String, description: String) {
        todoDataSource.insertTodo(todo.copy(title = title, description = description))
    }

    suspend fun deleteTodo(id: Long) {
        todoDataSource.deleteTodo(id)
    }

    fun getTodos(): Flow<List<TodoEntity>> =
        todoDataSource.getTodos()

    fun getCompletedTodos(): Flow<List<TodoEntity>> =
        todoDataSource.getCompletedTodos()

    fun getUncompletedTodos(): Flow<List<TodoEntity>> =
        todoDataSource.getUnCompletedTodos()

}