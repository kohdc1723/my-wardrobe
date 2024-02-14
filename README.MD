# My Wordrobe

## Project Summary

### About
**My Wardrobe** is a web application designed for users to efficiently organize and manage their clothing items.

### Key Features
- Catalog clothing items
- Search clothing items by name or brand
- Filter clothing items by seasons and collections
- Create a look by combining clothing items

### Built with
![spring]
![springboot]
![jpa-hibernate]

---

## Project Details

### File Structure
    .
    ├── src
    ├── gradle
    ├── .idea
    ├── gradlew
    ├── gradlew.bat
    ├── settings.gradle
    ├── build.gradle
    └── README.md

### Entity Relationship Diagram
![img.png](img.png)

### API Endpoints

**Users**

`POST`: `/api/users`

`GET`: `/api/users/{id}`

`PUT`: `/api/users`

`DELETE`: `/api/users/{id}`

**Clothes**

`POST`: `/api/clothes`

`GET`: `/api/clothes/{id}`

`GET`: `/api/clothes`

`PUT`: `/api/clothes`

`DELETE`: `/api/clothes/{id}`

**Collections**

`POST`: `/api/collections`

`GET`: `/api/collections/{id}`

`GET`: `/api/collections`

`PUT`: `/api/collections`

`DELETE`: `/api/collections/{id}`

**Looks**

`POST`: `/api/looks`

`GET`: `/api/looks/{id}`

`GET`: `/api/looks`

`PUT`: `/api/looks`

`DELETE`: `/api/looks/{id}`

**Keywords**

`POST`: `/api/keywords`

`GET`: `/api/keywords/{id}`

`GET`: `/api/keywords`

`PUT`: `/api/keywords`

`DELETE`: `/api/keywords/{id}`

---

[spring]: https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[springboot]: https://img.shields.io/badge/spring%20boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
[mysql]: https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white
[jpa-hibernate]: https://img.shields.io/badge/jpa%20hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white