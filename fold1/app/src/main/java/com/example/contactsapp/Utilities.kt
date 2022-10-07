package com.example.contactsapp

import android.view.View
import kotlinx.coroutines.*

inline fun <T> View.doAsync(
    crossinline backgroundTask: (scope: CoroutineScope) -> T,
    crossinline result: (T?) -> Unit
) {
    val job = CoroutineScope(Dispatchers.Main)

    val attachListener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(p0: View?) {}

        override fun onViewDetachedFromWindow(p0: View?) {
            job.cancel()
            removeOnAttachStateChangeListener(this)
        }
    }

    this.addOnAttachStateChangeListener(attachListener)

    job.launch {
        val data = async(Dispatchers.Default) {
            try {
                backgroundTask(this)
            } catch (e: Exception){
                e.printStackTrace()
                return@async null
            }
        }
        if (isActive) {
            try {
                result.invoke(data.await())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }

        this@doAsync.removeOnAttachStateChangeListener(attachListener)
    }
}