main() = quer(1234)

quer(n) = _quer(n, len(n), 0, 0)

_quer(n, len, i, res) = 
   if len = i then
      res
   else
      _quer(n//10, len, i+1, res+(n%10))
   endif

len(n) = _len(n, 0)

_len(n, i) =
   if n//10 = 0 then
      i+1
   else 
      _len(n//10, i+1)
   endif