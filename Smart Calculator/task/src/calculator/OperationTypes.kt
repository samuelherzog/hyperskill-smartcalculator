package calculator

class PlusOperation : Operation() {
    override fun calculate(lhs: Number, rhs: Number): Number {
        return Number(lhs.number + rhs.number)
    }
}
class MinusOperation : Operation() {
    override fun calculate(lhs: Number, rhs: Number): Number {
        return Number(lhs.number - rhs.number)
    }
}