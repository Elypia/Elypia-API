# Elypia API [![Matrix]][matrix-community] [![Discord]][discord-guild] [![i18n]][i18n-badge] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
## About
API for [Elypia], this is the core API that most of our services are interfacing with in order
to get data to display such as news, authentication, or users. 

## Requirements
* Java 11
* [MySQL 5.7]

## Running
Please visit the Wiki for extra information regarding setup!

### Running in Production with [Docker]
When running the backend in production you should build the project and use the [Dockerfile]
to build the image in order to deploy it, the image can use an external `application-{}.yml` files
however it defaults to using the internal `application-prod.yml` file in the classpath which
externalizes the main configurable properties the Elypia API cares about to environment variables.  
> Get more information on Spring configuration profiles [here].

#### Environment Variables
| Environment Variable             | Default   | Description                                                           |
|----------------------------------|-----------|-----------------------------------------------------------------------|
| DATABASE_URL                     | 127.0.0.1 | Host that runs an MySQL instance.                                     |
| DATABASE_USERNAME                | elypia    | Database user to authenticate as.                                     |
| DATABASE_PASSWORD                |           | Password for the authenticating user.                                 |
| RECAPTCHA_SITE_KEY               |           | Site key to make requests to the Google Recaptcha API.                |
| RECAPTCHA_SECRET_KEY             |           | Secret key to make requests to the Google Recaptcha API.              |
> Further configuration can be performed by looking at the Spring [documentation]
> including setting the `SPRING_PROFILES_ACTIVE` variable to change the profile from `prod`.

### Running in Development
The Elypia backend is expecting additional settings be configured, this could be
as your own Spring profile (`application-{}.yml`) or through any other means Spring supports.
The default `application-prod.yml` isn't populated with any real values and only expected maps
environment variables on the system to Spring configuration, so you'll be expected to make your own 
profile, and then set the profile to the one you just created.  

The following settings are recommeneded to be set to simulate the production environment:

#### Spring Configuration Profile
| Setting                     | Notes    |
|-----------------------------|----------|
| spring.datasource.url       | With SSL |
| spring.datasource.username  |          |
| spring.datasource.password  |          |
| google.recaptcha.site-key   |          |
| google.recaptcha.secret-key |          |

## Open-Source
This is open-source under the [Apache 2.0]!  
While not legal advice, you can find a [TL;DR] that sums up what
you can and can't do and any requirements if you want to use or derive work from this project!  

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.gg/hprGMaM "Discord Invite"
[i18n-badge]: https://i18n.elypia.org/engage/elypia-api/?utm "Weblate Translations"
[gitlab]: https://gitlab.com/Elypia/elypia-api/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[Elypia]: https://elypia.org/ "Elypia Homepage"
[MySQL 5.7]: https://www.mysql.com "MySQL Database Server"
[documentation]: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html "Spring Externalized Configuration"
[here]: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html "Spring Configuration Profiles"
[Docker]: https://www.docker.com "Docker"
[Dockerfile]: https://docs.docker.com/engine/reference/builder/ "Dockerfile Reference"
[Apache 2.0]: https://www.apache.org/licenses/LICENSE-2.0 "Apache 2.0 License"
[TL;DR]: https://tldrlegal.com/license/apache-license-2.0-(apache-2.0) "TL;DR of Apache 2.0"

[Matrix]: https://img.shields.io/matrix/elypia:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[i18n]: https://i18n.elypia.org/widgets/elypia-api/-/svg-badge.svg "Weblate Translation Badge"
[Build]: https://gitlab.com/Elypia/elypia-api/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/elypia-api/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/elypia-donate-blueviolet "Donate Shield"
