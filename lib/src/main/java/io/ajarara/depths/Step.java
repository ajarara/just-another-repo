package io.ajarara.depths;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Step {
    @Nullable
    final Step previous;
    @NotNull
    final Integer label;

    Step(@Nullable Step previous, @NotNull Integer label) {
        this.previous = previous;
        this.label = Objects.requireNonNull(label);
    }

    public List<Integer> walkUp() {
        final List<Integer> out = new ArrayList<>();
        Step curr = this;
        while (curr != null) {
            out.add(curr.label);
            curr = curr.previous;
        }
        return out;
    }
}
