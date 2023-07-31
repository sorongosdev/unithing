package com.unimind.unithing

import com.google.android.play.integrity.internal.t
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

object RxEventBus {

    private val publisher =  ReplaySubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}

class RxEvents {

    class CurrentUserEventSetRoom(val isSuccess: Boolean)
    class PostEventSetRoom(val isSuccess: Boolean)
    class EventGoToStore()
}



