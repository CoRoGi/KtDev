package stack

abstract class AbstractStack<T>: StackInterface<T>, MutableCollection<T> {
  abstract override val storage: MutableList<T>

  abstract override val size: Int

  abstract override fun peek(): T?

  abstract override fun push(value: T)

  abstract override fun pop(): T?

  override fun toString(): String {
    return storage.toString()
  }

   override fun clear() {
    storage.removeAll(storage)
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
  
  override fun contains(element: T): Boolean {
    return storage.contains(element)
  }
  
  override fun isEmpty(): Boolean {
      return size == 0
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
  

}
