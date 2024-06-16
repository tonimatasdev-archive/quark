plugins {
    java
    `maven-publish`
}

val projectVersion: String by extra
val jdaVersion: String by extra
val gsonVersion: String by extra

group = "dev.discordmk"
version = projectVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            artifact(tasks.jar)
        }
    }
}
