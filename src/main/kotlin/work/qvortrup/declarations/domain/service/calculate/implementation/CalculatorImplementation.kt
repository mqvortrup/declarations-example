package work.qvortrup.declarations.domain.service.calculate.implementation

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.entity.Dues
import work.qvortrup.declarations.domain.entity.Product
import work.qvortrup.declarations.domain.service.calculate.Calculator
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CalculatorImplementation : Calculator {
    override fun calculate(declaration: Declaration) {
        declaration.products.forEach { it.dues = calculateDues(it) }
        declaration.totalDues = declaration.products.fold(Dues(0.0, 0.0)) {
            total, next -> Dues(total.importTax + next.dues!!.importTax, total.valueAddedTax + next.dues!!.valueAddedTax)
        }
    }

    private fun calculateDues(product: Product): Dues {
        return Dues(
            product.quantity * product.tariff!!.customsRate,
            product.quantity * product.tariff!!.vatRate
        )
    }
}
