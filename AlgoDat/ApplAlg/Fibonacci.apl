main() = fibonacci(1)

fibonacci(n) = _fibonacci(n, 1, 0, 1)

_fibonacci(n, i, fPair, sPair) = 
   if n = 0 then
      fPair
   else if n = i then
      sPair
   else
      _fibonacci(n, i+1, sPair, fPair+sPair)
   endif
endif