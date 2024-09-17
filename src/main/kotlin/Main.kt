package me.wolfii

import net.hollowcube.polar.AnvilPolar
import net.hollowcube.polar.PolarWriter
import net.minestom.server.MinecraftServer
import java.io.File
import java.nio.file.Path
import kotlin.math.abs
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    MinecraftServer.init()

    if (args.isEmpty()) {
        println("usage: java -jar PolarWorldImporter-VERSION.jar <world directory> <radius: Int = 20>")
        exitProcess(1)
    }
    val directory = Path.of(args[0])
    val radius = if (args.size > 1) args[1].toInt() else 20
    val polarWorld = AnvilPolar.anvilToPolar(directory) { x, z -> abs(x) + abs(z) < radius }
    val worldName = directory.fileName.toString()
    File("./$worldName.polar").writeBytes(PolarWriter.write(polarWorld))

    MinecraftServer.stopCleanly()
}