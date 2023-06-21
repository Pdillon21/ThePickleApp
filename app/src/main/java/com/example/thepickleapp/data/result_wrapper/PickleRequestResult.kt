package com.example.thepickleapp.data.result_wrapper

import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus.SUCCESS
import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus.SERVER_ERROR
import com.example.thepickleapp.data.result_wrapper.PickleResponseStatus.CONNECTION_ERROR

data class PickleRequestResult<out T>(
    val status: PickleResponseStatus,
    val data: T? = null,
    val errorMessage: String? = null
) {
    companion object {
        fun <T> success(data: T?): PickleRequestResult<T> =
            PickleRequestResult(SUCCESS, data = data)

        fun <T> serverError(errorMessage: String? = null): PickleRequestResult<T> =
            PickleRequestResult(status = SERVER_ERROR, errorMessage = errorMessage)

        fun <T> connectionError(errorMessage: String? = null): PickleRequestResult<T> =
            PickleRequestResult(
                status = CONNECTION_ERROR,
                errorMessage = errorMessage
            )
    }
}
