package calculator

import java.util.*

interface Ops

interface Operand : Ops

fun main() {
    val input = Scanner(System.`in`)
    val memory = Memory()

    while (true) {
        val line = input.nextLine().trim()

        if (line.isBlank()) {
            continue
        }

        if (line == "/exit") {
            println("Bye!")
            return
        }

        if (line == "/help") {
            println("The program calculates the sum or difference of numbers, use as many + or - as you like")
            continue
        }

        if (line.first() == '/') {
            println("Unknown command")
            continue
        }

        if (line.contains('=')) {
            try {
                println(memory.parse(line))
                continue
            } catch (e: Exception) {
                println(e.message)
                continue
            }
        }

        val inputLines = line.split(' ')
        val validLines = inputLines.filter { it.isNotBlank() }

        try {
            val machine = Machine(memory, validLines)
            println(machine.getResult())

        } catch (e: Exception) {
            println("Invalid expression")
            continue
        }
    }
}

