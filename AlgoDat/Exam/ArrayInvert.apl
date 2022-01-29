main() = invert([1, 2, 3, 4, 5, 6])

invert(list) = _invert(list, length(list)-1, [])

_invert(list, i, res) =
   if i = -1 then
      res
   else
      _invert(list, i-1, list[i] -> res[length(list)-1-i])
   endif

