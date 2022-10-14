package com.darrenthiores.core.data.repository

import com.darrenthiores.core.data.local.LocalDataSource
import com.darrenthiores.core.model.domain.TodoDomain
import com.darrenthiores.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepository(
    private val localDataSource: LocalDataSource
): ITodoRepository {
    override suspend fun insert(todo: TodoDomain) {
        localDataSource.insert(DataMapper.mapDomainToEntity(todo))
    }

    override suspend fun checklistTodo(todo: TodoDomain, state: Boolean) {
        localDataSource.checklistTodo(DataMapper.mapDomainToEntity(todo), state)
    }

    override suspend fun updateTodo(todo: TodoDomain, title: String, description: String) {
        localDataSource.updateTodo(DataMapper.mapDomainToEntity(todo), title, description)
    }

    override suspend fun deleteTodo(id: Long) {
        localDataSource.deleteTodo(id)
    }

    override fun getTodos(): Flow<List<TodoDomain>> =
        localDataSource.getTodos().map { DataMapper.mapEntitiesToDomain(it) }

    override fun getCompletedTodos(): Flow<List<TodoDomain>> =
        localDataSource.getCompletedTodos().map { DataMapper.mapEntitiesToDomain(it) }

    override fun getUncompletedTodos(): Flow<List<TodoDomain>> =
        localDataSource.getUncompletedTodos().map { DataMapper.mapEntitiesToDomain(it) }
}