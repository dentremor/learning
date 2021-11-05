main() = prime(100)

prime(n) = _prime(n, 0, [])


_primes(n, i, p_list) = if n > i then
      if isPrime(i, 2) = true then
         _primes(n, i+1, i->p_list[length(p_list)])
   else
      p_list
   endif



isPrime(n, j) = if n >= j then
      if _isPrime(n, j) = true then
         true
      else
         false
      endif
   endif


_isPrime(n,j) = if n = j then
      true
   else if n%j = 0 then
      false
   else
      _isPrime(n, j+1)
   endif
endif
         
