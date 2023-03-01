package main

import "fmt"

func square(input *int) {
	*input *= *input
}

func main() {
	i := 5
	square(&i)
	fmt.Println(i) //Output: i: 25
}
