main() = prime(100)

prime(n) = _primes(n, 0, [])


_primes(n, i, p_list) = if n<i then
      p_list
      else
         if isPrime(i, 2) = true then
            _primes(n, i+1, i->p_list[length(p_list)])
         else
         endif
   endif



isPrime(n, j) = 
   if _isPrime(n, j) = true then
      true
   else
      false
   endif


_isPrime(n,j) = if n=j then
      true
   else if n%j = 0 then
      false
   else
      _isPrime(n, j+1)
   endif
endif

         
