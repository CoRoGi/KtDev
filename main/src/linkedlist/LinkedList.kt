package linkedlist

class LinkedList<T>(): MutableCollection<T> {
    var head: Node<T>? = null
    var tail: Node<T>? = null

  // This file was tangled

      override var size = 0
    
     override fun isEmpty(): Boolean {
        return size == 0
     }
    
      override fun add(element: T): Boolean {
        append(element)
        return true
    }
    
    
    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) { append(element) }
        return true
    }
    
    override fun clear() {
        head = null
        tail = null
        size = 0
    }
    
    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }
    
    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements) {
            result = remove(item) || result
        }
        return result
    }
    
    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
    
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }
    
    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }
        return false
    }
    
    override fun containsAll(elements: Collection<T>): Boolean {
        for (searched in elements) {
            if (!contains(searched)) return false
        }
        return true
    }
    
    override fun toString(): String {
        if (isEmpty()) return "Empty list"
        else { return head.toString() }
     }
    
    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    fun push(value: T) {
        head = Node(value, head)
        if (tail == null) {
            tail = head
        }
        size++
    }

    fun pop(): Node<T>? {
        if (isEmpty()) {
            return null
        }
    
        val node = head
        head = head!!.next
        size--
        return node
    }

    fun append(value: T) {
        if (isEmpty()) {
            push(value)
            return
        }
        tail!!.next = Node(value)
        tail = tail!!.next
        size++
    }

    fun removeLast(): Node<T>? {
        //0 item list
        if (isEmpty()) {
            return null
        }
    
        //1 item list
        if (head!!.next == null) {
            return pop()
        }
    
        //2+ item list
        var prev = head
        var current = head
        var next = current!!.next
    
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
    
        prev!!.next = null
        tail = prev
        size--
        return current
    }

    fun nodeAt(index: Int): Node<T>? {
        if (index >= size || index < 0) {
            return null
        }
    
        var current: Node<T> = head!!
        var next = current.next
    
        repeat (index) {
            current = next!!
            next = current.next
        }
    
        return current
    }

    fun insertAt(value: T, index: Int) {
        if (index >= size) {
            append(value)
            return
        }
    
        if (index == 0) {
            push(value)
            return
        }
    
        val prev = nodeAt(index - 1)
        var next = prev!!.next
        val newNode = Node(value, next)
        prev!!.next = newNode
        size++
    
    }

    fun removeAfter(node: Node<T>): Node<T>? {
        val result = node.next ?: return null
    
        if (node.next == tail) {
            tail = node
        }
    
        if (node.next != null) {
            size--
        }
    
        node.next = node.next?.next
        return result
    }

    fun removeValue(data: T): Node<T>? {
      if (isEmpty()) return null
          var node = head
          if (node!!.value == data) {
              size--
              return head!!.next
          }
          while (node!!.next != null) {
              if (node.next!!.value == data) {
                  node.next = node.next!!.next
                  size--
                  return head
              }
              node = node.next
          }
          return head
    }

    fun runner(steps: Int): Node<T>? {
        if (isEmpty()) return null
        var current: Node<T> = head!!
        var next: Node<T>? = current.next
        // This variable will point to the node that "current" is on
        var currentStep = 0
        while (next != null && currentStep < steps) {
            // This is how the actual "running" happens
            current = next!!
            next = current!!.next
            currentStep++
        }
        // This will either return the node at "steps" or the tail
        return current
    }
}
