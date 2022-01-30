main() = isPali('hasnnah')

isPali(word) = _isPali(word, 0)

_isPali(word, i) = 
   if length(word)//2 = i then
      'The given word is a Palindrome'
   else if word[i] = word[length(word)-i-1] then
      _isPali(word, i+1)
   else 
      'The given word is not a Palindrome'
   endif
   endif