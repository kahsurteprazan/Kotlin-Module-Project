class Menu<T>(private val title: String, private val items: MutableList<T>, private val onItemSelected: (T) -> Unit, private val onCreateNewItem: () -> T) {

    fun display() {
        while (true) {
            println("\n$title")
            println("0. Создать новый")

            items.forEachIndexed { index, item ->
                val itemName = if (item is Archive) item.name else (item as? Note)?.title ?: "Элемент"
                println("${index + 1}. $itemName")
            }
            println("${items.size + 1}. Назад")

            print("Выберите пункт меню: ")
            when (val choice = readLine()?.toIntOrNull()) {
                0 -> createNewItem()
                in 1..items.size -> onItemSelected(items[choice!! - 1])
                items.size + 1 -> return // Вернуться к предыдущему меню
                else -> println("Неверный ввод. Пожалуйста, введите правильный номер.")
            }
        }
    }

    private fun createNewItem() {
        val newItem = onCreateNewItem()
        items.add(newItem)
        println("Добавлен: $newItem")
    }
}

fun openNoteMenu(archive: Archive) {
    val noteMenu = Menu(
        title = "Список заметок в архиве: ${archive.name}",
        items = archive.notes,
        onItemSelected = { note -> viewNoteContent(note) },
        onCreateNewItem = {
            println("Введите название заметки:")
            val title = readLine().orEmpty()
            println("Введите содержание заметки:")
            val content = readLine().orEmpty()
            Note(title, content)
        }
    )
    noteMenu.display()
}

fun viewNoteContent(note: Note) {
    println("Содержание заметки '${note.title}':\n${note.content}")
    println("Нажмите Enter для возврата.")
    readLine()
}