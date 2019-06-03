# Wine Catalog Api

REST API used by the warehouse system to perform CRUD (create/read/update/delete)
operations on the bottles of wine

# Installing

Checkout project from Git repository with the following command:

git clone git@github.com:vincenzoSantonastasoGithub/wineCatalogApi.git

# Build with Gradle:

 - gradle build
 
 # Run with Gradle: 
 
 - gradle bootRun
 
# Run with Docker: 

 - docker build -t "wine_image:wine" .
 - docker run -p 5000:5000 wine_image:wine

# Run the tests

To run tests run the following command:

./gradlew test

# Heroku Environment

 Following the link of the Api deployed on heroku:

 https://winecatalogapi.herokuapp.com/

 Endpoint Swagger with documentation of each exposed endpoint:

 https://winecatalogapi.herokuapp.com/swagger-ui.html
