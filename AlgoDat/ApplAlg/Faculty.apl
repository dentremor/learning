main() = faculty(4)

faculty(n) = _faculty(n, 1, 1)

_faculty(n, i, res) =
   if (n+1)=i then
      res
   else 
      _faculty(n, i+1, i*res)
   endif