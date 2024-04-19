package todo

import (
	"bufio"
	"errors"
	"fmt"
	"os"
	"strconv"
	"time"
)

var reader = bufio.NewReader(os.Stdin)

func ReadInput() string {
	text, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error while reading:", err)
	}
	return text[:len(text)-1]
}

func readIndex(sliceLength int) int {
	for {
		input := ReadInput()
		delIndex, parseErr := parseInt(input)
		if parseErr != nil {
			fmt.Printf("\n%s is not a number! Try again: ", input)
		} else {
			intervalErr := numInInterval(delIndex, 0, sliceLength)
			if intervalErr != nil {
				fmt.Printf("\nIndex %s is out of bounds! Try again: ", input)
			} else {
				return delIndex
			}
		}
	}
}

func ClearScreen() {
	fmt.Print("\033[2J\033[H")
}

func parseDate(dateString string) time.Time {
	date, err := time.Parse(dateLayout, dateString)
	if err != nil {
		fmt.Println("Error while parsing the date:", err)
	}
	return date
}

func parseInt(s string) (int, error) {
	i, err := strconv.Atoi(s)
	if err != nil {
		return 0, errors.New("not a number")
	}
	return i, nil
}

func truncateString(str string, maxLength int) string {
	if len(str) > maxLength {
		if maxLength > 3 {
			return str[:maxLength-3] + "..."
		}
		return str[:maxLength]
	}
	return str
}

// Validator functions
func numInInterval(i int, lowerBound int, upperBound int) error {
	if (i < lowerBound) || (i > upperBound) {
		return errors.New("out of bounds")
	}
	return nil
}
