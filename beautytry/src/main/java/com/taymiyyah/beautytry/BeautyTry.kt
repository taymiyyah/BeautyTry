package com.taymiyyah.beautytry


sealed class BlockState<out T> {
    class Success<T> : BlockState<T>()
    class Failed<T>(val exception: Throwable = Exception()) : BlockState<T>()
}

object BeautyTry {
    var sharedOnFailedLogic: () -> Unit = {}
}

inline fun <T> tries(block: () -> T): BlockState<T> {
    return try {
        block()
        BlockState.Success()
    } catch (e: Throwable) {
        try {
            BeautyTry.sharedOnFailedLogic()
        } catch (e: Exception) {
        }
        e.printStackTrace()
        BlockState.Failed(e)
    }
}

inline fun <T> BlockState<T>.and(block: () -> T): BlockState<T> {
    val result = tries(block)
    return if (this is BlockState.Failed)
        this
    else
        result
}

inline fun <T> BlockState<T>.onFailed(block: (exception: Throwable) -> T): BlockState<T> {
    if (this is BlockState.Failed) {
        tries {
            block(this.exception)
        }
    }
    return this
}

inline fun <T> BlockState<T>.onAlways(block: () -> T): BlockState<T> {
    tries(block)
    return this
}

