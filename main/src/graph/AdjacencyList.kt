package graph

class AdjacencyList<T>: Graph<T> {

  private val adjacencies: HashMap<Vertex<T>, ArrayList<Edge<T>>> = HashMap()

  override fun createVertex(data: T): Vertex<T> {
      val vertex = Vertex(adjacencies.count(), data)
      adjacencies[vertex] = ArrayList<Edge<T>>()
      return vertex
  }

  override fun addDirectedEdge(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
      val edge = Edge(source, destination, weight)
      adjacencies[source]?.add(edge)
  }

  override fun edges(source: Vertex<T>): ArrayList<Edge<T>> {
      return adjacencies[source] ?: arrayListOf()
  }

  override fun weight(source: Vertex<T>, destination: Vertex<T>): Double? {
      edges(source).forEach {
          if (it.destination == destination) {
              return it.weight ?: null
          }
      }
      return 0.0
  }
}
