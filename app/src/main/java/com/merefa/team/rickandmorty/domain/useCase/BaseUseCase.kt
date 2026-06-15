package com.merefa.team.rickandmorty.domain.useCase

import android.accounts.NetworkErrorException
import com.merefa.team.rickandmorty.data.network.ResultObject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = BaseUseCase.Request<T>.() -> Unit

abstract class BaseUseCase<T> {

    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected lateinit var coroutineScope: CoroutineScope

    private val isActive = AtomicBoolean(false)
    private val isFinished = AtomicBoolean(false)

    abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        this@BaseUseCase.isActive.set(true)
        this@BaseUseCase.isFinished.set(false)
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        coroutineScope = CoroutineScope(foregroundContext + parentJob)
        coroutineScope.launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground()
                }
                isFinished.set(true)
                response(result)
            } catch (cancellationException: CancellationException) {
                cancellationException.printStackTrace()
                isFinished.set(true)
                response(cancellationException)
            } catch (e: Exception) {
                e.printStackTrace()
                isFinished.set(true)
                response(e)
            }
            this@BaseUseCase.isActive.set(false)
        }
    }

    fun isActive() = isActive.get()

    fun isFinished() = isFinished.get()

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    protected fun handleErrorResultObject(result: ResultObject.Error): Exception {
        return result.exception ?: NetworkErrorException(result.errorMessage)
    }

    class Request<T> {

        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((Exception) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (Exception) -> Unit) {
            onError = block
        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }

        operator fun invoke(result: T) {

            onComplete?.invoke(result)
        }

        operator fun invoke(error: Exception) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}