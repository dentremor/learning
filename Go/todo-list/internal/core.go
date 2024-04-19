package todo

import (
	"fmt"
	"strings"
	"time"
)

const dateLayout = "02.01.2006"

type ToDo struct {
	name     string
	deadline time.Time
	pending  bool
	priority int
}

func AddTodo(todoList *[]ToDo) {
	todo := ToDo{}
	fmt.Println("\n---- New ToDo ----")
	fmt.Printf("Name: ")
	todo.name = ReadInput()
	fmt.Printf("Deadline (DD.MM.YYYY): ")
	todo.deadline = parseDate(ReadInput())
	fmt.Printf("Priority (number): ")
	todo.priority, _ = parseInt(ReadInput())
	todo.pending = true

	*todoList = append(*todoList, todo)
}

func PrintTodos(todoList *[]ToDo) {
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

	// Print columns
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

func DeleteTodo(todoList *[]ToDo) {
	if len(*todoList) != 0 {
		PrintTodos(todoList)
		fmt.Printf("\nDelete Todo with index: ")
		delIndex := readIndex(len(*todoList))
		*todoList = append((*todoList)[:delIndex], (*todoList)[delIndex+1:]...)
	} else {
		fmt.Println("There are no todos!")
	}

}
