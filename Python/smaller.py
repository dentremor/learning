def smaller(arr: list) -> list:
   li = []
   for x, i in enumerate(arr):
      count = 0
      for y in range(len(arr)-i+1):
         if y < x:
            count+=1
      li.append(i)
   return li

def main():
   print(1)