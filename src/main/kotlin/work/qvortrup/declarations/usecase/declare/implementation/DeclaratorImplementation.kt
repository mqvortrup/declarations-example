package work.qvortrup.declarations.usecase.declare.implementation

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.service.calculate.Calculator
import work.qvortrup.declarations.domain.service.enrich.Enricher
import work.qvortrup.declarations.domain.service.receipts.Receipts
import work.qvortrup.declarations.domain.service.select.Selector
import work.qvortrup.declarations.domain.service.storage.Storage
import work.qvortrup.declarations.domain.service.validate.Validator
import work.qvortrup.declarations.usecase.declare.Declarator
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * The only responsibility of this class is to process the declaration according to the business rules
 */
@ApplicationScoped
class DeclaratorImplementation @Inject constructor(val enricher: Enricher, val validator: Validator, val selector: Selector, val calculator: Calculator, val receipts: Receipts, val storage: Storage): Declarator {
    override fun processDeclaration(declaration: Declaration) {
        enricher.enrich(declaration)
        validator.validate(declaration)
        calculator.calculate(declaration)
        selector.select(declaration)
        receipts.createReceipts(declaration)
        storage.store(declaration)
    }
}