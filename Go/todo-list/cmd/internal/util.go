package internal

func readInput() string {
	text, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error while reading:", err)
	}
	return text[:len(text)-1]
}

func clearScreen() {
	fmt.Print("\033[2J")
}

func parseDate(dateString string) time.Time {
	date, err := time.Parse(dateLayout, dateString)
	if err != nil {
		fmt.Println("Error while parsing the date:", err)
	}
	return date
}

func parseInt(s string, lowerBound int8, upperBound int8) (int8, error) {
	int8(i), err := strconv.ParseInt(s, 10, 8)
	if err != nil {
		return 0, err
	} else if (i < lowerBound) || (i > upperBound) {
		return 0, err
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
