package com.merefa.team.rickandmorty.data.network
import androidx.annotation.Keep

@Keep
sealed class ResultObject<out DATA_TYPE> {

    class Success<DATA_TYPE>(
        val data: DATA_TYPE
    ) : ResultObject<DATA_TYPE>()

    class Error(
        val errorMessage: String,
        val exception: Exception? = null
    ) : ResultObject<Nothing>()
}
