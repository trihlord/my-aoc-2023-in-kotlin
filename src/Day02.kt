fun main() {
    fun part1(input: List<String>): Int {
        val maxCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
        val emptyCubes = mapOf("red" to 0, "green" to 0, "blue" to 0)
        return input.fold(0) { acc, s ->
            val matchResult = Regex("^Game (\\d+): (.+)$").find(s) ?: return acc
            val (gameId, cubesSubsets) = matchResult.groupValues.let { Pair(it[1].toInt(), it[2]) }
            val gameCubes = cubesSubsets.split("; ", ", ")
                .map { it.split(" ").let { (first, second) -> Pair(second, first.toInt()) } }
                .fold(emptyCubes.toMutableMap()) { cubes, (color, count) ->
                    cubes[color] = (cubes[color] ?: 0) + count
                    cubes
                }
            val possible = maxCubes.all { (color, count) -> (gameCubes[color] ?: 0) <= count }
            if (possible) acc + gameId else acc
        }
    }

    val testInput = readInput("Day02_1_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
}
