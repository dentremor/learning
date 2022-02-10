main() = sum(100)

sum(n) = _sum(n, 1, 0)

_sum(n, i, sum) = 
   if i>n then
      sum
   else
      _sum(n, i+1, sum+i)
   endif