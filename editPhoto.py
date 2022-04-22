from PIL import Image, ImageDraw, ImageFont

im = Image.open("Leaderboard.png")

fnt = ImageFont.truetype('Elianto-Regular.ttf', 60)
fnt2 = ImageFont.truetype('Elianto-Regular.ttf', 40)

# Top left corner of entry space is at (40,170); (x,y)
# Top Right corner if entry space is at (940, 170); (x,y)
# Bottom left corner of entry space is at (40, 735); (x,y)
# Bottom right corner of entry space is at (940,735); (x,y)

text_spacing = (735-170) / 5
placex = 50
namex = 110
teamx = 420
pointx = 580

draw = ImageDraw.Draw(im)

draw.text((placex + 20, 180 + (0 * text_spacing)), "1:", font = fnt, fill = (255, 203, 27))
draw.text((placex     , 180 + (1 * text_spacing)), "2:", font = fnt, fill = (255, 203, 27))
draw.text((placex     , 180 + (2 * text_spacing)), "3:", font = fnt, fill = (255, 203, 27))
draw.text((placex     , 180 + (3 * text_spacing)), "4:", font = fnt, fill = (255, 203, 27))
draw.text((placex     , 180 + (4 * text_spacing)), "5:", font = fnt, fill = (255, 203, 27))

# draw.text((namex, 180 + (0 * text_spacing)), "Zachary"  , font = fnt, fill = (255, 203, 27))
# draw.text((namex, 180 + (1 * text_spacing)), "Daniel"   , font = fnt, fill = (255, 203, 27))
# draw.text((namex, 180 + (2 * text_spacing)), "Ethan"    , font = fnt, fill = (255, 203, 27))
# draw.text((namex, 180 + (3 * text_spacing)), "Cellarius", font = fnt, fill = (255, 203, 27))
# draw.text((namex, 180 + (4 * text_spacing)), "MMMMM..." , font = fnt, fill = (255, 203, 27))

# draw.text((teamx, 190 + (0 * text_spacing)), "(3536)", font = fnt2, fill = (255, 203, 27))
# draw.text((teamx, 190 + (1 * text_spacing)), "(3536)", font = fnt2, fill = (255, 203, 27))
# draw.text((teamx, 190 + (2 * text_spacing)), "(3536)", font = fnt2, fill = (255, 203, 27))
# draw.text((teamx, 190 + (3 * text_spacing)), "(3536)", font = fnt2, fill = (255, 203, 27))
# draw.text((teamx, 190 + (4 * text_spacing)), "(3536)", font = fnt2, fill = (255, 203, 27))

draw.text((pointx, 180 + (0 * text_spacing)), "- 1200", font = fnt, fill = (255, 203, 27))
draw.text((pointx +190, 180 + (0 * text_spacing)), "points", font = fnt, fill = (255, 203, 27))
# draw.text((pointx, 180 + (1 * text_spacing)), "- 120 points", font = fnt, fill = (255, 203, 27))
# draw.text((pointx, 180 + (2 * text_spacing)), "- 120 points", font = fnt, fill = (255, 203, 27))
# draw.text((pointx, 180 + (3 * text_spacing)), "- 120 points", font = fnt, fill = (255, 203, 27))
# draw.text((pointx, 180 + (4 * text_spacing)), "- 120 points", font = fnt, fill = (255, 203, 27))

im.show()