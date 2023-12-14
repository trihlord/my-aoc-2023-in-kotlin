fun main() {
    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, s ->
            val ints = s.toCharArray().map { it.digitToIntOrNull() }.filterNotNull()
            acc + 10 * ints.first() + ints.last()
        }
    }

    fun part2(input: List<String>): Int {
        val numbersMap = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
        val regex = numbersMap.keys.joinToString(prefix = "(?=(", postfix = "))", separator = "|").toPattern().toRegex()
        val input1 = input.map { s -> regex.replace(s) { numbersMap[it.groupValues[1]].toString() } }
        return part1(input1)
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day01_1_test")
    check(part1(testInput) == 142)

    testInput = readInput("Day01_2_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
