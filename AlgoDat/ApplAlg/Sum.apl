main() = sum(100)

sum(N) = _sum(N, 0, 1)

_sum(N, sum, i) = if i>N then
      sum
   else
      _sum(N, sum+i, i+1)
   endif