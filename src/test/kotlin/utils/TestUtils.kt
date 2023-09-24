package utils

object TestUtils {
    fun getFileContent(path: String) =
        this.javaClass.getResource(path)?.readText()
            ?: throw IllegalArgumentException("File not found: $path")
}