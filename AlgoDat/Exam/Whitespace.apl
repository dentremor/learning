main() = delete('Ha     llo We   lt!   ')

delete(word) = _delete(word, 0, '')

_delete(word, i, result) = 
   if i = length(word) then
      result
   else if isWhiteSpace(word[i]) then
      _delete(word, i+1, result)
   else
      _delete(word, i+1, result+word[i]) 
   endif
   endif

isWhiteSpace(char) = 
   if char = ' ' then
      true
   else
      false
   endif