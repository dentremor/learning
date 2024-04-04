package main

import (
	"bufio"
	"fmt"
	"os"
	"time"
)

var banner = `
  _____     ___     
 / ___/__  / _ \___ 
/ (_ / _ \/ // / _ \
\___/\___/____/\___/				 
`

var todoList = make([]ToDo, 0)
var reader = bufio.NewReader(os.Stdin)

const dateLayout = "02.01.2006"

type ToDo struct {
	name     string
	deadline time.Time
	pending  bool
	priority int8
}

func main() {
	fmt.Println(banner)

	for {
		fmt.Println("\nChoose an option: (1) Add ToDo, (2) Print ToDos, (q) Exit")
		option := display.readInput()

		switch option {
		case "1":
			internal.addTodo(&toDoList)
		case "2":
			printTodos(&toDoList)
		case "3":
			deleteTodo(&toDoList)
		case "q":
			fmt.Println("\nBye!")
			return
		default:
			fmt.Println("Unknown option:", option)
		}
	}
}
