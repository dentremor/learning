main() = sum(123, 38)

sum(a, b) = _sum(a, b, 0, 0, 0)

_sum(a, b, carry, res) = 
   if a%10 = 0 and b%10 = 0 then
      res
   else if getDigit(a, i)+getDigit(b, i)+carry >= 10 then
      _sum(a, b, i+1, 1, res+(a+b+carry-10))
   else
      _sum(a, b, i+1, 0, res+(a+b+carry))
   endif
   endif

getDigit(n, pointer) = _getDigit(n, pointer, i, [])