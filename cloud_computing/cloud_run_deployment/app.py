from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
from keras.preprocessing import image
from werkzeug.utils import secure_filename
import os

app = Flask(__name__)

model = tf.keras.models.load_model("checkpoint1.h5")

label = ['fresh_apple', 'fresh_banana', 'fresh_mango', 'rotten_apple', 'rotten_banana', 'rotten_mango', 'unripe_apple', 'unripe_banana', 'unripe_mango']

def preprocess_image(img):
    i = tf.keras.utils.load_img(img, target_size=(240,240))
    i = tf.keras.utils.img_to_array(i)/255.0
    i = i.reshape(1,240,240,3)
    return i

@app.route("/", methods=["GET", "POST"])
def classify_fruit():    
    if request.method == "POST":
        image_file = request.files["img"]
        if image_file is None or image_file.filename == "":
            return jsonify({"error": "no file"})
        
        try:
            # image = tf.keras.preprocessing.image.load_img(image_file, target_size=(240, 240))
            # image_array = tf.keras.preprocessing.image.img_to_array(image) / 255.0
            # image_tensor = np.expand_dims(image_array, axis=0)
            # create the folders when setting up your app
            os.makedirs(os.path.join(app.instance_path, 'temp_upload'), exist_ok=True)

            # when saving the file
            filename = os.path.join(app.instance_path, 'temp_upload', secure_filename(image_file.filename))
            image_file.save(filename)
            preprocessedImage = preprocess_image(filename)

            # Perform inference
            prediction = model.predict(preprocessedImage)
            predicted_class = label[np.argmax(prediction)]
            confidence = prediction[0][np.argmax(prediction)]
            # Prepare the response
            print(predicted_class)
            print(confidence)            
            response = {
                "predicted_class": str(predicted_class),
                "confidence": str(confidence)
            }
            return jsonify(response)
        except Exception as e:
            return jsonify({"error":str(e)})
    return jsonify({"message":"test"})

if __name__ == '__main__':
    app.run(debug = True)
