# AI Email Reply Generator

An AI-powered email reply generator that creates professional, friendly, or casual email responses using Google's Gemini AI. The application consists of a React frontend and a Spring Boot backend, providing a simple and responsive interface for generating email replies.

---

## Features

* Generate AI-powered email replies
* Choose different reply tones (Professional, Friendly, Casual, etc.)
* Copy generated replies with one click
* Responsive user interface built with React
* REST API built with Spring Boot
* Secure API key management using environment variables
* Dockerized backend for easy deployment

---

## Tech Stack

### Frontend

* React
* Vite
* Material UI
* Axios

### Backend

* Java 21
* Spring Boot
* Spring Web
* RestTemplate
* Maven

### AI

* Google Gemini API

### Deployment

* Frontend: Vercel
* Backend: Render (Docker)

### Version Control

* Git
* GitHub

---

## Project Structure

```text
AI_Email_Reply_Generator
│
├── email-reply-generator
│   ├── src
│   ├── Dockerfile
│   ├── pom.xml
│   └── ...
│
└── email-reply-react
    ├── src
    ├── package.json
    └── ...
```

---

## Application Architecture

```text
React (Vercel)
       │
       ▼
Spring Boot REST API
       │
       ▼
Google Gemini API
       │
       ▼
Generated Email Reply
```

---

## API Endpoint

### Generate Email Reply

**POST**

```
/api/email/generate
```

### Request Body

```json
{
  "emailContent": "Thank you for attending the meeting.",
  "tone": "Professional"
}
```

### Response

```text
Dear Sir,

Thank you for your email...

Best Regards,
```

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/krish8778/AI_Email_Reply_Generator.git
```

---

## Backend Setup

Navigate to the backend folder:

```bash
cd email-reply-generator
```

Create an `application.properties` file (or configure environment variables):

```properties
gemini.api.key=${GEMINI_API_KEY}
gemini.api.url=${GEMINI_API_URL}
```

Run the backend:

```bash
mvn spring-boot:run
```

---

## Frontend Setup

Navigate to the frontend folder:

```bash
cd email-reply-react
```

Install dependencies:

```bash
npm install
```

Create a `.env` file:

```env
VITE_API_URL=http://localhost:8080
```

Run the frontend:

```bash
npm run dev
```

---

## Docker

Build the Docker image:

```bash
docker build -t email-reply-generator .
```

Run the container:

```bash
docker run -p 8080:8080 \
-e GEMINI_API_KEY=YOUR_API_KEY \
-e GEMINI_API_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent \
email-reply-generator
```

---

## Environment Variables

### Backend

| Variable       | Description                     |
| -------------- | ------------------------------- |
| GEMINI_API_KEY | Google Gemini API Key           |
| GEMINI_API_URL | Gemini Generate Content API URL |

### Frontend

| Variable     | Description          |
| ------------ | -------------------- |
| VITE_API_URL | Backend API Base URL |

---

## Author

**Muthu Krishnan B**

GitHub: https://github.com/krish8778

LinkedIn: https://www.linkedin.com/in/krish8778


