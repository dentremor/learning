from tabulate import tabulate

def init() -> list:
   example_todos_1 = {'name': 'shillin', 'deadline': '02.08.2024', 'pending': True, 'priority': 1}
   example_todos_2 = {'name': 'interview', 'deadline': '22.07.2024', 'pending': True, 'priority': 3}
   example_todos_3 = {'name': 'gym', 'deadline': '20.07.2024', 'pending': True, 'priority': 2}
   return [example_todos_1, example_todos_2, example_todos_3]

def list_todos(todos: list) -> None:
   print(tabulate(todos, headers="keys", tablefmt="fancy_grid"))
   
def add_todo(todos: list) -> None:
   name = input("Name: ")
   deadline = input("Deadline (DD.MM.YYYY): ")
   priority = int(input("Name (1-3): "))
   todos.append({"name": name, "deadline": deadline, "pending": True, "priority": priority})
   
def del_todo(todos: list) -> None:
   hi = "bla"
   
def main():
   todos = init()
   inp = ''
   
   while True:
      inp = input("Enter command (add, del, list, q): ").strip()
      match inp:
         case 'add':
            add_todo(todos)
         case 'del':
            del_todo(todos)
         case 'list':
            list_todos(todos)
         case 'q':
            exit()

if __name__ == "__main__":
    main()