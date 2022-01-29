main() = median([1, 3, 8, 16, 30, 44])

median(list) = 
   if length(list)%2=0 then
      (list[length(list)/2] + list[(length(list)/2)-1])/2
   else 
      list[length(list)//2]
   endif