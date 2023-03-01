package main

import (
	"fmt"
)

func main() {
	i := 4
	// while
	for i > 0 {
		i--
	}

	elements := []int{0, 3, 4, 5, 6, 67, 7}
	// for each
	for i, element := range elements {
		fmt.Printf("i: %d = %d \n", i, element)
	}

	h := 7
	// for
	for i := 0; i < h; i++ {
		fmt.Printf("i: %d < %d \n", i, h)
	}
}
