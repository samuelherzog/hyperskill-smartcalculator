package calculator

class OpFactory {
    companion object {
        fun fromString(input: String): Ops {
            return when {
                Number.isValid(input) -> Number.fromString(input)
                Operation.isValid(input) -> Operation.fromString(input)
                Variable.isValidIdentifier(input) -> UnresolvedVariable(input)
                else -> throw Exception("Can't resolve Op $input")
            }
        }
    }
}
