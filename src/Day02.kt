fun main() {
    fun part1(input: List<String>): Int {
        val maxCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
        return input.fold(0) { acc, s ->
            val matchResult = Regex("^Game (\\d+): (.+)$").find(s) ?: return acc
            val gameId = matchResult.groupValues[1].toInt()
            val possible = matchResult.groupValues[2].split("; ").all {
                it.split(", ").associate { s ->
                    val strings = s.split(" ")
                    strings[1] to strings[0].toInt()
                }.all { entry -> (maxCubes[entry.key] ?: 0) >= entry.value }
            }
            if (possible) acc + gameId else acc
        }
    }

    val testInputPart1 = readInput("Day02_part1_test")
    check(part1(testInputPart1) == 8)

    val input = readInput("Day02")
    part1(input).println()
}
