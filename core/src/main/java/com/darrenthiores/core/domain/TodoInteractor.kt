package com.darrenthiores.core.domain

import com.darrenthiores.core.data.repository.ITodoRepository
import com.darrenthiores.core.model.domain.TodoDomain
import kotlinx.coroutines.flow.Flow

class TodoInteractor(
    private val todoRepository:  ITodoRepository
): TodoUseCase {
    override suspend fun insert(todo: TodoDomain) {
        todoRepository.insert(todo)
    }

    override suspend fun checklistTodo(todo: TodoDomain, state: Boolean) {
        todoRepository.checklistTodo(todo, state)
    }

    override suspend fun updateTodo(todo: TodoDomain, title: String, description: String) {
        todoRepository.updateTodo(todo, title, description)
    }

    override suspend fun deleteTodo(id: Long) {
        todoRepository.deleteTodo(id)
    }

    override fun getTodos(): Flow<List<TodoDomain>> =
        todoRepository.getTodos()

    override fun getCompletedTodos(): Flow<List<TodoDomain>> =
        todoRepository.getCompletedTodos()

    override fun getUncompletedTodos(): Flow<List<TodoDomain>> =
        todoRepository.getUncompletedTodos()
}