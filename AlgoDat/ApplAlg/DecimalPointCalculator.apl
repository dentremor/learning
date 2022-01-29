main() = getDecimal(2, 11)

getDecimal(number, decimal) = getDecimalHelper(number, decimal, [], 1, 0, 0)

getDecimalHelper(number, decimal, list, i_number, i_decimal, result) = 
   if (result + (i_number/power(10, i_decimal)))^2 < number then
      getDecimalHelper(number, decimal, (result + (i_number/power(10, i_decimal)))^2 -> list[length(list)], i_number+1, i_decimal, result)
   else if i_decimal = (decimal+1) then
      result
   else
      getDecimalHelper(number, decimal, [], 1, i_decimal+1, list[length(list)])
   endif
endif
      

power(basis, power) = powerHelper(basis, power, 1, 1)

powerHelper(basis, power, i, result) =
   if i>power then
      result
   else
      powerHelper(basis, power, i+1, basis*result)
   endif
