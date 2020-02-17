package calculator

class Machine(private val memory: Memory, inputs: List<String>) {
    private var commands: List<Ops> = listOf()

    init {
        buildCommands(inputs)
        validateCommandOrder()
    }

    fun getResult(): Int {
        var result = commands.first() as Number

        for (i in 1 until commands.lastIndex step 2) {
            val operation = commands[i] as Operation
            val operand = commands[i+1] as Number

            result = operation.calculate(result, operand)
        }

        return result.number
    }

    private fun buildCommands(inputs: List<String>) {
        commands = inputs
            .map { OpFactory.fromString(it.trim()) } // Strings to Ops
            .map {
                when (it) { // resolve Variables in Ops
                    is UnresolvedVariable -> memory.resolve(it)
                    else -> it
                }
            }
    }

    private fun validateCommandOrder() {
        // commands need to be operands joined by operations
        for ((i, command) in commands.withIndex()) {
            if (i % 2 == 0 && command is Operand) continue
            else if (i % 2 == 1 && command is Operation) continue
            else throw Exception("Order of input is funky")
        }
    }
}
