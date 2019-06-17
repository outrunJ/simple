package sync

import "sync"

func mutex() {
	l := new(sync.Mutex)
	l.Lock()
	defer l.Unlock()

	rwl := new(sync.RWMutex)
	rwl.RLock()
	defer rwl.RUnlock()
}
