package main

import "fmt"

func square(p *int) {
	*p *= *p
}

func main() {
	i := 5
	square(&i)
	fmt.Println(i) //Output: i: 25
}
