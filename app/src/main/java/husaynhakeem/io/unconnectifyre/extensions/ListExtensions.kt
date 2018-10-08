package husaynhakeem.io.unconnectifyre.extensions


fun <T> MutableList<T>.addIf(element: T, predicate: () -> Boolean) {
    if (predicate()) {
        this.add(element)
    }
}