from time import sleep
import leaderboardWindow, threading, cv2, numpy as np, subprocess, drawLBImage
from gpiozero import *

global comp_Java 
comp_Java = True
button = Button(4)

def main():
    a = True
    windowOrig = createWindow()
    windowOrig.start()
    while a is True:
        button.wait_for_press()
        input = scanQR()
        createEntry(input)
        createImage()
        sleep(1)
    return

# Create leaderboard window on different thread
def createWindow():
    return threading.Thread(target = leaderboardWindow.main)

def createImage():
    drawLBImage.main()

def sortEntries():
    if (comp_Java):
        subprocess.run("javac *.java", shell=True)
    
    subprocess.run("java sortTable", shell=True)
    return

def createEntry(newEntry):
    if (newEntry == "null"):
        return 0
    else:
        entryContents = newEntry.split(",")
        if(len(entryContents) != 4):
            return 0
        else:
            with open("new entry.txt", "w") as text_file:
                text_file.write(newEntry)

def scanQR():
    global qrinput

    cap = cv2.VideoCapture(0)
    while True:
        ret, frame = cap.read()
        cv2.imshow('image', frame)
        qrinput = cv2.waitKey(5)
        if qrinput == ord('q'):
            break

    return qrinput

if __name__ == '__main__':
    main()