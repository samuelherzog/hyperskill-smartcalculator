package calculator


abstract class Operation: Ops {
    abstract fun calculate(lhs: Number, rhs: Number): Number

    companion object {
        fun fromString(input: String): Operation {
            return if (input.length == 1) {
                factory(input[0])
            } else {
                factory(collapse(input))
            }
        }

        fun isValid(input: String): Boolean {
            return input.matches("[+-]+".toRegex())
        }

        private fun collapse(input: String): Char {
            val countNegatives =
                    input.filter { it != '+' }
                            .count()

            return if (countNegatives % 2 == 1) {
                '-'
            } else {
                '+'
            }
        }

        private fun factory(symbol: Char): Operation {
            return when (symbol) {
                '+' -> PlusOperation()
                '-' -> MinusOperation()
                else -> throw Exception("Invalid operation")
            }
        }
    }
}