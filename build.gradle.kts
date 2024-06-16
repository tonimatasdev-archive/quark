plugins {
    id("java")
}

group = "dev.discordmk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    
}

tasks.test {
    useJUnitPlatform()
}