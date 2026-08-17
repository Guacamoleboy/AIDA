# -------------------------------------------------------------------------------------------------------

# PRODUCTION Dockerfile

# Version 1.0.0
# Created by Guacamoleboy
# Last updated: 17/08-2026

# FILE PURPOSE
#   - Multi-stage Dockerfile for AIDA School Project
#   - Stage 1: Builds the React app using Node
#   - Stage 2: Serves the static build files via nginx

# -------------------------------------------------------------------------------------------------------

# ----- STAGE 1 -----------------------------------------------------------------------------------------

FROM node:22-alpine AS build

WORKDIR /app

COPY frontend/package*.json ./

RUN npm install

COPY frontend/ ./

RUN npm run build

# ----- STAGE 2 -----------------------------------------------------------------------------------------

FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]