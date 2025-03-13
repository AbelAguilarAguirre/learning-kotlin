//This is a task managing software
// define a Task class
class Task(val name: String, var isCompleted: Boolean = false) { // name is the tasks name, isCompleted is true if the task is marked completed but false by default
    //this function makes the isCompleted boolean true
    fun markAsCompleted() {
        isCompleted = true
    }
}

// function to display tasks
fun displayTasks(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks!")
        return
    }
    println("Tasks:")
    for ((index, task) in tasks.withIndex()) {
        val status = if (task.isCompleted) "[X]" else "[ ]"
        println("${index + 1}. $status ${task.name}")
    }
}

// function to add a task
fun addTask(tasks: MutableList<Task>) {
    while (true) {
        println("""
            Add a task:
            Press enter when done.""".trimIndent())
    val taskName = readln()
        if (taskName.isEmpty()){
            return}
    tasks.add(Task(taskName))
    println("Task added.")
    displayTasks(tasks)
}}

// function to mark a task as complete and move it to bottom
fun markTaskAsComplete(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks to complete.")
        return
    }
    displayTasks(tasks)
    println("Enter the number of the task you want to mark as complete:")
    val index = readln().toIntOrNull()?.minus(1)
    if (index != null && index in tasks.indices) {
        val task  = tasks.removeAt(index)
        task.markAsCompleted()
        tasks.add(task)
        println("Task marked as complete.")
        displayTasks(tasks)
    } else {
        println("Invalid task index.")
    }
}

// function to remove a task
fun removeTask(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks to remove.")
        return
    }
    displayTasks(tasks)
    println("Enter the number of the task you want to remove:")
    val index = readln().toIntOrNull()?.minus(1)
    if (index != null && index in tasks.indices) {
        tasks.removeAt(index)
        println("Task removed.")
        displayTasks(tasks)
    } else {
        println("Invalid task index.")
    }
}

// function to remove all tasks
fun removeAllTasks(tasks: MutableList<Task>) {
    while (true) {
        println("Are you sure you want to remove all tasks? (Y/N)")
        when (readln().uppercase()) {
            "Y" -> {
                tasks.clear()
                println("All tasks removed.")
                return
            }

            "N" -> {
                println("Operation cancelled.")
                return
            }

            else -> println("Invalid input. Please enter Y or N")
        }
    }
}

//main function to run the app
fun main() {
    val tasks = mutableListOf<Task>()
    while (true) {
        println(
            """
        1. Add task
        2. Mark task as complete
        3. Remove task
        4. Remove all tasks
        5. Display tasks
        6. Exit
        Enter your choice:""".trimIndent())
        when (readln()) {
            "1" -> addTask(tasks)
            "2" -> markTaskAsComplete(tasks)
            "3" -> removeTask(tasks)
            "4" -> removeAllTasks(tasks)
            "5" -> displayTasks(tasks)
            "6" -> return
            else -> println("Invalid choice.")
        }
    }
}