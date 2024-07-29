import requests

def directory_bruteforce(url: str, directories: list) -> list:
   existing_directories = []
   for directory in directories:
      full_url = f"{url}/{directory}"
      response = requests.get(full_url)
      if response.status_code == 200:
         existing_directories.append(directory)
   return existing_directories

if __name__ == "__main__":
   url = input("Enter a URL to scan (e.g. http://example.com): ")
   directories = ["admin", "login", "test", "backup"]
   existing_directories = directory_bruteforce(url, directories)
   print(f"Extisting directories at {url}: {existing_directories}")