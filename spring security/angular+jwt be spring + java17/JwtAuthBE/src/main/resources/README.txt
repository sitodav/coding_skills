POST : http://localhost:8082/jwtauthbe/auth/login
PAYLOAD (JSON) with username /password generated in console at application startup
{
    "username" : "sitodskij36.583192463390105",
    "password" : "sitodskij71.15605453621974"
}


responds with JWT TOKEN

-----------------------------------------------------

GET http://localhost:8082/jwtauthbe/courses
HEADER : AUTHORIZATION -> Bearer [JWT returned from login endpoint]


GET http://localhost:8082/jwtauthbe/users
HEADER : AUTHORIZATION -> Bearer [JWT returned from login endpoint]

GET http://localhost:8082/jwtauthbe/users/{userid}
HEADER : AUTHORIZATION -> Bearer [JWT returned from login endpoint]