import random

def fizz_buzz(i: int) -> str:
   if i%3 == 0:
      if i%5 == 0:
         return "Fizz Buzz"
      else: 
         return "Fizz"
   elif i%5 == 0:
      return "Buzz"
   else:
      return str(i)
   
def main():
   print(fizz_buzz(random.randint(0,1000)))
      