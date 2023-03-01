package main

import "fmt"

func square(p int) int {
	return p * p
}

func main() {
	i := square(5)
	fmt.Println(i) //Output: i: 25
}
