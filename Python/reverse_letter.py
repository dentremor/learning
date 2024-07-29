def reverse_letter(input: str) -> str:
   return ''.join([char for char in input if char.isalpha()])[::-1]