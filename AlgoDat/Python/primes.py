def primes(n):
    return _primes(n, 2, [])


def _primes(n, i, p_list):
    if n > i:
        if isPrime(i, 2) is True:
            p_list.append(i)
        return _primes(n, i+1, p_list)
    else:
        return p_list


def isPrime(n, j):
    if n >= j:
        if _isPrime(n, j) is True:
            return True
        else:
            return False


def _isPrime(n, j):
    if n == j:
        return True
    elif n % j == 0:
        return False

    else:
        return _isPrime(n, j+1)


print(primes(100))
