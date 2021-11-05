def mult_russe(a, b) -> int:
    return _mult_russe(a, b, 0)


def _mult_russe(a, b, sum) -> int:
    if a == 1:
        return sum + b
    elif a % 2 == 1:
        return _mult_russe(a//2, b*2, sum+b)
    else:
        return _mult_russe(a//2, b*2, sum)


print(mult_russe(45, 19))
