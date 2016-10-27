docker rm $(docker ps -a -q) -f
docker build -t user-rest . && \
docker run -p 27017:27017 -d --name mongodb mongo &&\
docker run -d -p 8080:8080 --name user-rest --link mongodb:mongo user-rest