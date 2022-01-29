main() = isPrime(5)

isPrime(n) = _isPrime(n, 2)

_isPrime(n, i) = 
   if n=i then
      true
   else if n%i = 0 then
      false
   else
      _isPrime(n, i+1) 
   endif
endif