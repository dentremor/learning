package internal

func addTodo(todoList *[]ToDo) {
	todo := ToDo{}
	fmt.Println("\n---- New ToDo ----")
	fmt.Printf("Name: ")
	todo.name = readInput()
	fmt.Printf("Deadline (DD.MM.YYYY): ")
	todo.deadline = parseDate(readInput())
	fmt.Printf("Priority (number): ")
	todo.priority = parseInt(readInput())
	todo.pending = true

	*todoList = append(*todoList, todo)
}

func printTodos(todoList *[]ToDo) {
	indexWidth := 6
	nameWidth := 25
	deadlineWidth := 15
	pendingWidth := 10
	priorityWidth := 9

	// Print header
	var headers = []string{"Index", "Name", "Deadline", "Pending", "Priority"}
	headerLine := fmt.Sprintf("|%*s|%*s|%*s|%*s|%*s|",
		-indexWidth, headers[0],
		-nameWidth, headers[1],
		-deadlineWidth, headers[2],
		-pendingWidth, headers[3],
		-priorityWidth, headers[4],
	)
	fmt.Println("\n" + headerLine)
	fmt.Println(strings.Repeat("-", len(headerLine)))

	// Print body
	for i, todo := range *todoList {
		fmt.Printf("|%*d|%*s|%*s|%*t|%*d|\n",
			-indexWidth, i,
			-nameWidth, truncateString(todo.name, nameWidth),
			-deadlineWidth, todo.deadline.Format(dateLayout),
			-pendingWidth, todo.pending,
			-priorityWidth, todo.priority,
		)
	}
}

func deleteTodo(todoList *[]ToDo) {
	printTodos(todoList)
	fmt.Printf("\nDelete Todo with index: ")
	delIndex, err := parseInt(readInput())
	if (err != nil) || (delIndex < 0) || ()
	*todoList = append((*todoList)[:delIndex], (*todoList)[delIndex+1:]...)

}