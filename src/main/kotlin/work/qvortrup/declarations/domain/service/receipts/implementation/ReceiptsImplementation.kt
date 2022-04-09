package work.qvortrup.declarations.domain.service.receipts.implementation

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.service.receipts.Receipts
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ReceiptsImplementation : Receipts {
    override fun createReceipts(declaration: Declaration) {
        declaration.customsReceipt = "Total customs: ${declaration.totalDues?.importTax} gummibears"
        declaration.vatReceipt = "Total VAT: ${declaration.totalDues?.valueAddedTax} gummibears"
    }
}
