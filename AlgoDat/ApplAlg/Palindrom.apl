main() = isPalindrom('otto')

isPalindrom(input) = _isPalindrom(input, 0, length(input)-1)

_isPalindrom(input, i, j) = 
   if j = -1 then 
      true
   else if input[i] = input[j] then
      _isPalindrom(input, i+1, j-1)
   else 
      false
   endif
endif