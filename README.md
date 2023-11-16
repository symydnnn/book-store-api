# BOOK STORE API
 Online Bookstore RESTful API 
 <br>
 *By* ***ŞEYMA AYDIN***  
 <br>

- Design and implement a RESTful API for an online bookstore using Java and Spring Boot. 
- This API should provide essential functionality for both the user and the bookstore admin.
- Two types of user information can be obtained. These consist of 'ADMIN' and 'USER' privileges. Users performing book-related operations are expected to have 'ADMIN' authority.
- When getting the book list or book, it will be sufficient to have admin and user read privileges.
- Books can be ordered with the logged in user information.

## Technologies & Tools

- Java 17
- Maven
- Spring Boot 3.1.5
- Spring Security
- Spring Data JPA
- Lombok
- MySQL

## API List

### USERS
    
- Sign Up A User
  <br>
  **POST** : /api/v1/users/signup

```
{
  "id": 1,
  "name": "Şeyma Aydın",
  "email": "seyma@gmail.com",
  "password": "1111",
  "role": "ADMIN",
  "createdAt": "2023-11-16T10:18:43.217Z",
  "updatedAt": "2023-11-16T10:18:43.217Z"
}       
```
    
- Login A User
  <br>
  **POST**  : /api/v1/users/login

***Request*** 
```
{
  "email": "seyma@gmail.com",
  "password": "123qwe"
}
```
***Response*** 
```
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZXltYUBnbWFpbC5jb20iLCJpYXQiOjE3MDAxMjc0MzYsImV4cCI6MTcwMDEyOTIzNn0.H94sTj9XJc06LbbRbk0RpuGdg7VrD_fOgw2ANcSJ9ms",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZXltYUBnbWFpbC5jb20iLCJpYXQiOjE3MDAxMjc0MzYsImV4cCI6MTcwMDEyOTIzNn0.H94sTj9XJc06LbbRbk0RpuGdg7VrD_fOgw2ANcSJ9ms"
}     
```


### BOOKS

- Get All Books By Pagination
  <br>
    **GET** : - /api/v1/books

***Request***
Request parameteres: 
  - int page
  - int limit

***Response***
```
{
  "content": [
    {
      "title": "İnsanlığımı Yitirirken",
      "author": "Osamu Dazai",
      "price": 58,
      "stockQuantity": 13,
      "createdAt": "2023-11-16T08:38:16.789+00:00",
      "updatedAt": "2023-11-16T10:36:21.761+00:00",
      "isbn": "9783410016809"
    },
    {
      "title": "Zeytin Dağı",
      "author": "Fatih Rıfkı Atay",
      "price": 50,
      "stockQuantity": 13,
      "createdAt": "2023-11-16T08:37:09.329+00:00",
      "updatedAt": "2023-11-16T10:37:19.741+00:00",
      "isbn": "9786933780950"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 2,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "last": false,
  "totalElements": 6,
  "totalPages": 3,
  "size": 2,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```
    
- Get Book By ISBN
  <br>
  **GET**  : /api/v1/books/{isbn}

- Save a Book
  <br>
  **POST** : /api/v1/books
    
- Update a Book
  <br>
  **PUT**  : /api/v1/books{isbn}
    
- Delete a Book By ISBN
  <br>
  **DEL**  : /api/v1/books/{isbn}

***Generic Response*** 
```
{
      "isbn": "978-859546-84512655495"
      "title": "Taht Oyunları",
      "author": "George R. R. Martin",
      "price": 175,
      "stockQuantity": 5,
      "createdAt": "2023-11-16T04:15:18.893+00:00",
      "updatedAt": "2023-11-16T09:14:01.116+00:00"
}  
```

### ORDERS
    
- Get All Orders By User ID
  <br>
  **GET**  : /api/v1/orders/{userId} 

```
[
  {
    "id": 14,
    "user": {
      "id": 1,
      "name": "Şeyma",
      "email": "seyma@gmail.com",
      "password": null,
      "role": "ADMIN",
      "createdAt": "2023-11-16T01:00:35.130+00:00",
      "updatedAt": null
    },
    "listOfBooks": [
      {
        "title": "Taht Oyunları",
        "author": "George R. R. Martin",
        "price": 175,
        "stockQuantity": 0,
        "createdAt": null,
        "updatedAt": null,
        "isbn": "978-859546-84512655495"
      },
      {
        "title": "Anne Karerina",
        "author": "Lev Tolstoy",
        "price": 215,
        "stockQuantity": 0,
        "createdAt": null,
        "updatedAt": null,
        "isbn": "978-859546-8956388454"
      }
    ],
    "totalPrice": 390,
    "orderDate": "2023-11-16T09:11:48.910+00:00",
    "createdAt": "2023-11-16T09:11:48.940+00:00",
    "updatedAt": "2023-11-16T09:11:49.449+00:00"
  },
  {
    "id": 13,
    "user": {
      "id": 1,
      "name": "Şeyma",
      "email": "seyma@gmail.com",
      "password": null,
      "role": "ADMIN",
      "createdAt": "2023-11-16T01:00:35.130+00:00",
      "updatedAt": null
    },
    "listOfBooks": [
      {
        "title": "Taht Oyunları",
        "author": "George R. R. Martin",
        "price": 175,
        "stockQuantity": 0,
        "createdAt": null,
        "updatedAt": null,
        "isbn": "978-859546-84512655495"
      },
      {
        "title": "Anne Karerina",
        "author": "Lev Tolstoy",
        "price": 215,
        "stockQuantity": 0,
        "createdAt": null,
        "updatedAt": null,
        "isbn": "978-859546-8956388454"
      }
    ],
    "totalPrice": 390,
    "orderDate": "2023-11-16T09:09:29.941+00:00",
    "createdAt": "2023-11-16T09:09:29.974+00:00",
    "updatedAt": "2023-11-16T09:09:30.472+00:00"
  },
  {
    "id": 9,
    "user": {
      "id": 1,
      "name": "Şeyma",
      "email": "seyma@gmail.com",
      "password": null,
      "role": "ADMIN",
      "createdAt": "2023-11-16T01:00:35.130+00:00",
      "updatedAt": null
    },
    "listOfBooks": [
      {
        "title": "Taht Oyunları",
        "author": "George R. R. Martin",
        "price": 175,
        "stockQuantity": 0,
        "createdAt": null,
        "updatedAt": null,
        "isbn": "978-859546-84512655495"
      },
      {
        "title": "Anne Karerina",
        "author": "Lev Tolstoy",
        "price": 215,
        "stockQuantity": 0,
        "createdAt": null,
        "updatedAt": null,
        "isbn": "978-859546-8956388454"
      }
    ],
    "totalPrice": 375,
    "orderDate": "2023-11-16T07:47:54.735+00:00",
    "createdAt": "2023-11-16T07:47:54.746+00:00",
    "updatedAt": "2023-11-16T07:47:54.864+00:00"
  }
]     
```
    
- Get Order By Order ID
  <br>
   **GET** : /api/v1/orders/details/{orderId}

***Response*** 
```
{
  "id": 14,
  "user": {
    "id": 1,
    "name": "Şeyma",
    "email": "seyma@gmail.com",
    "password": null,
    "role": "ADMIN",
    "createdAt": "2023-11-16T01:00:35.130+00:00",
    "updatedAt": null
  },
  "listOfBooks": [
    {
      "title": "Taht Oyunları",
      "author": "George R. R. Martin",
      "price": 175,
      "isbn": "978-859546-84512655495"
    },
    {
      "title": "Anne Karerina",
      "author": "Lev Tolstoy",
      "price": 215,
      "isbn": "978-859546-8956388454"
    }
  ],
  "totalPrice": 390,
  "orderDate": "2023-11-16T09:11:48.910+00:00",
  "createdAt": "2023-11-16T09:11:48.940+00:00",
  "updatedAt": "2023-11-16T09:11:49.449+00:00"
}    
```

- Save An Order
  <br>
  **POST** : /api/v1/orders/details/{orderId}

***Request*** 
```
[
  "9786933780950"
]
```


***Response*** 
```
{
  "id": 19,
  "user": {
    "id": 1,
    "name": "Şeyma",
    "email": "seyma@gmail.com",
    "password": null,
    "role": "ADMIN",
    "createdAt": "2023-11-16T01:00:35.130+00:00",
    "updatedAt": null
  },
  "listOfBooks": [
    {
      "title": "Zeytin Dağı",
      "author": "Fatih Rıfkı Atay",
      "price": 50,
      "stockQuantity": 13,
      "createdAt": "2023-11-16T08:37:09.329+00:00",
      "updatedAt": "2023-11-16T10:34:37.696+00:00",
      "isbn": "9786933780950"
    }
  ],
  "totalPrice": 50,
  "orderDate": "2023-11-16T10:37:19.723+00:00",
  "createdAt": "2023-11-16T10:37:19.724+00:00",
  "updatedAt": null
}
```

## ERROR HANDLING

#### ERROR RESPONSE
 {
  "errorCode": "string",
  "description": "string"
}

#### EXCEPTIONS

**400 (BAD_REQUEST)**
- UserAlreadyExistsException
- BookISBNAlreadyExistsException
- InsufficientStockException
- MinimumPriceForOrderException
- WrongRequestParametersException
- 
**401 (UNAUTHORIZED)**
- UnAuthorizedException
- 
**404 (NOT_FOUND)**
- UserNotFoundException
- BookNotFoundException
  

#### ERROR CODES AND DESCRPITIONS
- BOOK_NOT_FOUND("The Book You Are Looking For Could Not Be Found."),
- INSUFFICIENT_STOCK("The Book Is Not In Stock."),
- MINIMUM_PRICE_FOR_ORDER("Your Order Must Be Worth At Least 25 Cents."),
- BOOK_ISBN_ALREADY_EXISTS("There is a book as same ISBN."),
- SYSTEM_ERROR("A System Error Has Occurred."),
- UNAUTHORIZED_USER("The User Is Unauthorized."),
- USER_ALREADY_EXISTS("There is a user with the same information."),
- USER_NOT_FOUND("There is not a user."),
- WRONG_REQUEST_PARAMETERS("Missing request.");
  

## SWAGGER USING
![image](https://github.com/symydnnn/book-store-api/assets/73581330/e8ed53c5-3717-413d-8090-5c4b3713e8c5)


