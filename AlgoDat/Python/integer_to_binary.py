def get_binary(decimal):
   return get_binary_helper(decimal, '')

def get_binary_helper(decimal, string):
   if decimal == 1:
      return string + '1'
   else:
      return get_binary_helper(decimal//2, string + str(decimal%2))


print(get_binary(37))