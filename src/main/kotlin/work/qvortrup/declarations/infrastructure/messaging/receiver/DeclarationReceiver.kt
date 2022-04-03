package work.qvortrup.declarations.infrastructure.messaging.receiver

import work.qvortrup.declarations.controller.async.AsyncDeclaration
import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.entity.Product

/**
 * This class would be set up as a listener on a queue, forwarding to the async declaration
 */
class DeclarationReceiver(val asyncDeclaration: AsyncDeclaration): Runnable {
    override fun run() {
        val declarations = listOf(
            Declaration(
                "B Cust",
                Product("A", 5)
            ),
            Declaration(
                "A Cust",
                Product("B", 10)
            ),
            Declaration(
                "C Cust",
                Product("B", 10)
            ),
            Declaration(
                "A Cust",
                Product("S", 10)
            )
        )
        declarations.forEach {
            println("Received declaration: $it")
            asyncDeclaration.processDeclaration(it)
        }
    }
}