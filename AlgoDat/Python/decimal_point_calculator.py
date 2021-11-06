def get_decimal(number, decimal):
   return get_decimal_helper(number, decimal, [], 1, 0, 0)

def get_decimal_helper(number, decimal, list, i_number, i_decimal, result):
      cache = result + (i_number/power(10, i_decimal))
      list.append(cache)
      if (cache*cache) < number:
         return get_decimal_helper(number, decimal, list, i_number+1, i_decimal, result)
      elif i_decimal == (decimal+1):
         return result
      else:
         return get_decimal_helper(number, decimal, [], 1, i_decimal+1, list[len(list)-2])
      
def power(basis, power):
   return power_helper(basis, power, 1, 1)

def power_helper(basis, power, i, result):
   if i > power:
      return result
   else:
      return power_helper(basis, power, i+1, basis*result)
      

print(get_decimal(2,11))