main() = mittelwert([1, 3, 4, 5, 7, 8, 10, 16])

mittelwert(list) = _mittelwert(list, 0, 0)

_mittelwert(list, i, sum) =
   if (i = length(list)) then
      sum/length(list)
   else 
      _mittelwert(list, i+1, sum+list[i])
   endif