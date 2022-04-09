package work.qvortrup.declarations.domain.service.validate.implementation

import work.qvortrup.declarations.domain.entity.Declaration
import work.qvortrup.declarations.domain.service.validate.ValidationError
import work.qvortrup.declarations.domain.service.validate.Validator
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ValidatorImplementation : Validator {
    override fun validate(declaration: Declaration) {
        declaration.products.forEach {
            if (it.quantity <= 0.0) throw ValidationError("Quantity of $it must not be zero")
        }
    }

}
