package queue

interface QueueInterface<T>: MutableCollection<T> {

    override val size: Int

    val storage: MutableCollection<T>

    fun peek(): T?

    fun enqueue(item: T)

    fun dequeue(): T?

}
