package main

import "fmt"

type Address struct {
	Street string
	City   string
	State  string
	Zip    string
}

func (a Address) ToStringAdd() string {
	return fmt.Sprintf("%s, %s, %s %s", a.Street, a.City, a.State, a.Zip)
}

type Person struct {
	Name    string
	Address // Embed the Address struct
}

func main() {
	p := Person{
		Name: "John Doe",
		Address: Address{
			Street: "123 Main St",
			City:   "Anytown",
			State:  "CA",
			Zip:    "90210",
		},
	}

	fmt.Println(p.ToStringAdd()) // Output: 123 Main St, Anytown, CA 90210
}
