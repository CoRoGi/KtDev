package linkedlist

class Node<T>(var value: T, var next: Node<T>? = null, var prev: Node<T>? = null) {
    override fun toString(): String {
        return if (this.next != null) {
            "$value -> ${this.next.toString()}"
        } else {
            "$value"
        }
    }
}
