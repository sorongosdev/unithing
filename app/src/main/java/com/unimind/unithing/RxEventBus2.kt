package com.unimind.unithing

import android.content.Context
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

object RxEventBus2 {

    private val publisher =  ReplaySubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}

class RxEvents2 {

    class EventSetRoom(val isSuccess: Boolean)
    class EventGoToStore()
}



