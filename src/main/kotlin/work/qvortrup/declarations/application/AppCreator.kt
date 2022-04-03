package work.qvortrup.declarations.application

import work.qvortrup.declarations.controller.async.implementation.AsyncDeclarationImplementation
import work.qvortrup.declarations.infrastructure.db.customeraccess.CustomerAccessDB
import work.qvortrup.declarations.infrastructure.db.declarationaccess.DeclarationAccessDB
import work.qvortrup.declarations.infrastructure.db.receiptaccess.ReceiptAccessDB
import work.qvortrup.declarations.infrastructure.db.tariffaccess.TariffAccessDB
import work.qvortrup.declarations.domain.service.calculate.implementation.CalculatorImplementation
import work.qvortrup.declarations.domain.service.enrich.implementation.EnricherImplementation
import work.qvortrup.declarations.domain.service.receipts.implementation.ReceiptsImplementation
import work.qvortrup.declarations.domain.service.select.implementation.SelectorImplementation
import work.qvortrup.declarations.domain.service.storage.implementation.StorageImplementation
import work.qvortrup.declarations.domain.service.validate.implementation.ValidatorImplementation
import work.qvortrup.declarations.infrastructure.messaging.receiver.DeclarationReceiver
import work.qvortrup.declarations.infrastructure.messaging.replier.DeclarationReplierMessaging
import work.qvortrup.declarations.usecase.declare.implementation.DeclaratorImplementation

class AppCreator {
    private val customerAccess = CustomerAccessDB()
    private val tariffAccess = TariffAccessDB()
    private val enricher = EnricherImplementation(customerAccess, tariffAccess)

    private val validator = ValidatorImplementation()

    private val selector = SelectorImplementation()

    private val calculator = CalculatorImplementation()

    private val receipts = ReceiptsImplementation()

    private val declarationAccess = DeclarationAccessDB()
    private val receiptAccess = ReceiptAccessDB()
    private val storage = StorageImplementation(declarationAccess, receiptAccess)

    private val declarator = DeclaratorImplementation(enricher, validator, selector, calculator, receipts, storage)
    private val replier = DeclarationReplierMessaging()
    private val asyncDeclaration = AsyncDeclarationImplementation(declarator, replier)
    val receiver = DeclarationReceiver(asyncDeclaration)
}