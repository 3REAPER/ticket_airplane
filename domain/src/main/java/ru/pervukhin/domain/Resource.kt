package ru.pervukhin.domain

class Resource<T> private constructor(
    val status: Status,
    val data: T? = null
) {

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(status = Status.Success, data = data)
        }

        fun <T> error(): Resource<T> {
            return Resource(status = Status.Error)
        }

        fun <T> loading(): Resource<T> {
            return Resource(status = Status.Loading)
        }

    }

    fun ifSuccess(block: (T?) -> Unit): Resource<T> {
        if (status == Status.Success) {
            block.invoke(data)
        }

        return this
    }

    fun ifError(block: () -> Unit): Resource<T> {
        if (status == Status.Error) {
            block.invoke()
        }

        return this
    }

    fun ifLoading(block: () -> Unit): Resource<T> {
        if (status == Status.Loading) {
            block.invoke()
        }

        return this
    }

    enum class Status {
        Error,
        Success,
        Loading
    }
}