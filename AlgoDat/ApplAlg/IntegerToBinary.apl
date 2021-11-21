main() = getBinary(37)

getBinary(decimal) = getBinaryHelper(decimal, '')

getBinaryHelper(decimal, string) =
   if decimal = 1 then
      string + 1
   else
      getBinaryHelper(decimal//2, string + decimal%2)
   endif