package tree

import queue.LinkedListQueue

typealias TreeNodeVisitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {
    private val children : MutableList<TreeNode<T>> = mutableListOf()

    fun add(value: T) {
        val newNode = TreeNode<T>(value)
        children.add(newNode)
    }

    fun addNode(child: TreeNode<T>) {
        children.add(child)
    }

    fun remove(node: TreeNode<T>): TreeNode<T>? {
        this.children.addAll(node.children)
        val nodeIndex = children.indexOf(node)
        if (nodeIndex == -1) return null
        val removedNode = children.removeAt(nodeIndex)
        return removedNode
    }

    fun removeRecursive(node: TreeNode<T>): TreeNode<T>? {
        val nodeIndex = children.indexOf(node)
        if (nodeIndex == -1) return null
        val removedNode = children.removeAt(nodeIndex)
        return removedNode
    }

    fun breadthFirstTraversal(visit: TreeNodeVisitor<T>) {
        visit(this)
        val queue = LinkedListQueue<TreeNode<T>>()
        this.children.forEach {
            queue.enqueue(it)
        }
    
        var nextNode = queue.dequeue()
        while (nextNode != null) {
            visit(nextNode)
            nextNode.children.forEach { child ->
                queue.enqueue(child) 
            }
            nextNode = queue.dequeue()
        }
    
    }

    fun depthFirstTraversal(visit: TreeNodeVisitor<T>) {
        visit(this)
    
        this.children.forEach { child ->
            child.depthFirstTraversal(visit)
        }
    }
}
