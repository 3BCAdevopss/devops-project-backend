Devops Project – Backend

This repository contains the backend service for my DevOps project.
The backend is built using Java & Spring Boot, follows DevOps best practices, includes CI/CD pipelines, SonarCloud analysis, Dockerization, and is deployed on Render.

🌐 Live Backend API
👉 https://devops-project-backend-h8hb.onrender.com/api/products

📌 Project Overview

Backend developed using Spring Boot

RESTful APIs for frontend integration

Code quality analysis using SonarCloud

CI/CD implemented with GitHub Actions

Containerized using Docker

Deployed on Render Cloud

Integrated with frontend deployed on Vercel

🛠️ Tools & Technologies Used

Backend Framework: Spring Boot

Language: Java 17

Build Tool: Maven

API Type: RESTful APIs

Containerization: Docker

Code Quality: SonarCloud

CI/CD: GitHub Actions

Deployment Platform: Render

Version Control: Git & GitHub

⚙️ Project Setup & Build Steps
1️⃣ Clone the Repository
git clone https://github.com/3BCAdevopss/devops-project-backend.git
cd devops-project-backend

2️⃣ Build the Project Using Maven
mvn clean package


This generates the JAR file inside the target/ directory.

🐳 Docker Image Build & Run
3️⃣ Build Docker Image
docker build -t devops-project-backend .

4️⃣ Run Docker Container
docker run -p 8080:8080 devops-project-backend


Backend will be available at:

http://localhost:8080

🔄 Git Workflow & Pull Request Process

Created feature branches for backend changes

Implemented APIs and configuration updates

Committed changes with proper commit messages

Raised Pull Requests (PRs) to main

GitHub Actions pipeline triggered automatically

SonarCloud analysis executed on PR

PR merged after successful checks

🔍 SonarCloud Code Analysis

SonarCloud integrated via GitHub Actions

Analyzed:

Code smells

Bugs

Vulnerabilities

Maintainability

Quality Gate initially failed due to:

Missing coverage

Configuration updated and issues reviewed

sonar backend passed image:(Backend is named as profit margin analyzer)
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/547588a1-ea43-4422-98d0-5592e38646c0" />


🚀 Deployment on Render
Deployment Steps:

Dockerized backend application

Connected GitHub repository to Render

Configured build and start commands

Render automatically builds and deploys Docker image

Public API exposed via Render service URL

🌐 Live API Endpoint
👉 https://devops-project-backend-h8hb.onrender.com/api/products

🔗 Frontend Integration

Backend APIs consumed by React frontend

CORS configured to allow frontend access

Data fetched using REST endpoints

End-to-end integration tested successfully

⚠️ Challenges Faced & Solutions
🔴 SonarCloud Quality Gate Issues

Initial failure due to 0% test coverage

Solution:

Reviewed issues

Adjusted quality gate expectations

Documented known limitations

🔴 Docker Build Issues

JAR file not found during image build

Solution:

Ensured Maven build runs before Docker build

Corrected Dockerfile paths

🔴 Render Deployment Challenges

Application failed to start initially

Port mismatch issues

Solution:

Ensured correct port exposure

Verified Render service configuration

Rebuilt and redeployed successfully

📈 Key Learnings

Building production-ready Spring Boot APIs

Dockerizing backend applications

Implementing CI/CD pipelines for backend

Understanding cloud deployment using Render

Debugging build, container, and deployment issues
