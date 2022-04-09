package work.qvortrup.declarations.infrastructure.db.customeraccess

import work.qvortrup.declarations.domain.entity.Customer
import work.qvortrup.declarations.domain.service.enrich.CustomerAccess
import work.qvortrup.declarations.domain.service.enrich.CustomerNotFound
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomerAccessDB: CustomerAccess {
    override fun getCustomer(customerCode: String): Customer {
        return when (customerCode) {
            "A Cust" -> Customer("A Cust")
            "B Cust" -> Customer("B Cust")
            else -> throw CustomerNotFound(customerCode)
        }
    }

}
