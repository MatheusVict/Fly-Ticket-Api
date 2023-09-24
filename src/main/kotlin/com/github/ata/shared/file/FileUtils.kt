package com.github.ata.shared.file

object FileUtils {
    fun loadResource(path: String) =
        this.javaClass.getResource(path)?.readText()
            ?: throw IllegalArgumentException("Resource not found: $path")
}