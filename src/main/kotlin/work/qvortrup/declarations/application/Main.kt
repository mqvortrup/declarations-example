package work.qvortrup.declarations.application

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.entity.Product

fun main(args: Array<String>) {
    val app = AppCreator()

    app.receiver.run()
}