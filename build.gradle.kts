plugins {
    java
}

tasks.compileJava {
    options.compilerArgs.add("--module-source-path")
    options.compilerArgs.add(files("src/main/java").asPath)

    println(options.compilerArgs)
}