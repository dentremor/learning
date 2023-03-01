package main

import "fmt"

type Duck interface {
	Quack()
}

type Pigeon struct {
}

func (p Pigeon) Quack() {
	fmt.Println("Quack quack")
}

func Fly(d Duck) {
	fmt.Println("Flap flap")
}

func main() {
	var duck Duck = Pigeon{}
	duck.Quack() // Output: "Quack quack"
	duck.Fly()
}
