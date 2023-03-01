package main

import "fmt"

func printHelloWorld() {
	fmt.Println("Hello, world!")
}

func printGoodbye() {
	fmt.Println("Goodbye!")
}

func main() {
	go printHelloWorld()
	go printGoodbye()
}
