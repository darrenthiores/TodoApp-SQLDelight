package com.darrenthiores.core.di

import android.content.ContentValues
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.transaction
import com.darrenthiores.core.BuildConfig
import com.darrenthiores.core.TodoDatabase
import com.darrenthiores.core.data.local.LocalDataSource
import com.darrenthiores.core.data.local.TodoDataSource
import com.darrenthiores.core.data.local.TodoDataSourceImpl
import com.darrenthiores.core.data.repository.ITodoRepository
import com.darrenthiores.core.data.repository.TodoRepository
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import todoappcompose.tododb.TodoEntity
import java.util.concurrent.Executors

val databaseModule = module {

    single<SqlDriver> {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.PASSWORD.toCharArray())
        val factory = SupportFactory(passphrase)

        AndroidSqliteDriver(
            schema = TodoDatabase.Schema,
            context = androidApplication(),
            name = "todo.db",
            factory = factory,
            callback = object : AndroidSqliteDriver.Callback(TodoDatabase.Schema) {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    fillWithStartingData(db)
                }
            }
        )
    }

    single<TodoDataSource> { TodoDataSourceImpl(TodoDatabase(get())) }

}

private fun fillWithStartingData(db: SupportSQLiteDatabase) {
    Executors.newSingleThreadExecutor().execute {
        db.transaction {
            listOfTodos().forEach {
                db.insert(
                    "todoEntity",
                    SQLiteDatabase.CONFLICT_REPLACE,
                    ContentValues()
                        .apply {
                            putNull("id")
                            put("title", it.title)
                            put("description", it.description)
                            putNull("done")
                        }
                )
            }
        }
    }
}

private fun listOfTodos(): List<TodoEntity> = listOf(
    TodoEntity(
        id = 0,
        title = "This is sample 1",
        description = "This is just a sample, delete or update it",
        done = null
    ),
    TodoEntity(
        id = 0,
        title = "This is sample 2",
        description = "This is just a sample, delete or update it",
        done = null
    ),
    TodoEntity(
        id = 0,
        title = "This is sample 3",
        description = "This is just a sample, delete or update it",
        done = null
    )
)

val repositoryModule = module {

    single { LocalDataSource(get()) }

    single<ITodoRepository> { TodoRepository(get()) }

}