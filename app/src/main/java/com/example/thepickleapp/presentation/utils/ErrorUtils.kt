package com.example.thepickleapp.presentation.utils

object ErrorUtils {
    fun getDefaultErrorData(): ErrorData {
        //ToDo pasar strings a values
        return ErrorData(
            errorTitle = "Ups! Something went wrong :(",
            errorMessage = "We couldn't perform this action for this universe."
        )
    }
}