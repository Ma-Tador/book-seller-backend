# Spring boot Book-Seller

## Endpoints

###Sign-up
POST /api/authentication/sign-up
HOST: localhost:8080
Content-Type: Application/json
{
	"name":"snapi",
	"username":"snapanu",
	"password":"password"
}

###Sign-in
POST /api/authentication/sign-in
HOST: localhost:8080
Content-Type: Application/json
{
	"username":"snapanu",
	"password":"password"
}

###Make-admin
PUT localhost:8080/api/internal/make-admin/admin
Authorization: Bearer MyInternalApiKey123!

###Create Book (only ADMIN)
POST localhost:8080/api/book
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0NDc1OTMyMywidXNlcklkIjoyLCJyb2xlcyI6IlJPTEVfQURNSU4ifQ.rA_Er6fgqmCuaLMpPMa9EUlhv_CXuH-1fX5Ijtmr1cnF96CKIqLGQTiGjq3soyRmB67u9xKmh6_Io6KQrOZ7KA
 {
	"title":"Book2 title",
	"price":20,
	"description":"some 2 book",
	"author":"Ion Creanga"
}

###Delete Book by id (only ADMIN)
POST localhost:8080/api/book/4
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0NDc1OTMyMywidXNlcklkIjoyLCJyb2xlcyI6IlJPTEVfQURNSU4ifQ.rA_Er6fgqmCuaLMpPMa9EUlhv_CXuH-1fX5Ijtmr1cnF96CKIqLGQTiGjq3soyRmB67u9xKmh6_Io6KQrOZ7KA

###Get all books (all users)
localhost:8080/api/book

###create Purchase history
POST localhost:8080/api/purchase-history
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0NDc1OTMyMywidXNlcklkIjoyLCJyb2xlcyI6IlJPTEVfQURNSU4ifQ.rA_Er6fgqmCuaLMpPMa9EUlhv_CXuH-1fX5Ijtmr1cnF96CKIqLGQTiGjq3soyRmB67u9xKmh6_Io6KQrOZ7KA
{
	"userId":2,
	"bookId":3,
	"price":10
}

###Get all purchases of the current user logged in
GET localhost:8080/api/purchase-history
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0NDc1OTMyMywidXNlcklkIjoyLCJyb2xlcyI6IlJPTEVfQURNSU4ifQ.rA_Er6fgqmCuaLMpPMa9EUlhv_CXuH-1fX5Ijtmr1cnF96CKIqLGQTiGjq3soyRmB67u9xKmh6_Io6KQrOZ7KA


##Heroku Cloud configuration
###1.Create a free account
###2.Create in Java Project Eclipse, a file called app.jso -> describe your app to be uploaded in cloud
###3.Create in Eclipse Project a file called Procfile -> select processes to be run by Heroku (ex.java -jar myproj.jar)
###4.Create in Eclipse Proj file system.properties -> give system properties details, ex.java version etc

