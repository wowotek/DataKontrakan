from __whash__ import *

def std_hash(data):
    hd = data
    for _ in range(25):
        hd = createHash1(createHash512(createHash256(createHash1(data))))

    return hd

def create_block(data1, data2):
    
    hd1 = encode64(data1)
    for _ in range(2):
        hd1 = std_hash(hd1)

    hd2 = encode64(data2)
    for _ in range(2):
        hd2 = std_hash(hd2)

    new_data = std_hash(hd1) + hd2 + hd1 + std_hash(hd2)
            
    return std_hash(encode64(new_data))
    
