package main

import "fmt"

// The Animal interface defines behavior that all animals have.
type Animal interface {
	Speak() string
}

// The Dog type represents a dog.
type Dog struct{}

// Speak implements the Animal interface for the Dog type.
func (d Dog) Speak() string {
	return "Woof!"
}

// The Cat type represents a cat.
type Cat struct{}

// Speak implements the Animal interface for the Cat type.
func (c Cat) Speak() string {
	return "Meow!"
}

func main() {
	// Create a slice of Animal values.
	animals := []Animal{Dog{}, Cat{}}

	// Print the sounds made by each animal.
	for _, animal := range animals {
		fmt.Println(animal.Speak())
	}
}
