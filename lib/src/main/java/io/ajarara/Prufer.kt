package io.ajarara

object Prufer {
    @JvmStatic
    fun of(encoding: List<Int>): List<Set<Int>> {
        val degrees = MutableList(encoding.size + 2) { 1 }
        encoding.forEach { degrees[it] += 1 }

        val graph = List(encoding.size + 2) { mutableSetOf<Int>() }
        for (prufer in encoding) {
            graph.withIndex()
                    .firstOrNull { degrees[it.index] == 1 }
                    ?.let {
                        it.value.add(prufer)
                        graph[prufer].add(it.index)
                        degrees[prufer] -= 1
                        degrees[it.index] -= 1
                    }
        }

        val lastTwo = degrees.withIndex()
                .filter { it.value == 1 }
                .map { it.index }
        require(lastTwo.size == 2)
        val (u, v) = lastTwo
        graph[u].add(v)
        graph[v].add(u)
        return graph
    }
}