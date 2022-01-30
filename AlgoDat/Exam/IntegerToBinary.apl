main() = toBin(13)

toBin(n) = _toBin(n, '')

_toBin(n, res) = 
   if n = 0 then  
      invert(res)
   else if n%2 = 0 then
      _toBin(n//2, res+'0')
   else
      _toBin(n//2, res+'1')
   endif
   endif

invert(list) = _invert(list, 0, '')

_invert(list, i, res) = 
   if i = length(list) then
      res
   else
      _invert(list, i+1, list[length(list)-1-i] -> res[length(res)])
   endif