main() = addition(16, 18)

addition(a, b) = _addition(fill([], a, 0), fill([], b, 0), 0, false)

fill(list, n, i) = 
   if i=n then
      list[3,4]
   else
      fill((i->list[(i//100),((i//10)%10),(i%10)]), n, (i+1))
endif

_addition(a, b, i, carry) = 
   if 1=1 then
      a
   else
      b
   endif