Backend Application – DevOps Project

This repository contains the backend application for a full-stack DevOps project.
The backend is built using Spring Boot, containerized with Docker, analyzed using SonarCloud, automated via GitHub Actions, and designed to integrate with a frontend deployed on Vercel.

##Project Overview

Backend developed using Spring Boot (Java)

RESTful APIs for frontend communication

Dockerized using multi-stage Dockerfile

CI/CD implemented with GitHub Actions

Static code analysis with SonarCloud

Designed for deployment on VM / EC2 / Docker platform

##Tech Stack

Backend Framework: Spring Boot (Java)

Build Tool: Maven

Containerization: Docker, Docker Compose

CI/CD: GitHub Actions

Code Quality: SonarCloud



##Configuration

Application Port

Default backend port:

8080

##CORS Configuration

CORS is enabled to allow requests from frontend deployed on Vercel.

Example:

@CrossOrigin(origins = "*")
@RestController

##Run Locally (Without Docker)

Prerequisites

Java 17+

Maven

##Steps

mvn clean install

mvn spring-boot:run


Backend will be available at:
http://localhost:8080

##Run Using Docker

Build Docker Image

docker build -t devops-backend .

Run Container

docker run -d -p 8080:8080 devops-backend


Access API:

http://localhost:8080

##Run with Docker Compose

When running frontend and backend together:

docker compose up --build


Services:

Backend → http://localhost:8080

Frontend → http://localhost:3000

Frontend communicates with backend using Docker service name:

http://backend:8080

##CI/CD Pipeline

Implemented using GitHub Actions

Triggered on every push to main

Pipeline stages:

Code checkout

Build using Maven

Docker image build

Push image to Docker Hub

SonarCloud analysis
