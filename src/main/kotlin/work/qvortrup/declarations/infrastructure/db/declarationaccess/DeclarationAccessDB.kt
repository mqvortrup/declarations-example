package work.qvortrup.declarations.infrastructure.db.declarationaccess

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.service.storage.DeclarationAccess

class DeclarationAccessDB: DeclarationAccess {
    override fun store(declaration: Declaration) {
        println("Storing declaration $declaration")
    }

}
