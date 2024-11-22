package heap

class MinHeap<T: Comparable<T>>: Heap<T>() {
  override fun isHigherPriority(a: T, b: T): Boolean {
    return a.compareTo(b) == -1
  }

  companion object {
    fun <T: Comparable<T>> create(elements: ArrayList<T>): MinHeap<T> {
      val heap = MinHeap<T>()
      heap.heapify(elements)
      return heap
    }
  }
}
