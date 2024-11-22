package queue

class ArrayListQueue<T>: AbstractQueue<T>() {
    override val storage = ArrayList<T>()

    override fun peek(): T? {
        return if (size == 0) {
            null
        } else {
            storage[0]
        }
    }

    override fun dequeue(): T? {
        return if (size == 0) {
            null
        } else {
            storage.removeAt(0)
        }
    }

    override fun enqueue(item: T) {
        storage.add(item)
    }
}
