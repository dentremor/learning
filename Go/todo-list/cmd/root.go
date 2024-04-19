package main

import (
	"fmt"

	todo "github.com/dentremor/learning/Go/todo-list/internal"
)

var banner = `
  _____     ___     
 / ___/__  / _ \___ 
/ (_ / _ \/ // / _ \
\___/\___/____/\___/				 
`

var todoList = make([]todo.ToDo, 0)

func printWelcomeScreen() {
	todo.ClearScreen()
	fmt.Println(banner)
	fmt.Println("  add\t\tTo add a new todo.")
	fmt.Println("  list\t\tTo list all todos.")
	fmt.Println("  del\t\tTo delete a todo.")
	fmt.Println("  comp\t\tTo mark a todo as completed.")
	fmt.Println("  expo\t\tTo export the todos.")
	fmt.Println("  impo\t\tTo import the todos.")
	fmt.Println("  exit\t\tTo escape.\n")
}

func main() {
	for {
		printWelcomeScreen()
		option := todo.ReadInput()

		switch option {
		case "add":
			todo.AddTodo(&todoList)
		case "list":
			todo.PrintTodos(&todoList)
		case "del":
			todo.DeleteTodo(&todoList)
		case "exit":
			fmt.Println("\nBye!")
			return
		default:
			fmt.Println("Unknown option:", option)
		}
	}
}
