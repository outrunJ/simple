package goroutine

import (
	"time"
	"fmt"
)

func main() {
	c := make(chan int64, 5)
	defer close(c)
	timeout := make(chan bool)
	defer close(timeout)
	go func() {
		c <- 1
		time.Sleep(time.Second)
		timeout <- true
	}()

	time.Sleep(2 * time.Second)

	select {
	case <-timeout:
		fmt.Println("timeout")
	case i := <-c:
		fmt.Println(i)
	default:
		fmt.Println("channel empty")
	}
}
