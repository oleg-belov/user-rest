== User rest

[![Build Status](https://travis-ci.org/SergeyPirogov/user-rest.svg?branch=master)](https://travis-ci.org/SergeyPirogov/user-rest)

Provide users notebook

Available methods

= Get all users

GET /users

= Get user by id

GET /user/{id}

= Add new user

POST user/add

```
{
  "id": "57c3f66dd4c63cf400447bda",
  "username": "ivan",
  "firstname": "ivanko",
  "lastname": null,
  "email": "ivan"
}
```

= Update user

PUT user/update/{id}

```
{
  "id": "57c3f66dd4c63cf400447bda",
  "username": "selin"
}
```

= Delete user

DELETE /user/delete/{id}
