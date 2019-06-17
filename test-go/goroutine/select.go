package goroutine

import "fmt"

func random(c chan int64, max int) {
	defer close(c)
	for i := 0; i < max; i++ {
		select {
		case c <- 0:
		case c <- 1:
		default:
			fmt.Println("channel full")
		}
		fmt.Print(<-c)
	}
}

func main() {
	c := make(chan int64, 1)
	random(c, 10)
}
