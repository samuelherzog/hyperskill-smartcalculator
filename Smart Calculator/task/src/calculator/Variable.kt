package calculator

class Variable: Operand {
    companion object {
        fun isValidIdentifier(name: String): Boolean {
            return name.matches("[a-zA-Z]+".toRegex())
        }
    }
}

class UnresolvedVariable(val name: String): Operand
