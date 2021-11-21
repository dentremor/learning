def primes(n):
    return primes_helper(n, 2, [])


def primes_helper(n, i, p_list):
    if n > i:
        if is_prime(i, 2) is True:
            p_list.append(i)
        return primes_helper(n, i+1, p_list)
    else:
        return p_list

def is_prime(n, j):
    if n == j:
        return True
    elif n % j == 0:
        return False
    else:
        return is_prime(n, j+1)


print(primes(100))
