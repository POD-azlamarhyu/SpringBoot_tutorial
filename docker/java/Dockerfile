FROM eclipse-temurin:21-jdk
ARG API_PORT
ARG WORK_DIR

WORKDIR ${WORK_DIR}
RUN apt-get update && apt-get upgrade -y
EXPOSE ${API_PORT}