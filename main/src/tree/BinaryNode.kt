package tree

typealias BinaryVisitor<T> = (BinaryNode<T>?) -> Unit

class BinaryNode<T>(var value: T) {
    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    val min: BinaryNode<T>?
        get() = leftChild?.min ?: this

    fun traverseInOrder(visit: BinaryVisitor<T>) {
        leftChild?.traverseInOrder(visit)
        visit(this)
        rightChild?.traverseInOrder(visit)
    }

    fun traversePreOrder(visit: BinaryVisitor<T>) {
        visit(this)
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }
    
    fun traversePreOrderWithNull(visit: BinaryVisitor<T>) {
        visit(this)
        leftChild?.traversePreOrder(visit) ?: visit(null)
        rightChild?.traversePreOrder(visit) ?: visit(null)
    }

    fun traversePostOrder(visit: BinaryVisitor<T>) {
        visit(this)
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
    }

    fun serialize(node: BinaryNode<T> = this): MutableList<T?> {
        val list = mutableListOf<T?>()
        node.traversePreOrderWithNull { currentNode ->
            currentNode?.let {
                list.add(currentNode.value)
            } ?: list.add(null)
        }
        return list
    }

    fun deserialize(list: MutableList<T?>): BinaryNode<T>? {
        var rootValue = list.removeAt(list.size - 1) ?: return null
        val root = BinaryNode<T>(rootValue)
    
        root.leftChild = deserialize(list)
        root.rightChild = deserialize(list)
    
        return root
    }
}
