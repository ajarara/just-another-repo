package io.ajarara.depths;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class JavaIterativeDepthFirstPathRetrieval {

    public static List<Integer> iteratorBased(
            final Map<Integer, List<Integer>> adjacency,
            final Integer rootLabel,
            final Integer targetLabel
    ) {
        final LinkedList<Step> stack = new LinkedList<>();
        stack.add(new Step(null, rootLabel));
        while(!stack.isEmpty()) {
            Step curr = stack.pop();
            if (curr.label.equals(targetLabel)) {
                return curr.walkUp();
            } else {
                for (Integer cand : adjacency.get(curr.label)) {
                    stack.add(new Step(curr, cand));
                }
            }
        }

        return Collections.emptyList();
    }

    public static List<Integer> streamBased(
            final Map<Integer, List<Integer>> adjacency,
            final int rootLabel,
            final int targetLabel
    ) {
        final LinkedList<Step> stack = new LinkedList<>();
        stack.add(new Step(null, rootLabel));
        while (!stack.isEmpty()) {
            final Step curr = stack.pop();
            if (curr.label == targetLabel) {
                return curr.walkUp();
            } else {
                adjacency.get(curr.label)
                        .stream()
                        .map(cand -> new Step(curr, cand))
                        .forEach(stack::push);
            }
        }

        return Collections.emptyList();
    }

    private JavaIterativeDepthFirstPathRetrieval() {
        throw new IllegalStateException("Cannot be instantiated");
    }
}
