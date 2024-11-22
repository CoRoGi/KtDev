package stack

interface StackInterface<T> {
    val storage: MutableList<T>

    val size: Int

    fun peek(): T?

    fun push(value: T)

    fun pop(): T?
}
