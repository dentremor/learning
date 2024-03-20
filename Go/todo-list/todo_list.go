package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
	"time"
)

// Variables and structs

var banner = `
  _____     ___     
 / ___/__  / _ \___ 
/ (_ / _ \/ // / _ \
\___/\___/____/\___/				 
`

var toDoList = make([]ToDo, 0)
var reader = bufio.NewReader(os.Stdin)

const dateLayout = "02.01.2006"

type ToDo struct {
	name     string
	deadline time.Time
	pending  bool
	priority int8
}

// Helper

func readInput() string {
	text, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Fehler beim Lesen:", err)
	}
	return text[:len(text)-1]
}

func clearScreen() {
	fmt.Print("\033[2J")
}

func parseDate(dateString string) time.Time {
	date, err := time.Parse(dateLayout, dateString)
	if err != nil {
		fmt.Println("Fehler beim Parsen des Datums:", err)
	}
	return date
}

func parseInt(s string) int8 {
	i, err := strconv.ParseInt(s, 10, 8)
	if err != nil {
		panic(err)
	}
	return int8(i)
}

func truncateString(str string, maxLength int) string {
	if len(str) > maxLength {
		if maxLength > 3 {
			return str[:maxLength-3] + "..."
		}
		return str[:maxLength]
	}
	return str
}

// Functions

func addTodo(todoList *[]ToDo) {
	todo := ToDo{}
	fmt.Println("---- New ToDo ----")
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
	fmt.Println(headerLine)
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

func main() {
	fmt.Println(banner)

	for {
		fmt.Println("\nChoose an option: (1) Add ToDo, (2) Print ToDos, (3) Beenden")
		option := readInput()

		switch option {
		case "1":
			addTodo(&toDoList)
		case "2":
			printTodos(&toDoList)
		case "3":
			fmt.Println("Bye!")
			return
		default:
			fmt.Println("Unknown option:", option)
		}
	}
}
