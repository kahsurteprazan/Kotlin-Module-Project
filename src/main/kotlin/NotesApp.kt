class NotesApp {

    private val archives = mutableListOf<Archive>()

    fun run() {
        val archiveMenu = Menu(
            title = "Список архивов",
            items = archives,
            onItemSelected = { archive -> openNoteMenu(archive) },
            onCreateNewItem = {
                println("Введите имя архива:")
                var name = readLine().orEmpty()
                while (name.isBlank()) {
                    println("Имя архива не может быть пустым. Введите имя архива:")
                    name = readLine().orEmpty()
                }
                Archive(name)
            }
        )

        archiveMenu.display()
    }

    private fun openNoteMenu(archive: Archive) {
        val noteMenu = Menu(
            title = "Список заметок в архиве: ${archive.name}",
            items = archive.notes,
            onItemSelected = { note -> editNoteContent(note) },
            onCreateNewItem = {
                var title: String
                do {
                    println("Введите название заметки:")
                    title = readLine().orEmpty()
                    if (title.isBlank()) println("Название заметки не может быть пустым.")
                } while (title.isBlank())

                var content: String
                do {
                    println("Введите содержание заметки:")
                    content = readLine().orEmpty()
                    if (content.isBlank()) println("Содержание заметки не может быть пустым.")
                } while (content.isBlank())

                Note(title, content)
            }
        )
        noteMenu.display()
    }

    private fun editNoteContent(note: Note) {
        println("Текущее содержание: ${note.content}")
        println("Нажмите Enter для возврата")
        val newContent = readLine().orEmpty()

        if (newContent.isNotBlank()) {
            note.content = newContent
            println("Заметка обновлена.")
        }
    }
}