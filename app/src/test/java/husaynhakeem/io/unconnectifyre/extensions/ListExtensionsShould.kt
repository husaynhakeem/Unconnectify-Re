package husaynhakeem.io.unconnectifyre.extensions

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ListExtensionsShould {

    @Test
    fun addAnElementToAListIfPredicateReturnsTrue() {
        val element = "any element"
        val predicate = { true }
        val list = mutableListOf<String>()

        list.addIf(element, predicate)

        assertTrue(list.contains(element))
    }

    @Test
    fun notAddAnElementToAListIfPredicateReturnsFalse() {
        val element = "any element"
        val predicate = { false }
        val list = mutableListOf<String>()

        list.addIf(element, predicate)

        assertFalse(list.contains(element))
    }
}