main() = primes(100)

primes(n) = _primes(n, 2, [])

_primes(n, i, res) =
   if n < i then
      res
   else if isPrime(i) then
      _primes(n, i+1, i -> res[length(res)])
   else
      _primes(n, i+1, res)
   endif
   endif

isPrime(n) = _isPrime(n, 2)

_isPrime(n, i) = 
   if n = i then
      true
   else if n%i = 0 then
      false
   else
      _isPrime(n, i+1)
   endif
   endif