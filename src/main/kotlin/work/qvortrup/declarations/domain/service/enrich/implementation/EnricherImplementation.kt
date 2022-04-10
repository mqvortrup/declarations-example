package work.qvortrup.declarations.domain.service.enrich.implementation

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.service.enrich.CustomerAccess
import work.qvortrup.declarations.domain.service.enrich.Enricher
import work.qvortrup.declarations.domain.service.enrich.TariffAccess

class EnricherImplementation(private val customerAccess: CustomerAccess, private val tariffAccess: TariffAccess) :
    Enricher {
    private val tariffAssigner = TariffAssigner(tariffAccess)
    override fun enrich(declaration: Declaration) {
        val customer = customerAccess.getCustomer(declaration.customerCode)
        declaration.customer = customer
        tariffAssigner.assign(declaration)
    }
}

class TariffAssigner(private val tariffAccess: TariffAccess) {
    fun assign(declaration: Declaration) {
        val productCodes = declaration.products.map { it.productCode }
        val tariffs = tariffAccess.getTariffs(productCodes)
        declaration.products.forEach { it.tariff = tariffs[it.productCode]!! }
    }
}