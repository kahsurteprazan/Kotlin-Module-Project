fun main() {
    val archives = mutableListOf<Archive>()

    val archiveMenu = Menu(
        title = "Список архивов",
        items = archives,
        onItemSelected = { archive -> openNoteMenu(archive) },
        onCreateNewItem = {
            println("Введите имя архива:")
            val name = readLine().orEmpty()
            Archive(name)
        }
    )

    archiveMenu.display()
}