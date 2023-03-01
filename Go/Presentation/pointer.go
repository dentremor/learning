package main

import (
	"fmt"
)

func main() {
	i := 5
	fmt.Println("i:", i) //Output: i: 5

	p := &i
	fmt.Println("p:", p)   //Output: p: 0xc0000b8000
	fmt.Println("*p:", *p) //Output: *p: 5

	*p = 10
	fmt.Println("i:", i) //Output: i: 10
}
