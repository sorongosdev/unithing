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

    class CurrentUserEvent(val isSuccess: Boolean)
    class PostEvent(val isSuccess: Boolean)
    /**댓글버튼 누른 후 postView 업데이트*/
    class CommentEvent(val isSuccess: Boolean)
    /**댓글 등록을 위해 필요*/
    class CommentRegisterEvent(val isSuccess: Boolean)
    /**포스트 등록 후 피드를 업데이트*/
    class FeedUpdateEvent(val isSuccess: Boolean)
//    class EventGoToStore()
}



