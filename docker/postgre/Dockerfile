FROM postgres:15.7-bullseye

ARG DB_PORT

ENV LANGUAGE ja_JP:ja
ENV TZ JST-9
ENV TERM xterm
ENV TZ Asia/Tokyo
RUN apt-get update && apt-get install -y
RUN mkdir -p /psql/data/

WORKDIR /psql/data/
EXPOSE ${DB_PORT}