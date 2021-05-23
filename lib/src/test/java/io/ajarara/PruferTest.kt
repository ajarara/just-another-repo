package io.ajarara

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PruferTest {
    @Test
    fun `trivial prufer`() {
        val trivial = Prufer.of(listOf())

        assertTrue(trivial[0] == setOf(1)) { trivial[0].joinToString() }
        assertTrue(trivial[1] == setOf(0)) { trivial[1].joinToString() }
    }

    @Test
    fun `wikipedia prufer`() {
        val wikipedia = Prufer.of(listOf(3, 3, 3, 4))

        assertTrue(wikipedia[0] == setOf(3))
        assertTrue(wikipedia[1] == setOf(3))
        assertTrue(wikipedia[2] == setOf(3))
        assertTrue(wikipedia[3] == setOf(0, 1, 2, 4))
        assertTrue(wikipedia[4] == setOf(3, 5))
        assertTrue(wikipedia[5] == setOf(4))
    }

    @Test
    fun `large linked list prufer`() {
        val large = Prufer.of(List(10_000) { it })

        large.forEach { assertTrue(it.size <= 2) }
    }
}