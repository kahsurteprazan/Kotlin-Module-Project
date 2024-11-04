class Menu<T>(
    private val title: String,
    private val items: MutableList<T>,
    private val onItemSelected: (T) -> Unit,
    private val onCreateNewItem: () -> T
) {

    fun display() {
        while (true) {
            println("\n$title")
            println("0. Создать новый")

            items.forEachIndexed { index, item ->
                val itemName = when (item) {
                    is Archive -> item.name
                    is Note -> item.title
                    else -> "Элемент"
                }
                println("${index + 1}. $itemName")
            }
            println("${items.size + 1}. Назад")

            print("Выберите пункт меню: ")
            when (val choice = readLine()?.toIntOrNull()) {
                0 -> createNewItem()
                in 1..items.size -> onItemSelected(items[choice!! - 1])
                items.size + 1 -> return
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

