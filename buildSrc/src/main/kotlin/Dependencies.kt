import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

val Project.customLibs get() = the<LibrariesForLibs>()