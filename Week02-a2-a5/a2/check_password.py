import wget
import os.path
import sys

import re


def download():
    # Could have used a more reliable source, but this was the easiest to download
    url = "https://raw.githubusercontent.com/danielmiessler/SecLists/master/Passwords/Common-Credentials/10-million-password-list-top-1000000.txt"
    wget.download(
        url,
        "C:\\Users\Annika\Desktop\Sem4\coding_projects\security\code_assignments\Week02-a5",
    )


def check_password(password):
    pattern = (
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9!@#\$%\^&\*])(?=.*[!@#\$%\^&\*])(?=.{8,})"
    )

    if re.match(pattern, password) is not None:
        return True
    return False

    with open("10-million-password-list-top-1000000.txt") as f:
        if password in f.read():
            return False


if __name__ == "__main__":
    if len(sys.argv) == 2:  # First arg is program
        args = sys.argv[1]
        print(args, " :", check_password(args))
    else:
        print("Hej3Mef!fdVHdDU: ", check_password("Hej3Mef!fdVHdDU"))
        print("qwerty: ", (check_password("qwerty")))
        print("annikaersej: ", (check_password("annikaersej")))
