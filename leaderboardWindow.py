import tkinter, os, drawLBImage
from PIL import Image, ImageTk

def main(a):
    e = a
    #Create tkinter window
    root = tkinter.Tk()  
    root.title("display image")
    root.attributes('-fullscreen', True)

    #Setup images
    stgImg = ImageTk.PhotoImage(file = "Leaderboard_Edited.png")
    label = tkinter.Label(root, image = stgImg)
    label.pack()
    
    def update_display():
        if e.isSet():
            drawLBImage.main()
            e.clear()
        else:
            stgImg = ImageTk.PhotoImage(file = "Leaderboard_Edited.png")
            label.configure(image = stgImg)
            label.image = stgImg
            label.update_idletasks()
        label.after(100, update_display)

    update_display()

    #Start window
    root.mainloop()