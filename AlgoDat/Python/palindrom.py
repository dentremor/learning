def isPalindrom(input):
   return _isPalindrom(input, 0, len(input)-1)

def _isPalindrom(input, i, j):
   if j == -1:
      return True
   if input[i] == input[j]:
      return _isPalindrom(input, i+1, j-1)
   else:
      return False
      
print(isPalindrom('12321'))