package main

import (
	"fmt"
	"time"
)

func main() {

	slice := make([]task, 0)
	flag := true
	var input string
	fmt.Println("\n**** GoDo - Your todo manager ****")

	for flag {
		fmt.Println("\n1 -> Adds a new Task")
		fmt.Println("2 -> Shows all pending tasks")
		fmt.Println("3 -> Shows all pending tasks for today")
		fmt.Print("\nPlease choose one of the options above: ")
		fmt.Scanf("%s", &input)

		switch input {
		case "1":
			newTask(&slice)
			fmt.Println(slice)
		case "2":
			showAllPendingTasks(&slice)
		case "3":
			showPendingTasksToday(&slice)
		case "q":
			flag = false
		default:
			fmt.Println(input + " is an invalid input!")
		}
	}
}

type task struct {
	title      string
	pending    bool
	importancy int
	deadline   time.Time
}

func newTask(slice *[]task) {
	var titel string
	var importancy, year, month, day int

	fmt.Println("\n--------- Adding a new task ---------")

	fmt.Print("Titel: ")
	fmt.Scanf("%s", &titel)

	fmt.Print("Importancy (1-5): ")
	fmt.Scanf("%d", &importancy)

	fmt.Print("Year: ")
	fmt.Scanf("%d", &year)

	fmt.Print("Month (1-12): ")
	fmt.Scanf("%d", &month)

	fmt.Print("Day (1-31): ")
	fmt.Scanf("%d", &day)

	t := task{
		title:      titel,
		pending:    true,
		importancy: importancy,
		deadline:   time.Date(year, time.Month(month), day, 12, 0, 0, 0, time.UTC),
	}
	*slice = append(*slice, t)

	fmt.Println("------ Task successfully added ------")
}

func showAllPendingTasks(slice *[]task) {
	cache := make([]task, 0)
	for i := 0; i < len(*slice); i++ {
		if (*slice)[i].pending == true {
			cache = append(cache, (*slice)[i])
		}
	}
	fmt.Println(cache)
}

func showPendingTasksToday(slice *[]task) {
	now := time.Now()
	cache := make([]task, 0)
	for i := 0; i < len(*slice); i++ {
		if ((*slice)[i].pending == true) && (dateEqual((*slice)[i].deadline, now)) {
			cache = append(cache, (*slice)[i])
		}
	}
	fmt.Println(cache)
}

func dateEqual(date1, date2 time.Time) bool {
	y1, m1, d1 := date1.Date()
	y2, m2, d2 := date2.Date()
	return y1 == y2 && m1 == m2 && d1 == d2
}
