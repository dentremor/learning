package main

import (
	"fmt"
	"sync"
)

func printHelloWorld(wg *sync.WaitGroup) {
	fmt.Println("Hello, world!")
	wg.Done()
}

func printGoodbye(wg *sync.WaitGroup) {
	fmt.Println("Goodbye!")
	wg.Done()
}

func main() {
	var wg sync.WaitGroup

	// add two goroutines that wait for the waitgroup
	wg.Add(2)

	go printHelloWorld(&wg)
	go printGoodbye(&wg)

	// wait until both goroutines are finished
	wg.Wait()
}
