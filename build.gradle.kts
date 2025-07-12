plugins {
    kotlin("jvm") version "2.2.0"
    id("com.gradleup.shadow") version "9.0.0-rc1"
}

group = "me.wolfii"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom:2025.07.11-1.21.7")
    implementation("org.slf4j:slf4j-nop:2.0.17")

    implementation("dev.hollowcube:polar:1.14.6")
}

kotlin {
    jvmToolchain(21)
}


tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "me.wolfii.MainKt"
        }
    }

    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        mergeServiceFiles()
        archiveClassifier.set("")
    }
}