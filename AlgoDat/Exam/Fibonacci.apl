main() = fibonacci(4)

fibonacci(n) = _fibonacci(n, 3, 0, 1)

_fibonacci(n, i, low, high) = 
   if n=1 then
      low
   else if n=0 then
      high
   else if n>i then
      _fibonacci(n, i+1, high, low+high)
   else
      low+high
   endif
   endif
   endif
