import time


def check_password():
    password = "abcd"
    no_of_tries = 0
    delay = 1
    while True:
        input_pw = input("Type password:\n")
        if input_pw == password:
            print("Successfull login")
            break
        else:
            no_of_tries += 1
            if no_of_tries >= 3:
                sleep = delay * delay
                delay += 1
                print(f"Wrong password. Try again in {sleep} seconds")
                time.sleep(sleep)
            else:
                print("Wrong password. Try again")


check_password()
