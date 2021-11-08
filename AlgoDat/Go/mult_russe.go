package main

import "fmt"

func main() {
	fmt.Println(mult_russe(45, 19))
}

func mult_russe(a int, b int) (result int) {
	return mult_russe_helper(a, b, 0)
}

func mult_russe_helper(a int, b int, sum int) (result int) {
	if a == 1 {
		result = sum + b
		return result
	} else if a%2 == 1 {
		return mult_russe_helper(a/2, b*2, sum+b)
	} else {
		return mult_russe_helper(a/2, b*2, sum)
	}
}
