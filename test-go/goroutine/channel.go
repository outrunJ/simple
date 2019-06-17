package goroutine

import (
	"fmt"
	"time"
)

type AutoInc struct {
	start, step int
	queue       chan int
	running     bool
}

func New(start, step int) (*AutoInc) {
	ai := &AutoInc{
		start:   start,
		step:    step,
		running: true,
		queue:   make(chan int, 4),
	}
	go ai.process()
	return ai
}

func (ai *AutoInc) process() {
	defer func() { recover() }()
	for i := ai.start; ai.running; i += ai.step {
		ai.queue <- i
	}
}

func (ai *AutoInc) Id() int {
	return <-ai.queue
}

func (ai *AutoInc) Print() {
	for ai.running {
		fmt.Println(ai.Id())
	}
}

func (ai *AutoInc) Close() {
	ai.running = false
	close(ai.queue)
}

func main() {
	ai := New(0, 1)
	go ai.Print()

	time.Sleep(1000 * time.Millisecond)
	ai.Close()
	time.Sleep(1000 * time.Millisecond)
}
