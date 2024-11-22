package tree

class BinarySearchTree<T: Comparable<T>>() {
    var root: BinaryNode<T>? = null

  fun insert(value: T) {
      root = insert(root, value)
  }
  
  private fun insert(root: BinaryNode<T>?, value: T): BinaryNode<T>? {
      root ?: return BinaryNode(value)
  
      if (value < root.value) {
          root.leftChild = insert(root.leftChild, value)
      } else {
          root.rightChild = insert(root.rightChild, value)
      }
  
      return root
  }

  fun remove(value: T) {
      root = remove(root, value)
  }
  
  private fun remove(node: BinaryNode<T>?, value: T): BinaryNode<T>? {
      node ?: return null
  
      when {
          value == node.value -> {
              if (node.leftChild == null && node.rightChild == null) {
                  return null
              }
              if (node.leftChild == null) {
                  return node.rightChild
              }
              if (node.rightChild == null) {
                  return node.leftChild
              }
              node.rightChild?.min?.value?.let {
                  node.value = it
                  node.rightChild = remove(node.rightChild, node.value)
              }
          }
          value < node.value -> node.leftChild = remove(node.leftChild, node.value)
          else -> node.rightChild = remove(node.rightChild, node.value)
      }
  
      return node
  }

  fun contains(value: T): Boolean {
      var current = root
      while (current != null) {
          if (current.value == value) {
              return true
          } else if (current.value < value) {
              current = current.rightChild
          } else if (current.value > value) {
              current = current.leftChild
          }
      }
      return false
  }
}
