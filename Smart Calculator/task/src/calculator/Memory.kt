package calculator

class Memory {
    private val variables = mutableMapOf<String, Operand>()

    fun resolve(variable: UnresolvedVariable): Operand {
        return variables[variable.name] ?: throw Exception("Unknown variable")
    }

    fun parse(input: String): Operand {
        val identifier = input.substringBefore('=').trim()
        val value = input.substringAfter('=').trim()

        checkForLegalName(identifier)

        when {
            isValidNumber(value) -> variables[identifier] = Number.fromString(value)
            isKnownVariable(value) -> variables[identifier] = variables[value]!!
            isUnknownVariable(value) -> throw Exception("Unknown variable")
            else -> throw Exception("Invalid assignment")
        }

        return variables[identifier]!!
    }

    private fun checkForLegalName(identifier: String) {
        if (!Variable.isValidIdentifier(identifier)) {
            throw Exception("Invalid identifier")
        }
    }

    private fun isValidNumber(value: String): Boolean {
        return Number.isValid(value)
    }

    private fun isKnownVariable(name: String): Boolean {
        return (Variable.isValidIdentifier(name) && variables.containsKey(name))
    }

    private fun isUnknownVariable(name: String): Boolean {
        return (Variable.isValidIdentifier(name) && !variables.containsKey(name))
    }
}