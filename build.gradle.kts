plugins {
    id("java")
    id("application")
}

group = "ru.vachok.networkaclparser"
version = "0.1"

repositories {
    mavenCentral()
}
dependencies {
    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json:json:20250517")
    // https://mvnrepository.com/artifact/org.mockito/mockito-core
    testImplementation("org.mockito:mockito-core:5.18.0")
    // https://mvnrepository.com/artifact/org.testng/testng
    testImplementation("org.testng:testng:7.11.0")
}
gradle.startParameter.isParallelProjectExecutionEnabled = true
sourceSets {
    main {
        java.srcDir("src/main/java")

    }
    test {
        java.srcDir("src/test/java")
    }
}
application {
    // Define the main class for the application.
    mainClass = "ru.vachok.networkaclparser.StartMe"
}
tasks.jar {
    archiveFileName.set("app.jar")
}
tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "ru.vachok.networkaclparser.StartMe"
        exclude("META-INF/")
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
tasks.javadoc {
    options.encoding = "UTF-8"
    (options as StandardJavadocDocletOptions).charSet = "UTF-8"
    (options as StandardJavadocDocletOptions).docEncoding = "UTF-8"
    (options as StandardJavadocDocletOptions).addStringOption("private", "-private")
}

tasks.test {
    useTestNG()
}