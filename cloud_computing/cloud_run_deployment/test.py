import requests

resp = requests.post("https://ricek1-oes4ruxepa-et.a.run.app", files={'image': open('apple.jpg', 'rb')})

print(resp.json())