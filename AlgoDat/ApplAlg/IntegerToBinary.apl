main() = getBinary(37)

getBinary(decimal) = getBinaryHelper(decimal, [])

getBinaryHelper(decimal, list) =
   if decimal = 1 then
      1->list[length(list)]
      list = invert(list)
      list
   else if decimal%2 = 0 then
      0->list[length(list)]
   else:
      1->list[length(list)]
   getBinaryHelper(decimal//2, list)
   endif
endif

invert(list) = invertHelper(list, 1)

invertHelper(list, i) =
   cache = list[length(list)-i]
   list[length(list)-1] = list[i-1]
   list[i-1] = cache
   if (length(list) // i) = 2 then
      list
   else
      invertHelper(list, i+1)
   endif