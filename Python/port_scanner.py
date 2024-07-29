import socket

def port_scanner(ip: str, ports: list[int]) -> list[int]:
    open_ports = []
    for port in ports:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
            sock.settimeout(1)
            result = sock.connect_ex((ip, port))
            if result == 0:
                open_ports.append(port)
    return open_ports

if __name__ == '__main__':
   ip = input(f'Enter IP address: ')
   ports = [21, 22, 23, 80, 443, 8080]
   open_ports = port_scanner(ip, ports)
   print(f'Open ports on {ip}: {open_ports}')