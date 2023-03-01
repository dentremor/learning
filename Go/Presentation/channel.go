package main

import (
	"fmt"
	"time"
)

func printHelloWorld(ch chan string) {
	ch <- "Hello, world!"
}

func printGoodbye(ch chan string) {
	time.Sleep(5 * time.Second)
	ch <- "Goodbye!"
}

func main() {
	ch := make(chan string)
	go printHelloWorld(ch)
	go printGoodbye(ch)

	// Empfangen Sie die Werte vom channel und drucken Sie sie aus.
	for {
		select {
		case a := <-ch:
			fmt.Println(a)
		}
	}
}
