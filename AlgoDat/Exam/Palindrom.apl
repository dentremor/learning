main() = isPali('hanah')

isPali(word) = _isPali(word, 0)

_isPali(word, i) = 
   if length(word)//2 = i then
      true
   else if word[i] = word[length(word)-i-1] then
      _isPali(word, i+1)
   else 
      false
   endif
   endif