from PIL import Image, ImageDraw, ImageFont

def main():
    x = 0
    while True:
        drawLine(x)
        x = x + 1
        if (x == 5):
            break

def drawLine(line):
    if(line == 0):
        im = Image.open("Leaderboard_Blank.png")
    else:
        im = Image.open("Leaderboard_Edited.png")
    draw = ImageDraw.Draw(im)
    fnt = ImageFont.truetype('Elianto-Regular.ttf', 60)
    fnt2 = ImageFont.truetype('Elianto-Regular.ttf', 40)

    with open("new entries.txt", "r") as text_file:
        x = 0
        for lines in text_file:
            if x == line:
                break
            x = x + 1

    splitLine = lines.split(",")
    
    if(len(splitLine[0]) > 7):
        newString = splitLine[0]
        splitLine[0] = newString[:7] + "..."

    draw.text((110, 180 + (line * ((735-170)/5) )), splitLine[0]  , font = fnt, fill = (255, 203, 27))
    draw.text((420, 190 + (line * ((735-170)/5) )), splitLine[2], font = fnt2, fill = (255, 203, 27))
    draw.text((580, 180 + (line * ((735-170)/5) )), "- " + splitLine[1], font = fnt, fill = (255, 203, 27))
    draw.text((770, 180 + (line * ((735-170)/5) )), "points", font = fnt, fill = (255, 203, 27))

    im.save(fp = "Leaderboard_Edited.png")
    
    

if __name__ == '__main__':
    main()