package main

import graph.Edge
import graph.Vertex
import java.util.PriorityQueue

fun main() {
    println("Testing main")
}

fun shortestPath(edges: List<IntArray>, src: Int): Map<Int, Int> {
    val adjacencyList = hashMapOf<Int, ArrayList<Pair<Int, Int>>>()
    val testV = arrayListOf<Vertex<Int>>()
    val testE = arrayListOf<IntArray>()
    val sorted: List<IntArray> = testE.sortedBy { it.first() }

   val stack = ArrayDeque<Int>()
    val test = stack.removeFirst()

    edges.forEach { edge ->
        val source = edge[0]
        val dest = edge[1]
        val weight = edge[2]
        if (source in 0..10)
        adjacencyList[source]?.add(Pair(dest, weight))
            ?: adjacencyList.put(source, arrayListOf(Pair(dest, weight)))
    }

    edges.forEach { edge ->
        val source = edge[0]
        val dest = edge[1]
    }
    val shortest = hashMapOf<Int, Int>()
    val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy {it.second})
    minHeap.offer(Pair(src, 0))

    while (!minHeap.isEmpty()) {
        var curr = minHeap.poll()
        val currWeight = curr.second
        val currDest = curr.first
        if (shortest.containsKey(currDest)) continue
        shortest[currDest] = currWeight

        adjacencyList[currDest]!!.forEach { pair ->
            val n2 = pair.first
            val w2 = pair.second
            if (!shortest.containsKey(n2)) minHeap.add(Pair(n2, currWeight + w2))
        }
    }

    return shortest
}

class NewEdge<T>(source: T, dest: T, weight: Double)
