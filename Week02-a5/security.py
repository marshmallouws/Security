#import webget

import re
def check_password(password):
    common_passwords = ['12345678', '1qaz2wsX']

    pattern = '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})'
    for p in common_passwords:
        if p == password:
            return False

    if re.match(pattern, password) is not None:
        return True
    return False

print(check_password("Hej3Mef!fdVHdDU"))