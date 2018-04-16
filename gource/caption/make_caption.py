import os
import sys

def osdelay(time: int):
    time = 0
    os.system("sleep {}".format(str(time)))
    sys.stdout.flush()

def slowPrint(text):
    text = str(text)
    for i in text:
        print(i, end="")
        osdelay(0.035)
        sys.stdout.flush()
    print()
    sys.stdout.flush()


os.system('git log --pretty="%at|%an released version %D" > OriginalCaption')
os.system('sleep 0.1')
os.system('cp OriginalCaption WIPCaption')

readLines = []
osdelay(1.7)
slowPrint("Reading Created Git Logs File : ")
osdelay(0.8)
with open("WIPCaption", 'r+') as f:
    x = f.readlines()
    _ = 0
    for i in x:
        print(str(_), end="")
        for j in range(6 - len(str(_))-1):
            print(" ", end="")
        print("Lines Read: " + i, end="")
        readLines.append(i)
        osdelay(0.02)
        _ += 1

print()
readLine = [i for i in readLines]
delim = ["|", "1", "2", "3", "4", "5", "6", "7", "8", "9","0"]
username = []

readLine.reverse()
osdelay(1.7)
slowPrint("\nReversing History : ")
osdelay(0.8)
for i in readLine:
    print("     "+i, end="")
    osdelay(0.02)
    
for i in range(len(readLine)):
    parseText = []
    whitespace = 0
    for j in range(len(readLine[i])):
        if whitespace >= 1:
            break
        
        if readLine[i][j] not in delim:
            if readLine[i][j] == " ":
                whitespace += 1
            parseText.append(readLine[i][j])

    parseText.pop()
    username.append("".join(parseText))

duplicate = []
for i in range(len(username)):
    try:
        if username[i] == username[i+1]:
            duplicate.append(i)
    except:
        pass

itera = 0
osdelay(1.7)
slowPrint("\nRemoving Duplicates Commit from same User :")
osdelay(0.8)
for i in duplicate:
    print("    Duplicates: "+readLine[i], end="")
    readLine[i] = "_DUPLICATE_\n"
    osdelay(0.02)

for i in range(len(readLine)):
    try:
        readLine.pop(readLine.index("_DUPLICATE_\n"))
    except:
        pass

print("\nNew History Created : ")
for i in readLine:
    print("    "+i, end="")
            
with open("_WIPCaption", 'w+') as f:
    print("\nCreating new Logs")
    for i in readLine:
        print("    Created New Logs : {}".format(i), end="")
        f.write(i)

os.system('mv _WIPCaption MasterCaption')
os.system('rm WIPCaption')
