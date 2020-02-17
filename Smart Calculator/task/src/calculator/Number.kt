package calculator

class Number(val number: Int): Operand {
    override fun toString(): String {
        return number.toString()
    }

    companion object {
        fun fromString(input: String): Operand {
            return Number(input.toInt())
        }

        fun isValid(input: String): Boolean {
            return input.matches("[+-]?[0-9]+".toRegex())
        }
    }
}
