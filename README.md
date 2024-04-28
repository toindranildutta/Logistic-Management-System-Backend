# ID-SpringBoot-JWT-MySql
## Start with the WebSecurityConfig file. Where I will declare which web services require login and which do not.
+ Simultaneously I embedded JwtAuthenticationEntryPoint and jwtRequestFilter to use

The purpose of JwtAuthenticationEntryPoint is to reject all requests that do not authenticate into the system. If a request is not logged in, a 403 error will be thrown
The purpose of jwtRequestFilter is to check whether all requests transmitted to the server are valid or not by checking the token in the header.
JwtTokenUtil is responsible for generating tokens based on the key you provide in the application.properties file
