import cv2
import numpy as np
from pyzbar.pyzbar import decode

def main():
    print("Reading qrcode")
    global barcodeData
    barcodeData = ''
    
    def decoder(image):
        global barcodeData
        gray_img = cv2.cvtColor(image,0)
        barcode = decode(gray_img)

        for obj in barcode:
            points = obj.polygon
            (x,y,w,h) = obj.rect
            pts = np.array(points, np.int32)
            pts = pts.reshape((-1, 1, 2))
            cv2.polylines(image, [pts], True, (0, 255, 0), 3)

            barcodeData = obj.data.decode("utf-8")
            barcodeType = obj.type
            string = "Data " + str(barcodeData) + " | Type " + str(barcodeType)
            
            cv2.putText(frame, string, (x,y), cv2.FONT_HERSHEY_SIMPLEX,0.8,(255,0,0), 2)
           
           
    cap = cv2.VideoCapture(0)
    while True:
        ret, frame = cap.read()
        decoder(frame)
        cv2.imshow('Image', frame)
        code = cv2.waitKey(1)
        if barcodeData != '':
            cap.release()
            cv2.destroyAllWindows()
            print(barcodeData)
            return barcodeData
        if code == ord('q'):
            cap.release()
            cv2.destroyAllWindows()
            break

if __name__ == '__main__':
    b = main()