package stack

import queue.LinkedListQueue

class QueueStack<T>: AbstractStack<T>() {
  private val leftQueue = LinkedListQueue<T>()
  private val rightQueue = LinkedListQueue<T>()

  override val storage: MutableList<T>
      get() = rightQueue.plus(leftQueue).toMutableList()

  override val size: Int
      get() = storage.size

  override fun peek(): T? {
      return leftQueue.peek()
  }

  override fun pop(): T? {
      if (isEmpty()) return null
      val popped = leftQueue.dequeue()
      while (!rightQueue.isEmpty()) {
          leftQueue.enqueue(rightQueue.dequeue()!!)
      }
      while (leftQueue.size > 1) {
          rightQueue.enqueue(leftQueue.dequeue()!!)
      }
      return popped
  }

  override fun push(value: T) {
      while (!leftQueue.isEmpty()) {
          rightQueue.enqueue(leftQueue.dequeue()!!)
      }
      leftQueue.enqueue(value)
  }

}
