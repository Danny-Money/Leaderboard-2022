import leaderboardWindow, threading, subprocess, qrReader
from gpiozero import *

global comp_Java 
comp_Java = True
button = Button(4)
e = threading.Event()
e.clear()

def main():
    if (comp_Java):
        subprocess.run("javac *.java", shell=True)
    a = True
    windowOrig = createWindow()
    windowOrig.start()
    while a is True:
        print("Waiting for press")
        button.wait_for_press()
        input = scanQR()
        createEntry(input)
        print("Entry created")
        sortEntries()
        print("Entries sorted")
        createImage()
        print("Image edited")
    return

# Create leaderboard window on different thread
def createWindow():
    return threading.Thread(target = leaderboardWindow.main, args=(e,))

def createImage():
    e.set()

def sortEntries():
    subprocess.run("java sortTable", shell=True)
    return

def createEntry(newEntry):
    if newEntry is not None:
        entryContents = newEntry.split(",")
        if(len(entryContents) != 4):
            return 0
        else:
            with open("new entry.txt", "w") as text_file:
                text_file.write(newEntry)

def scanQR():
    a = qrReader.main()
    return a

if __name__ == '__main__':
    main()