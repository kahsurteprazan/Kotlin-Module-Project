class NoteMenu(private val archive: Archive) {

    fun display() {
        while (true) {
            println("\nСписок заметок в архиве: ${archive.name}")
            println("0. Создать заметку")
            archive.notes.forEachIndexed { index, note -> println("${index + 1}. Заметка $index") }
            println("${archive.notes.size + 1}. Назад")

            val choice = readLine()?.toIntOrNull()
            if (choice == 0) {
                createNote()
            } else if (choice in 1..archive.notes.size) {
                viewNoteContent(archive.notes[choice!! - 1])
            } else if (choice == archive.notes.size + 1) {
                break
            } else {
                println("Неверный ввод")
            }
        }
    }

    private fun createNote() {
        println("Введите текст заметки:")
        val content = readLine().orEmpty()
        if (content.isNotBlank()) {
            archive.notes.add(content)
            println("Заметка добавлена.")
        } else {
            println("Заметка не может быть пустой.")
        }
    }

    private fun viewNoteContent(note: String) {
        println("Содержание заметки: $note")
        println("Нажмите Enter для возврата.")
        readLine()
    }
}
