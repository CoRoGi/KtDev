package queue

  abstract class AbstractQueue<T> : QueueInterface<T> {
    override fun toString(): String {
        return storage.toString()
    }
    override val size: Int
        get() = storage.size

    abstract override val storage: MutableCollection<T>

    abstract override fun peek(): T?

    abstract override fun dequeue(): T?

    abstract override fun enqueue(item: T)

    override fun contains(element: T): Boolean {
        return storage.contains(element)
    }

    override fun clear() {
        storage.clear()
    }

    override fun isEmpty(): Boolean {
        return storage.isEmpty()
    }

    override fun iterator(): MutableIterator<T> {
        return storage.iterator()
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return storage.retainAll(elements)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return storage.removeAll(elements)
    }

    override fun remove(element: T): Boolean {
        return storage.remove(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return storage.containsAll(elements)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return storage.addAll(elements)
    }

    override fun add(element: T): Boolean {
        return storage.add(element)
    }
}
