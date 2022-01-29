main() = binsearch(13, [1, 5, 7, 9, 12, 14, 17, 20])

binsearch(n, list) = _binsearch(n, list, 0, length(list)-1)

_binsearch(n, list, lowp, highp) =
   if lowp=highp then
      if n=list[lowp] then
         list[lowp]
      else
         'Not Found'
      endif
   else if highp-lowp=1 then
      if n=list[highp] then
         list[highp]
      else
         'Not Found'
      endif
   else
      if n > list[(highp+lowp)//2] then
         _binsearch(n, list, (highp+lowp)//2, highp)
      else 
         _binsearch(n, list, lowp, (highp+lowp)//2)
      endif
   endif
endif
