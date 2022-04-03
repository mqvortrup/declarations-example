package work.qvortrup.declarations.infrastructure.db.receiptaccess

import work.qvortrup.declarations.domain.service.storage.ReceiptAccess

class ReceiptAccessDB: ReceiptAccess {
    override fun store(receipt: String) {
        println("Storing receipt: $receipt")
    }
}
