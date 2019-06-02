# Wine Catalog Api

REST API used by the warehouse system to perform create/read/update/delete (CRUD)
operations on the bottles of wine

### Installing

Checkout project from Git repository with the following command:

git clone git@github.com:vincenzoSantonastasoGithub/wineCatalogApi.git

# Build/Start with Gradle:

 - gradle build
 - gradle bootRun
 
# Build/Start with Docker: 

 - docker build -t "wine_container:docker" .
 - docker build -t "wine_container:docker" .


### Run the tests

To run tests run the following command:

./gradlew test

### Heroku Environment

 Following the link of the Api deployed on heroku:

 https://winecatalogapi.herokuapp.com/

 Endpoint Swagger with documentation of each exposed endpoint:

 https://winecatalogapi.herokuapp.com/swagger-ui.html
