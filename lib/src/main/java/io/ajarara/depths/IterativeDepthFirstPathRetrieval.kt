package io.ajarara

import java.util.*

fun iterativeDepthFirstPathRetrieval(
        adj: Map<Int, List<Int>>,
        rootLabel: Int,
        targetLabel: Int
): List<Int> {
    val stack = LinkedList<Step>()
    stack.add(Step(previous = null, label = rootLabel))
    while (!stack.isEmpty()) {
        val curr = stack.pop()
        if (curr.label == targetLabel) {
            return reconstruct(curr)
        } else {
            adj.getValue(curr.label)
                    .asReversed()
                    .map { Step(curr, it) }
                    .forEach(stack::push)
        }
    }
    return listOf()
}

private fun reconstruct(path: Step): List<Int> {
    var curr: Step? = path
    val out = mutableListOf<Int>()
    while(curr != null) {
        out.add(path.label)
        curr = curr.previous
    }

    return generateSequence(path) { path.previous }
            .map { it.label }
            .toList()
}

private class Step(
        val previous: Step?,
        val label: Int
)