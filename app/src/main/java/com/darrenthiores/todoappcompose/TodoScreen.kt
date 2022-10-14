package com.darrenthiores.todoappcompose

enum class TodoScreen {
    Landing,
    Home,
    Detail;

    companion object{
        fun fromRoute(route: String?): TodoScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Detail.name -> Detail
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}