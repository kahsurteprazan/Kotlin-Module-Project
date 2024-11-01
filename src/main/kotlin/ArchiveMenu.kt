class ArchiveMenu {
    private val archives = mutableListOf<Archive>()

    fun display() {
        while (true) {
            println("\nСписок архивов:")
            println("0. Создать архив")
            archives.forEachIndexed { index, archive -> println("${index + 1}. ${archive.name}") }
            println("${archives.size + 1}. Выход")

            val choice = readLine()?.toIntOrNull()
            if (choice == 0) {
                createArchive()
            } else if (choice in 1..archives.size) {
                openArchive(archives[choice!! - 1])
            } else if (choice == archives.size + 1) {
                println("Выход из программы")
                break
            } else {
                println("Неверный ввод")
            }
        }
    }

    private fun createArchive() {
        println("Введите имя архива:")
        val name = readLine().orEmpty()
        if (name.isNotBlank()) {
            archives.add(Archive(name))
            println("Архив '$name' создан.")
        } else {
            println("Имя архива не может быть пустым.")
        }
    }

    private fun openArchive(archive: Archive) {
        val noteMenu = NoteMenu(archive)
        noteMenu.display()
    }
}
