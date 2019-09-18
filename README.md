# Elypia API [![Discord][discord-members]][discord] [![Internationalization][i18n-badge]][i18n] [![GitLab Build][gitlab-build]][gitlab] [![GitLab Coverage][gitlab-coverage]][gitlab]
## About
API for [Elypia][elypia], this is the core API that most of our services are interfacing with in order
to get data to display such as news, authentication, or users. 

## Requirements
* Java 11
* [MySQL 5.7][mysql]

## Running
Please visit the Wiki for extra information regarding setup!

### Running in Production with [Docker][docker]
When running the backend in production you should build the project and use the [Dockerfile][dockerfile]
to build the image in order to deploy it, the image can use an external `application-{}.yml` files
however it defaults to using the internal `application-prod.yml` file in the classpath which
externalizes the main configurable properties the Elypia API cares about to environment variables.  
> Get more information on Spring configuration profiles [here][spring-profiles].

#### Environment Variables
| Environment Variable             | Default   | Description                                                           |
|----------------------------------|-----------|-----------------------------------------------------------------------|
| DATABASE_IP                      | 127.0.0.1 | Host that runs an MySQL instance.                                     |
| DATABASE_PORT                    | 3306      | Port the server is running on.                                        |
| DATABASE_NAME                    | elypia    | Name of the database to connect to.                                   |
| DATABASE_TRUST_KEYSTORE          |           | Keystore with certificate for MySQL instance to authenticate against. |
| DATABASE_TRUST_KEYSTORE_PASSWORD |           | The password for this trust certificate keystore.                     |
| DATABASE_CLIENT_KEYSTORE         |           | Keystore with key to authenticate this application to the MySQL.      |
| DATABASE_CLIENT_PASSWORD         |           | Password for this client certificate keystore.                        |
| DATABASE_USERNAME                | elypia    | Database user to authenticate as.                                     |
| DATABASE_PASSWORD                |           | Password for the authenticating user.                                 |
| RECAPTCHA_SITE_KEY               |           | Site key to make requests to the Google Recaptcha API.                |
| RECAPTCHA_SECRET_KEY             |           | Secret key to make requests to the Google Recaptcha API.              |
> Further configuration can be performed by looking at the Spring [documentation][spring-config]
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
This is open-source under the [GNU Affero General Public License][agpl]!  
While not legal advice, you can find a [TL;DR][agpl-tldr] that sums up what
you can and can't do and any requirements if you want to use or derive work from this project!  

[discord]: https://discord.gg/hprGMaM "Discord Invite"
[discord-members]: https://discordapp.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[i18n]: https://i18n.elypia.org/engage/elypia-backend/?utm "Weblate Translations"
[i18n-badge]: https://i18n.elypia.org/widgets/elypia-backend/-/svg-badge.svg "Weblate Translation Badge"
[gitlab]: https://gitlab.com/Elypia/elypia-api/commits/master "Repository on GitLab"
[gitlab-build]: https://gitlab.com/Elypia/elypia-api/badges/master/pipeline.svg "GitLab Build Shield"
[gitlab-coverage]: https://gitlab.com/Elypia/elypia-api/badges/master/coverage.svg "GitLab Coverage Shield"
[elypia]: https://elypia.org/ "Elypia Homepage"
[mysql]: https://www.mysql.com "MySQL Database Server"
[spring-config]: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html "Spring Externalized Configuration"
[spring-profiles]: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html "Spring Configuration Profiles"
[docker]: https://www.docker.com "Docker"
[dockerfile]: https://docs.docker.com/engine/reference/builder/ "Dockerfile Reference"
[agpl]: https://www.gnu.org/licenses/agpl-3.0.en.html "AGPL"
[agpl-tldr]: https://tldrlegal.com/license/gnu-affero-general-public-license-v3-(agpl-3.0) "TLDR of AGPL"
