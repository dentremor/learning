main() = multRus(45, 19)

multRus(a, b) = _multRus(a, b, 0)

_multRus(a, b, sum) = 
   if a=1 then
      sum + b
   else if a%2 = 1 then
      _multRus(a//2, b*2, sum+b)
   else
      _multRus(a//2, b*2, sum)
   endif
endif