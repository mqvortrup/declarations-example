package work.qvortrup.declarations.controller.async.implementation

import work.qvortrup.declarations.controller.async.AsyncDeclaration
import work.qvortrup.declarations.controller.async.AsyncReplier
import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.usecase.declare.Declarator
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * This class has the responsibility of collection the possible errors and replying differently in case of errors
 */
@ApplicationScoped
class AsyncDeclarationImplementation @Inject constructor(val declarator: Declarator, val replier: AsyncReplier) : AsyncDeclaration {
    override fun processDeclaration(declaration: Declaration) {
        try {
            declarator.processDeclaration(declaration)
            replier.onSuccess(declaration)
        } catch (t : Throwable) {
            replier.onFailure(declaration, listOf(t))
        }
    }
}