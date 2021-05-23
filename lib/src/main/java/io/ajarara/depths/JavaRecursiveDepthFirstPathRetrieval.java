package io.ajarara.depths;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JavaRecursiveDepthFirstPathRetrieval {

    public static List<Integer> iteratorBased(
            final Map<Integer, List<Integer>> adjacency,
            final Integer rootLabel,
            final Integer targetLabel
    ) {
        if (rootLabel.equals(targetLabel)) {
            return Collections.singletonList(rootLabel);
        }

        return iteratorRecursion(
                adjacency,
                targetLabel,
                new Step(null, rootLabel)
        );
    }

    private static List<Integer> iteratorRecursion(
            final Map<Integer, List<Integer>> adjacency,
            final Integer targetLabel,
            final Step current
    ) {
        if (current.label.equals(targetLabel)) {
            return current.walkUp();
        } else {
            for (Integer newLabel : adjacency.get(current.label)) {
                List<Integer> path = iteratorRecursion(
                        adjacency,
                        targetLabel,
                        new Step(current, newLabel)
                );

                if (!path.isEmpty()) {
                    return path;
                }
            }
            return Collections.emptyList();
        }
    }

    public static List<Integer> streamBased(
            final Map<Integer, List<Integer>> adjacency,
            final Integer rootLabel,
            final Integer targetLabel
    ) {
        if (rootLabel.equals(targetLabel)) {
            return Collections.singletonList(rootLabel);
        }

        return streamRecursion(
                adjacency,
                targetLabel,
                new Step(null, rootLabel)
        );
    }

    private static List<Integer> streamRecursion(
            final Map<Integer, List<Integer>> adjacency,
            final Integer targetLabel,
            final Step current
    ) {
        if (current.label.equals(targetLabel)) {
            return current.walkUp();
        } else {
            return adjacency.get(current.label).stream()
                    .map(child -> streamRecursion(
                            adjacency,
                            targetLabel,
                            new Step(current, child)
                    ))
                    .filter(ls -> !ls.isEmpty())
                    .findFirst()
                    .orElse(Collections.emptyList());
        }
    }


    private JavaRecursiveDepthFirstPathRetrieval() {
        throw new IllegalStateException("Cannot be instantiated");
    }
}
