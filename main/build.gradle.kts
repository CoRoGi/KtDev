plugins {
    application
    kotlin("jvm")
}

sourceSets { getByName("main").java.srcDirs("src") }

application {
    mainClass.set("main.MainKt")
}
