package heap

class MaxHeap<T: Comparable<T>>: Heap<T>() {
  override fun isHigherPriority(a: T, b: T): Boolean {
    return a.compareTo(b) == 1
  }

  companion object {
    fun <T: Comparable<T>> create(elements: ArrayList<T>): MaxHeap<T> {
      val heap = MaxHeap<T>()
      heap.heapify(elements)
      return heap
    }
  }
}
