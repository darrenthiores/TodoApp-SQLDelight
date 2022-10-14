package com.darrenthiores.core.utils

import com.darrenthiores.core.model.domain.TodoDomain
import com.darrenthiores.core.model.presenter.Todo
import todoappcompose.tododb.TodoEntity

object DataMapper {

    fun mapEntitiesToDomain(input: List<TodoEntity>): List<TodoDomain> =
        input.map {
            TodoDomain(
                it.id,
                it.title,
                it.description,
                it.done ?: false
            )
        }

    fun mapDomainToEntity(input: TodoDomain): TodoEntity =
        TodoEntity(
            input.id,
            input.title,
            input.description,
            input.done
        )

    fun mapDomainToPresenter(input: List<TodoDomain>): List<Todo> =
        input.map {
            Todo(
                it.id,
                it.title,
                it.description,
                it.done
            )
        }

    fun mapPresenterToDomain(input: Todo): TodoDomain =
        TodoDomain(
            input.id,
            input.title,
            input.description,
            input.done
        )

}