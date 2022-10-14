package com.darrenthiores.todoappcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.core.domain.TodoUseCase
import com.darrenthiores.core.model.presenter.Todo
import com.darrenthiores.core.utils.DataMapper
import kotlinx.coroutines.launch

class DetailViewModel(
    private val todoUseCase: TodoUseCase
): ViewModel() {

    fun insert(todo:Todo) {
        viewModelScope.launch {
            todoUseCase.insert(DataMapper.mapPresenterToDomain(todo))
        }
    }

    fun updateTodo(todo: Todo, title: String, description: String) {
        viewModelScope.launch {
            todoUseCase.updateTodo(DataMapper.mapPresenterToDomain(todo), title, description)
        }
    }

    fun deleteTodo(id: Long) {
        viewModelScope.launch {
            todoUseCase.deleteTodo(id)
        }
    }

}