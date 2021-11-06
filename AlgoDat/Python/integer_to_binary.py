def get_binary(decimal):
   return get_binary_helper(decimal, [])

def get_binary_helper(decimal, list):
   if decimal == 1:
      list.append(1)
      invert(list)
      return list
   elif decimal%2 == 0:
      list.append(0)
   else:
      list.append(1)
   return get_binary_helper(decimal//2, list)

def invert(list):
   return invert_helper(list, 1)

def invert_helper(list, i):
   cache = list[len(list)-i]
   list[len(list)-i] = list[i-1]
   list[i-1] = cache
   if len(list) // i == 2:
      return list
   else:
      return invert_helper(list, i+1) 

print(get_binary(37))