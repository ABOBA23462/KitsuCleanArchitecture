plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    // Inject
    implementation(Dependencies.Inject.inject)

    // Coroutines
    implementation(Dependencies.Coroutines.kotlinCoroutinesCore)
}