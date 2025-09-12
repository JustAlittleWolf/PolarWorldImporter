plugins {
    kotlin("jvm") version "2.2.20"
    id("com.gradleup.shadow") version "9.1.0"
}

group = "me.wolfii"
version = "1.2.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom:2025.08.29-1.21.8")
    implementation("org.slf4j:slf4j-nop:2.0.17")

    implementation("dev.hollowcube:polar:1.14.7")
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