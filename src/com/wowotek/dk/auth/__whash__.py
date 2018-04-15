import hashlib as h
import base64 as b

def createHash512(string_object:str, iteration=10):
    hashed = string_object
    
    for _ in range(iteration):
        for __ in range(iteration):
            hashed = b.b64encode(str(hashed).encode())

        hashed = str(hashed)
        for __ in range(iteration):
            hashed = h.sha512(hashed.encode()).hexdigest()

        for __ in range(iteration):
            hashed = b.b64encode(str(hashed).encode())

        hashed = str(hashed)
        for __ in range(iteration):
            hashed = h.sha512(hashed.encode()).hexdigest()
            
    return str(hashed)

def createHash256(string_object:str, iteration=10):
    hashed = string_object
    
    for _ in range(iteration):
        for __ in range(iteration):
            hashed = b.b64encode(str(hashed).encode())

        hashed = str(hashed)
        for __ in range(iteration):
            hashed = h.sha256(hashed.encode()).hexdigest()

        for __ in range(iteration):
            hashed = b.b64encode(str(hashed).encode())

        hashed = str(hashed)
        for __ in range(iteration):
            hashed = h.sha256(hashed.encode()).hexdigest()
            
    return str(hashed)

def createHash1(string_object:str, iteration=10):
    hashed = string_object
    
    for _ in range(iteration):
        for __ in range(iteration):
            hashed = b.b64encode(str(hashed).encode())

        hashed = str(hashed)
        for __ in range(iteration):
            hashed = h.sha1(hashed.encode()).hexdigest()

        for __ in range(iteration):
            hashed = b.b64encode(str(hashed).encode())

        hashed = str(hashed)
        for __ in range(iteration):
            hashed = h.sha1(hashed.encode()).hexdigest()
            
    return str(hashed)

def encode64(string_object:str, iteration=10):
    encoded = string_object
    for _ in range(iteration):
        encoded = b.b64encode(str(encoded).encode())

    return encoded
