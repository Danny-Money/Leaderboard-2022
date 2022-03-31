import tkinter, os
from PIL import Image, ImageTk
from matplotlib import container

def main():
    #Create tkinter window
    root = tkinter.Tk()  
    root.title("display image")
    root.attributes('-fullscreen', True)

    #Setup images
    stgImg = ImageTk.PhotoImage(file = "Leaderboard_Edited.png")
    label = tkinter.Label(root, image = stgImg)
    label.pack()

    def update_display():
        stgImg = ImageTk.PhotoImage(file = "Leaderboard_Edited.png")
        label.configure(image = stgImg)
        label.image = stgImg
        label.update_idletasks()
        label.after(1500, update_display)

    update_display()

    #Start window
    root.mainloop()

if __name__ == '__main__':
    main()