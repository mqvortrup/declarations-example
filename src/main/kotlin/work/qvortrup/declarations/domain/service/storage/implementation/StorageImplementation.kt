package work.qvortrup.declarations.domain.service.storage.implementation

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.service.storage.DeclarationAccess
import work.qvortrup.declarations.domain.service.storage.ReceiptAccess
import work.qvortrup.declarations.domain.service.storage.Storage

class StorageImplementation(val declarationAccess: DeclarationAccess, val receiptAccess: ReceiptAccess): Storage {
    override fun store(declaration: Declaration) {
        declarationAccess.store(declaration)
        receiptAccess.store(declaration.customsReceipt!!)
        receiptAccess.store(declaration.vatReceipt!!)
    }

}
