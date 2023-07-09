package lib.kg.newcleanarch.data.base

import kotlinx.coroutines.flow.flow
import lib.kg.newcleanarch.domain.utils.Resource
import java.lang.Exception

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(request()))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "unknown error"))
        }
    }
}