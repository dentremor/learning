main() = toInt('101010101111')

toInt(input) = _toInt(input, 0, 0)

_toInt(input, i, res) = 
   if length(input) = i then
      res
   else if input[length(input)-i-1] = '1' then
      _toInt(input, i+1, res+ (1*2^i))
   else 
      _toInt(input, i+1, res)
   endif
   endif