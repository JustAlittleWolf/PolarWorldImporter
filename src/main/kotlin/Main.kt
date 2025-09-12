package me.wolfii

import net.hollowcube.polar.AnvilPolar
import net.hollowcube.polar.PolarWriter
import net.minestom.server.MinecraftServer
import java.io.File
import java.nio.file.Path
import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    MinecraftServer.init()

    if (args.isEmpty()) {
        println("usage: java -jar PolarWorldImporter-VERSION.jar <world directory> <radius: Int = 20> <shape: String = \"SQUARE\"")
        exitProcess(1)
    }
    val directory = Path.of(args[0])
    val radius = if (args.size > 1) args[1].toInt() else 20
    val shape = if (args.size > 2) args[2] else "SQUARE"
    val polarWorld = AnvilPolar.anvilToPolar(
        directory, when (shape) {
            "DIAMOND" -> { x, z -> abs(x) + abs(z) < radius }
            "SQUARE" -> { x, z -> abs(x) < radius && abs(z) < radius }
            "CIRCLE" -> { x, z -> sqrt((x * x + z * z).toDouble()) < radius }
            else -> throw IllegalStateException("Unknown shape: $shape")
        })
    val worldName = directory.fileName.toString()
    File("./$worldName.polar").writeBytes(PolarWriter.write(polarWorld))

    MinecraftServer.stopCleanly()
}