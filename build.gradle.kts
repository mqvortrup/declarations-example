import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
    // CDI with weld requires all code in a jar, shadow jar creates an executable fat jar
    id("com.github.johnrengelman.shadow") version "7.0.0"
    // CDI with weld requires classes to be proxiable, i.e. they cannot be final
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
}

group = "work.qvortrup"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Java CDI reference implmentation
    implementation("org.jboss.weld.se:weld-se-shaded:3.1.5.SP1")
    // speed up start of CDI container
    implementation("org.jboss:jandex:2.4.2.Final")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("work.qvortrup.declarations.application.MainKt")
}

tasks.jar {
    // make slim jar executable by including manifest with Main-Class attribute
    manifest {
        attributes(mapOf("Main-Class" to "work.qvortrup.declarations.application.MainKt"))
    }
}

tasks {
    shadowJar {
        // make fat jar executable by including manifest with Main-Class attribute
        // shadowJar automatically includes all jars needed at runtime into the fat jar
        manifest {
            attributes(Pair("Main-Class", "work.qvortrup.declarations.application.MainKt"))
        }
    }
}

allOpen {
    // all classes annotated with @ApplicationScoped (i.e. with instances that should be injected) are left open
    annotation("javax.enterprise.context.ApplicationScoped")
}