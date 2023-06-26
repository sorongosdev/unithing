package com.unimind.unithing

import android.content.Context
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

object RxEventBus {

    private val publisher =  ReplaySubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}

class RxEvents {

    class EventSetRoom(val isSuccess: Boolean)
    class EventSetRoom2(val isSuccess: Boolean)
    class EventGoToStore()
}



