import time
def f1():
    time.sleep(2)
    return 2
def f():
    yield f1()


if __name__ == '__main__':
    print(next(f()))
    print(1)