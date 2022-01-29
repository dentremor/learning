package main

import "fmt"

func main() {
	numbers := [8]int{7, 4, 6, 2, 5, 3, 8, 1}

	for i := 0; i < len(numbers); i++ {
		for j := 0; j < len(numbers)-1; j++ {
			println("run: ", j)
			if numbers[j] > numbers[j+1] {
				cache := numbers[j]
				numbers[j] = numbers[j+1]
				numbers[j+1] = cache
			}
		}
	}

	fmt.Println(numbers)
}
