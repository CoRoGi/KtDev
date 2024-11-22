package tree

typealias AVLVisitor<T> = (AVLNode<T>) -> Unit

class AVLNode<T>(var value: T) {
    var leftChild : AVLNode<T>? = null
    var rightChild : AVLNode<T>? = null

    val min:AVLNode<T>?
        get() = leftChild?.min ?: this

    var height = 0

    var leftHeight: Int = leftChild?.height ?: -1
    var rightHeight: Int = rightChild?.height ?: -1

    var balanceFactor = leftHeight - rightHeight
}
