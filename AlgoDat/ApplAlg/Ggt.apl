main() = ggt(63,27)

ggt(x,y) = if x=0 then
      y
   else
      if x<y then
         ggt(y,x)
      else
         ggt(x%y,y)
      endif
   endif