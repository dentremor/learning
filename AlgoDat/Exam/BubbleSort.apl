use 'helpers/list.apl'

main() = bubsort([10, 3, 5, 2, 90, 74, 40, 345, 2, 8, 1, 0])

bubsort(list) = _bubsort(list, 0, 0)

_bubsort(list, i, j) =
if i=(length(list)-1) then
   list
else
   __bubsort(list, i, j)
endif

__bubsort(list, i, j) =
   if j = length(list)-1 then
      _bubsort(list, i+1, 0)
   else
      if list[j] > list[j+1] then
         __bubsort(change(list, j, j+1), i, j+1)
      else
         __bubsort(list, i, j+1)
      endif
   endif


