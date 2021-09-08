This project was created based on spring boot and jwt token and using h2 as a databse but can change to any other relational databses

# Build docker image
just run this command to build corresponding image `docker build -t shopping-app .`

# Run docker image
run this command `docker run -it -p 8080:8080 shopping-app` and check the url on browser: http://localhost:8080/swagger-ui/index.html to see all implemented apis
