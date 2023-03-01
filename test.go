package main

import (
	"fmt"
	"sort"
	"strconv"
	"strings"
)

func main() {
	fmt.Println(solution("Ana", "a"))
}

func ReverseSeq(n int) []int {
	arr := make([]int, n)
	for count := 0; count <= n; count++ {
		arr[n] = n - count
	}
	return arr
}

func Solution(word string) string {
	var ret = ""
	for i := 0; i < len(word); i++ {
		ret = ret + string(word[len(word)-i-1])
	}
	return ret
}

func SmallestIntegerFinder(numbers []int) int {
	sort.Ints(numbers)
	return numbers[0]
}

func HighAndLow(in string) string {
	strings.Fields(in)
	sl := make([]int, len(in))
	for i, val := range in {
		sl[i], _ = strconv.Atoi(string(val))
	}
	sort.Ints(sl)
	return "s"
}

func GetMiddle(s string) string {
	if len(s)%2 == 0 {
		return s[len(s)/2-1 : len(s)/2+1]
	} else {
		return s[len(s)/2 : len(s)/2+1]
	}
}

func solution(str, ending string) bool {
	if len(str) > 0 && str[len(str)-1:] == ending {
		return true
	} else {
		return false
	}
}
