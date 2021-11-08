package main

import (
	"fmt"
)

func main() {
	fmt.Println(primes(100))
}

func primes(n int) (result []int) {
	var empty_list = []int{}
	return primes_helper(n, 2, empty_list) //implicit call possible?
}

func primes_helper(n int, i int, list []int) (result []int) {
	if n > i {
		if is_prime(i, 2) == true {
			list = append(list, i)
		}
		return primes_helper(n, i+1, list)
	} else {
		result = list
		return
	}
}

func is_prime(n int, j int) (result bool) {
	if is_prime_helper(n, j) == true {
		result = true
		return
	} else {
		result = false
		return
	}

}

func is_prime_helper(n int, j int) (result bool) {
	if n == j {
		result = true
		return
	} else if n%j == 0 {
		result = false
		return
	} else {
		return is_prime_helper(n, j+1)
	}
}
