# libs
import threading
import client
import random

def main():
    print("Start")

    number_of_threads = 1
    uuids = random.sample(range(1, 65535), number_of_threads)
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
