package queue

import linkedlist.LinkedList

class LinkedListQueue<T>: AbstractQueue<T>() {
    override val storage = LinkedList<T>()

    override fun peek(): T? {
        if (isEmpty()) return null
        return storage.head!!.value
    }

    override fun dequeue(): T? {
        if (isEmpty()) return null
        return storage.pop()!!.value
    }

    override fun enqueue(item: T) {
        storage.append(item)
    }
}
