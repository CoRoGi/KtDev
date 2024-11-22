package heap

abstract class Heap<T: Comparable<T>>() {
    var elements: ArrayList<T> = ArrayList<T>()

    val count: Int
        get() = elements.size

    fun peek(): T? = elements.firstOrNull()

    abstract fun isHigherPriority(a: T, b: T): Boolean

    private fun leftChildIndex(index: Int) = (index * 2) + 1
    private fun rightChildIndex(index: Int) = (index * 2) + 2
    private fun parentIndex(index: Int) = (index - 1) / 2

    private fun swap(a: Int, b: Int) {
        var temp = elements[a]
        elements[a] = elements[b]
        elements[b] = temp
    }

    private fun siftUp(index: Int) {
        var child = index
        var parent = parentIndex(child)
    
        while (child > 0 && isHigherPriority(elements[child], elements[parent])) {
            swap(child, parent)
            child = parent
            parent = parentIndex(child)
        }
    }

    private fun siftDown(index: Int) {
      var parent = index
    
      while (true) {
          var leftChild = leftChildIndex(parent)
          var rightChild = rightChildIndex(parent)
          var candidate = parent
    
          if (leftChild < count && !isHigherPriority(elements[candidate], elements[leftChild])) {
              candidate = leftChild
          }
    
          if (rightChild < count && !isHigherPriority(elements[candidate], elements[rightChild])) {
              candidate = rightChild
          }
    
          if (candidate == parent) {
              return
          }
    
          swap(parent, candidate)
          parent = candidate
      }
    }

    

    fun remove(): T? {
        if (elements.size == 0) {
            return null
        }
    
        swap(0, elements.size - 1)
        val item = elements.removeAt(elements.size - 1)
        siftDown(0)
        return item
    }
    
    fun remove(index: Int): T? {
        if (elements.size == 0) {
            return null
        }
    
        if (index == elements.size - 1) {
            return elements.removeAt(elements.size - 1)
        }
    
        swap(index, elements.size - 1)
        val item = elements.removeAt(elements.size - 1)
        siftDown(index)
        siftUp(index)
        return item
    }

    
    fun indexOf(element: T): Int {
      return indexOf(element, 0)
    }
    
    private fun indexOf(element: T, index: Int): Int {
        if (elements[index] == element) {
            return index
        }
    
        var leftChild = indexOf(element, leftChildIndex(index))
    
        if (leftChild != -1) {
            return leftChild
        }
    
        var rightChild = indexOf(element, rightChildIndex(index))
    
        if (rightChild != -1) {
            return rightChild
        }
    
        return -1
    }

    fun heapify(values: ArrayList<T>) {
      elements = values
      if (!elements.isEmpty()) {
        (count / 2 downTo 0).forEach {
          siftDown(it)
        }
      }
    }
    
}
