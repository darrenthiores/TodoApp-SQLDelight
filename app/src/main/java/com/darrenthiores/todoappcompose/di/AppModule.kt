package com.darrenthiores.todoappcompose.di

import com.darrenthiores.core.domain.TodoInteractor
import com.darrenthiores.core.domain.TodoUseCase
import com.darrenthiores.todoappcompose.viewModel.DetailViewModel
import com.darrenthiores.todoappcompose.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {

    factory<TodoUseCase> { TodoInteractor(get()) }

}

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }

}