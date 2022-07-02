<div id="top"></div>

[![LinkedIn][linkedin-shield]][linkedin-url]



<br />
<div align="center">
  <a href="https://github.com/gutierrezMartinIvan/Alkemy-challenge-backend-springboot-Disney-lab">
    <img src="https://cdn.discordapp.com/attachments/992700792660295740/992700799769640970/unknown.png" alt="Logo" width="150" height="80">
  </a>

  <h3 align="center">CHALLENGE BACKEND - Java Spring Boot (API)</h3>
  
  <p align="center">
    API developed using a REST patron and Spring Security.
    <br />
    <a href="https://github.com/gutierrezMartinIvan/Alkemy-challenge-backend-springboot-Disney-lab"><strong>Explore the docs »</strong></a>
    <br />
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

Welcome to the Alkemy Spring Boot challenge backend, in this challenge I have to create an API REST with a REST patron, using the library Spring Security to       manage the authentication for the user and their roles.
Also JUnit, Mockito and Swagger were implemeted for testing and documentation.

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Maven](https://maven.apache.org)
* [Swagger-ui](https://swagger.io/tools/swagger-ui/)
* [Mockito](https://site.mockito.org)
* [JUnit](https://junit.org/junit5/)
* [MapStruct](https://mapstruct.org)
* [Lombok](https://projectlombok.org)
* [JWT.IO](https://jwt.io)
* [Sendgrid](https://sendgrid.com)
* [PostgreSQL](https://www.postgresql.org)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

Please follow the next steps for the correct configuration of the API.

### Prerequisites

Create a new empty basedata and set it into the application properties of the API.

### Instructions

1.Run the API and create the three following roles FROM the database:
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

2.Once you've created the three roles, you must create a new user to be able to use the services (Look in AuthController for being able to create a new user).

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

To access to the documentation please go to: "http://localhost:"port"/swagger-ui/index.html". Replace the port for the port given for the API, it's usually 8080.

NOTE: YOU DON'T NEED TO BE LOGGED IN TO ACCESS TO THE DOCUMENTATION.

Example of how to register a new user:

![image](https://user-images.githubusercontent.com/83437892/176992971-13d0a5d9-f388-4862-b768-f642e7dec7ef.png)

"role" can be "admin", "mod" or "user"

Admin role has full control of the service.
Moderator role can save or update but it hasn't let delete anything from the service.
User role only can use get services.

Example of how to log in:

![image](https://user-images.githubusercontent.com/83437892/176992986-73b3dc83-4369-474e-8093-5089ccbb2bec.png)

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [x] Add Entity
- [x] Add DTO
- [x] Add Service
- [x] Add Repository
- [x] Add Mapper
- [x] Add Exception
- [x] Add Security
- [x] Add Email Service
- [x] Users And Roles
    - [x] Add User
    - [x] Add Role

See the [open issues](https://github.com/gutierrezMartinIvan/Alkemy-challenge-backend-springboot-Disney-lab/issues) for a full list of known issues.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Martin Gutierrez - martGutierrez98@outlook.com

Project Link: [CHALLENGE BACKEND - Java Spring Boot (API)](https://github.com/gutierrezMartinIvan/Alkemy-challenge-backend-springboot-Disney-lab)

<p align="right">(<a href="#top">back to top</a>)</p>


[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/martin-gutierrez-024610208/
