from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
from keras.preprocessing import image

app = Flask(__name__)

model = tf.keras.models.load_model("checkpoint1.h5")

label = ['fresh_apple', 'fresh_banana', 'fresh_mango', 'rotten_apple', 'rotten_banana', 'rotten_mango', 'unripe_apple', 'unripe_banana', 'unripe_mango']

def preprocess_image(img):
    i = image.load_img(img, target_size=(240,240))
    i = image.img_to_array(i)/255.0
    i = i.reshape(1, 240,240,3)
    return i


@app.route("/predict", methods=["POST"])
def classify_fruit():
    image_file = request.files["image"]
    # image = tf.keras.preprocessing.image.load_img(image_file, target_size=(240, 240))
    # image_array = tf.keras.preprocessing.image.img_to_array(image) / 255.0
    # image_tensor = np.expand_dims(image_array, axis=0)
    preprocessedImage = preprocess_image(image_file)

    # Perform inference
    prediction = model.predict(preprocessedImage)
    predicted_class = np.argmax(prediction)
    confidence = prediction[0][np.argmax(prediction)]
    # Prepare the response
    response = {
        "predicted_class": predicted_class,
        "prediction_scores": prediction.tolist(),
        "confidence": confidence
    }
    return jsonify(response)

if __name__ == '__main__':
    app.run(debug = True)
