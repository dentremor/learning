package main

import "fmt"

type Employee interface {
	PrintInfo()
}

type Person struct {
	Name string
	Age  int
}

func (p Person) PrintInfo() {
	fmt.Printf("Name: %s, Age: %d\n", p.Name, p.Age)
}

func main() {
	p := Person{Name: "John Doe", Age: 35}
	var e Employee = p
	e.PrintInfo() // Output: Name: John Doe, Age: 35
}
