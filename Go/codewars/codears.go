package main

import (
	"fmt"
	"strconv"
	"strings"
)

func ToCsvText(array [][]int) string {
	array2 := []string{}
	for _, a := range array {
		k := []string{}
		for _, n := range a {
			k = append(k, fmt.Sprint(n))
		}
		array2 = append(array2, strings.Join(k, ","))
	}
	return strings.Join(array2, "\n")
}

func Invert(arr []int) []int {
	invArr := make([]int, len(arr))

	for i, num := range arr {
		invArr[i] = -num
	}
	return invArr
}

func StringToNumber(str string) int {
	n, _ := strconv.Atoi(str)
	return n
}

func main() {
	fmt.Println(StringToNumber("1234"))
}
