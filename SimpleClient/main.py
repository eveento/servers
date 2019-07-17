# libs
import threading
import client
import random

def main():
    number_of_threads = 1
    uuids = random.sample(range(1, 9223372036854775807), number_of_threads)
    # while True:
    n = 2
    while n>1:
        print("Start")
        n = 0
        
        threads = []

        for uuid in uuids:
            t = threading.Thread(target=client.job, args=(uuid,))
            threads.append(t)
            t.start()

        for i in range(number_of_threads):
            threads[i].join()

        print("Finish")


if __name__ == "__main__":
    main()
