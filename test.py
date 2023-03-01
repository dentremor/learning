from random import random


def pig_it(text):
   return ' '.join(e if len(e)==1 else e[1:] + e[0] + 'ay' for e in text.split(' '))
 
print(pig_it("Hey fellow warriors"))

print(random())