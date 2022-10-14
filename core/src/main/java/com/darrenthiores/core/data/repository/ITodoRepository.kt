package com.darrenthiores.core.data.repository

import com.darrenthiores.core.model.domain.TodoDomain
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    suspend fun insert(todo: TodoDomain)

    suspend fun checklistTodo(todo: TodoDomain, state: Boolean)

    suspend fun updateTodo(todo: TodoDomain, title: String, description: String)

    suspend fun deleteTodo(id: Long)

    fun getTodos(): Flow<List<TodoDomain>>

    fun getCompletedTodos(): Flow<List<TodoDomain>>

    fun getUncompletedTodos(): Flow<List<TodoDomain>>

}