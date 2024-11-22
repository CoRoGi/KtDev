package stack

class ArrayListStack<T>(): AbstractStack<T>() {

  override fun toString(): String {
      return storage.toString()
  }

  override val storage: MutableList<T> = ArrayList()

  override val size: Int
    get() = storage.size

  override fun peek(): T? {
      if (isEmpty()) return null
      return storage.last()
  }

  override fun push(value: T) {
    storage.add(value)
  }

  override fun pop(): T? {
    if (isEmpty()) return null
    val popped = storage.removeLast()
    return popped
  }
}
