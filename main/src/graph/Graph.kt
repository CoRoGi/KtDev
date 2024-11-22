package graph

import queue.LinkedListQueue
import stack.ArrayListStack

interface Graph<T> {
    fun createVertex(data: T): Vertex<T>

    //adds an Edge, could also be called addEdge
    fun add(edge: EdgeType,
            source: Vertex<T>,
            destination: Vertex<T>,
            weight: Double?)
    {
        when (edge) {
            EdgeType.DIRECTED -> addDirectedEdge(source, destination, weight)
            EdgeType.UNDIRECTED -> addUndirectedEdge(source, destination, weight)
        }
    }

    fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?)

    fun addUndirectedEdge(source: Vertex<T>,
                          destination: Vertex<T>,
                          weight: Double?)
    {
        addDirectedEdge(source, destination, weight)
        addDirectedEdge(destination, source, weight)
    }

    fun edges(source: Vertex<T>): ArrayList<Edge<T>>

    fun weight(source: Vertex<T>, destination: Vertex<T>): Double?

    fun BFS(source: Vertex<T>): ArrayList<Vertex<T>> {
    
        val queue = LinkedListQueue<Vertex<T>>()
        val enqueued = ArrayList<Vertex<T>>()
        val visited = ArrayList<Vertex<T>>()
    
        queue.enqueue(source)
        enqueued.add(source)
    
        while (true) {
            val vertex = queue.dequeue() ?: break
            visited.add(vertex)
            edges(vertex).forEach {
                if (!enqueued.contains(it.destination)) {
                    queue.enqueue(it.destination)
                    enqueued.add(it.destination)
                }
            }
        }
    
        return visited
    }

    fun DFS(source: Vertex<T>): ArrayList<Vertex<T>> {
    
        val stack = ArrayListStack<Vertex<T>>()
        val pushed = mutableSetOf<Vertex<T>>()
        val visited = ArrayList<Vertex<T>>()
    
        stack.push(source)
        pushed.add(source)
        visited.add(source)
    
        outer@ while (true) {
    
            if (stack.isEmpty()) break
    
            val vertex = stack.peek()!!
    
            if (edges(vertex).isEmpty()) {
                stack.pop()
                continue
            }
    
            for (i in 0 until edges(vertex).size) {
                val destination = edges(vertex)[i].destination
    
                if (destination !in pushed) {
                    stack.push(destination)
                    pushed.add(destination)
                    visited.add(destination)
                    continue@outer
                }
            }
    
            stack.pop()
        }
    
        return visited
    
    }
  }
