main() = fibonacci(4)

fibonacci(n) = _fibonacci(n, 2, [0, 1])

_fibonacci(n, i, list) = 
   if n=1 then
      [0]
   else if n <= i then
      list[i-1]
   else
      _fibonacci(n, i+1, list[i-2]+list[i-1] -> list[length(list)])
   endif
   endif