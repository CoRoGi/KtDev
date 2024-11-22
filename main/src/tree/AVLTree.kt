package tree

import kotlin.math.max

class AVLTree<T: Comparable<T>>() {
    var root: AVLNode<T>? = null

    private fun leftRotate(node: AVLNode<T>): AVLNode<T> {
        var pivot = node.rightChild!!
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        node.height = max(node.rightHeight, node.leftHeight) + 1
        pivot.height = max(pivot.rightHeight, pivot.leftHeight) + 1
        return pivot
    }

    private fun rightRotate(node: AVLNode<T>): AVLNode<T> {
        var pivot = node.leftChild!!
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.height = max(node.rightHeight, node.leftHeight) + 1
        pivot.height = max(pivot.rightHeight, pivot.leftHeight) + 1
        return pivot
    }

    private fun rightLeftRotate(node: AVLNode<T>): AVLNode<T> {
        var rightChild = node.rightChild!!
        rightChild = rightRotate(rightChild)
        return leftRotate(node)
    }

    private fun leftRightRotate(node: AVLNode<T>): AVLNode<T> {
        var leftChild = node.leftChild!!
        leftChild = leftRotate(leftChild)
        return rightRotate(node)
    }

    private fun balanced(node: AVLNode<T>): AVLNode<T> {
        return when (node.balanceFactor) {
            2 -> {
                if (node.leftChild?.balanceFactor == -1) {
                    leftRightRotate(node)
                } else {
                    rightRotate(node)
                }
            }
    
            -2 -> {
                if (node.rightChild?.balanceFactor == 1) {
                    rightLeftRotate(node)
                } else {
                    leftRotate(node)
                }
            }
    
            else -> node
        }
    }

    fun insert(value: T) {
        root = insert(root, value)
    }
    
    private fun insert(node: AVLNode<T>?, value: T): AVLNode<T> {
        node ?: return AVLNode(value)
    
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
    
        val balancedNode = balanced(node)
        balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
    
        return balancedNode
    }

    fun remove(value: T) {
        root = remove(root, value)
    }
    
    private fun remove(node: AVLNode<T>?, value: T): AVLNode<T>? {
      node ?: return null
    
      when {
          value == node.value -> {
              if (node.leftChild == null && node.rightChild == null) {
                  return null
              } else if (node.leftChild == null) {
                  return node.rightChild
              } else if (node.rightChild == null) {
                  return node.leftChild
              }
              node.rightChild?.min?.value?.let {
                  node.value = it
              }
              node.rightChild = remove(node.rightChild, node.value)
          }
    
          value < node.value -> node.leftChild = remove(node.leftChild, value)
          else -> node.rightChild = remove(node.rightChild, value)
      }
    
      val balancedNode = balanced(node)
      balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
      return balancedNode
    }

    
}
