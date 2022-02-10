main() = invert('Hallo')

invert(string) = _invert(string, 0, '')

_invert(string, i, res) = 
   if i = length(string) then
      res
   else
      _invert(string, i+1, res+string[length(string)-1-i])
   endif
