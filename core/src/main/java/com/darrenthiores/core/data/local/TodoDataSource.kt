package com.darrenthiores.core.data.local

import kotlinx.coroutines.flow.Flow
import todoappcompose.tododb.TodoEntity

interface TodoDataSource {
    suspend fun insertTodo(todo: TodoEntity)

    suspend fun deleteTodo(id: Long)

    fun getTodos(): Flow<List<TodoEntity>>

    fun getCompletedTodos(): Flow<List<TodoEntity>>

    fun getUnCompletedTodos(): Flow<List<TodoEntity>>
}