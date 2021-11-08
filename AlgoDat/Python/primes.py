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
      if is_prime_helper(n, j) is True:
         return True
      else:
         return False


def is_prime_helper(n, j):
    if n == j:
        return True
    elif n % j == 0:
        return False
    else:
        return is_prime_helper(n, j+1)


print(primes(100))
