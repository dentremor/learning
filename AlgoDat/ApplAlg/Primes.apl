main() = prime(100)

prime(n) = _primes(n, 2, [])


_primes(n, i, p_list) = 
   if n<i then
      p_list
   else if isPrime(i) = true then
      _primes(n, i+1, i->p_list[length(p_list)])
   else
      _primes(n, i+1, p_list)
   endif
endif

isPrime(t) = _isPrime(t, 2)

_isPrime(n,j) = 
   if n=j then
      true
   else if n%j = 0 then
      false
   else
      _isPrime(n, j+1)
   endif
endif

         
