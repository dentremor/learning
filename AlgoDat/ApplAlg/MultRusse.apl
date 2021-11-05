main() = multRusse(45, 19)

multRusse(a, b) = _multRusse(a, b, 0)

_multRusse(a, b, sum) = if a=1 then
      sum + b
   else if a%2 = 1 then
      _multRusse(a//2, b*2, sum+b)
   else
      _multRusse(a//2, b*2, sum)
   endif
endif