plugins {
    java
}

tasks.compileJava {
    options.compilerArgs.add("--module-source-path")
    options.compilerArgs.add(files("src/main/java").asPath)
}

file("src/main/java").listFiles() { pathname -> pathname.isDirectory }.forEach {
    val tabName = it.name.split("/")
    val name = tabName[tabName.size-1]

//    tasks.register<Jar>("${name}Jar") {
//        // Create a jar archive BUT the inner structure is not correct !
//        archiveBaseName.set("${name}_notworking")
//        dependsOn("classes")
//        val sourcesMain = sourceSets.main.get()
//
//        from(sourcesMain.output)
//        {
//            include("${name}/**")
//        }
//    }

    tasks.register("${name}Jar") {
        dependsOn("classes")
        val sourcesMain = sourceSets.main.get()

        // TODO : Find another way to build the jar (without using the "jar command")
        doFirst{
            mkdir("${project.buildDir.absolutePath}/libs")
        }
        doLast{
            project.exec{
                workingDir(".")
                executable("jar")
                args("--create")
                args("--file","${project.buildDir.absolutePath}/libs/${name}.jar")
                args("-C","${sourcesMain.output.classesDirs.elementAt(0).absolutePath}/${name}")
                args(".")
            }
        }
    }
}

tasks.jar.configure() {
    actions.clear()
    file("src/main/java").listFiles() { pathname -> pathname.isDirectory }.forEach {
        val tabName = it.name.split("/")
        val name = tabName[tabName.size-1]
        dependsOn ("${name}Jar")
    }

    doFirst {
        println("Creation of a jar per module...")
    }
}

/*tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "application.application.Main"
    }
}*/