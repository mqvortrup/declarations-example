package work.qvortrup.declarations.domain.service.select.implementation

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.entity.SelectionResult
import work.qvortrup.declarations.domain.service.select.Selector

class SelectorImplementation : Selector {
    override fun select(declaration: Declaration) {
        declaration.selectionResult = if (declaration.products.any { it.productCode == "S" })
            SelectionResult.RED
        else
            SelectionResult.GREEN
    }
}
