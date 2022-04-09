package work.qvortrup.declarations.infrastructure.db.tariffaccess

import work.qvortrup.declarations.domain.entity.Tariff
import work.qvortrup.declarations.domain.service.enrich.TariffAccess
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TariffAccessDB : TariffAccess {
    override fun getTariffs(productCodes: List<String>): Map<String, Tariff> {
        return productCodes.associateWith { Tariff(it, 0.05, 0.05) }
    }
}
