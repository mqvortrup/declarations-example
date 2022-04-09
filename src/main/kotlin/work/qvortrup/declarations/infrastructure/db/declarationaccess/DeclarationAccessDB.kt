package work.qvortrup.declarations.infrastructure.db.declarationaccess

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.service.storage.DeclarationAccess
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DeclarationAccessDB: DeclarationAccess {
    override fun store(declaration: Declaration) {
        println("Storing declaration $declaration")
    }

}
