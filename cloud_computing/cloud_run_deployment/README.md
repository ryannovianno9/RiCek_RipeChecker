CREDIT
1. Base API: Rezha Aulia
2. Updating API: Javier Al Faiza

### 1. Write App (Flask, TensorFlow) and install the requirements library

### 2. Setup Google Cloud
Create new project
activate cloud run API and Cloud Build API

### 3. Install and init Google Cloud SDK
https://cloud.google.com/sdk/docs/install

### 4. Dockerfile, requirement.txt, .dockerignore
https://cloud.google.com/run/docs/quickstart/build-and-deploy#containerizing

### 5. Cloud Build & deploy (make sure in the same directory)
```
gcloud builds submit --tag gcr.io/ricek-389015/classify_fruit
gcloud run deploy --image gcr.io/ricek-389015/classify_fruit --platform managed
```
