package husaynhakeem.io.unconnectifyre.utilities

import java.util.concurrent.Executors


private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun runOnIoThread(block: () -> Unit) {
    IO_EXECUTOR.execute { block.invoke() }
}