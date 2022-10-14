package com.darrenthiores.todoappcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.core.domain.TodoUseCase
import com.darrenthiores.core.model.presenter.Todo
import com.darrenthiores.core.utils.DataMapper
import com.darrenthiores.todoappcompose.screen.FilterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val todoUseCase: TodoUseCase
): ViewModel() {

    private var _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>>
        get() = _todos

    private var _filterState = MutableStateFlow(FilterState.Default)
    val filterState: StateFlow<FilterState>
        get() = _filterState

    init {
        viewModelScope.launch {
            todoUseCase.getTodos().collect { todos ->
                _todos.value = if(todos.isNotEmpty()) {
                    DataMapper.mapDomainToPresenter(todos)
                } else {
                    emptyList()
                }
            }
        }
    }

    fun checklistTodo(todo: Todo, state: Boolean) {
        viewModelScope.launch {
            todoUseCase.checklistTodo(DataMapper.mapPresenterToDomain(todo), state)
        }
    }

    fun deleteTodo(id: Long) {
        viewModelScope.launch {
            todoUseCase.deleteTodo(id)
        }
    }

    fun filterTodo(filter: FilterState) {
        _filterState.value = filter
    }

}