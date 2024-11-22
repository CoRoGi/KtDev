package queue

import stack.ArrayListStack

class StackQueue<T>: AbstractQueue<T>() {
    // The stack we pull from for read operations
    private var readStack = ArrayListStack<T>()
    // The stack we insert into on write operations
    private var writeStack = ArrayListStack<T>()

    override val storage: MutableCollection<T>
        get() = writeStack.plus(readStack).toMutableList()

    override fun toString(): String {
        if (writeStack.size == 0) {
            val reversed = readStack.reversed()
            return reversed.toString()
        }
        if (readStack.size == 0) {
            return writeStack.toString()
        }
        val reversed = readStack.reversed()
        val newList = reversed.plus(writeStack)
        return newList.toString()
    }
    private fun transferElements() {
        while (writeStack.size > 0) {
            readStack.push(writeStack.pop()!!)
        }
    }

    override fun peek(): T? {
        if (size == 0) return null
        if (readStack.size == 0) transferElements()
        return readStack.peek()
    }

    override fun dequeue(): T? {
        if (size == 0) return null
        if (readStack.size == 0) transferElements()
        return readStack.pop()
    }

    override fun enqueue(item: T) {
        writeStack.push(item)
    }
}
