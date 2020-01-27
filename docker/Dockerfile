FROM ubuntu:latest

RUN apt-get update -y && apt-get upgrade -y
RUN apt-get install -y default-jre
RUN apt-get install -y postgresql
RUN apt-get install -y sudo
RUN apt-get update -y && apt-get upgrade -y
RUN service postgresql start && sudo -u postgres psql --command "alter user postgres with password 'password';"
COPY mogger-20200125.jar app.jar
ENTRYPOINT service postgresql start && java -jar app.jar
