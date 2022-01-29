main() = delete('Ha2l3l5o We564lt2!1')

delete(str) = _delete(str, 0, '')

_delete(str, i, res) = 
   if i = length(str) then
      res
   else if isDigit(str[i]) then
      _delete(str, i+1, res)
   else
      _delete(str, i+1, res+str[i])
   endif
   endif

isDigit(x) = _isDigit(x, 0, '0123456789')

_isDigit(x, i, list) =
   if i = length(list)-1 then
      false
   else if list[i] = x then
      true
   else 
      _isDigit(x, i+1, list)
   endif
   endif