plugins {
    kotlin("jvm") version "2.0.20"
    id("com.gradleup.shadow") version "8.3.1"
}

group = "me.wolfii"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom-snapshots:9fbff439e7")
    implementation("org.slf4j:slf4j-nop:2.0.16")

    implementation("dev.hollowcube:polar:1.11.2")
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