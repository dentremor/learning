package montecarlo

import "math/rand"

type point struct {
	x float64
	y float64
}

func rainDrop() point{
	lenghtOfField := 1
	p := point{
		x:(0.5 - rand.Float64()*1) * lenghtOfField, 
		y:(0.5 - rand.Float64()*1) * lenghtOfField
	}
	return c
}

func isPointInCircle(point) {
	lenghtOfField := 1
	return math.pow(point.x, 2) + math.pow(point.y, 2) <= math.pow(lenghtOfField/2, 2)
}

func main() {

}