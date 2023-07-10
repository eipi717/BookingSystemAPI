<center> <h1>Booking System API</h1> </center>
<div align="center">

[![license](https://img.shields.io/github/license/dec0dOS/amazing-github-template.svg?style=flat-square)](LICENSE)
[![made by eipi717](https://img.shields.io/badge/made%20by-eipi717-ff1414.svg?style=flat-square)](https://www.linkedin.com/in/nicholas-ho-954053216/)

</div>

## Table Of Content
  * [Table Of Content](#table-of-content)
  * [Description](#description)
  * [Installation](#installation)
    * [Prerequisite](#prerequisite)
    * [Database setup](#database-setup)
    * [Installation steps](#installation-steps)
      * [Step 1 - Clone the project](#step-1---clone-the-project)
      * [Step 2 - Open the project](#step-2---open-the-project)
      * [Step 3 - Edit `application.properties`](#step-3---edit-applicationproperties)
  * [Links](#links)

## Description
This is a project that allows users to book lessons.
It can be used for all business sizes.
List of lessons, bookings and recommended lessons will be all provided in this application.
The application provides a clean user interface for admin to manage the bookings and the lessons

## Installation

### Prerequisite
- Spring Boot 3
- JAVA 17
- MySQL 8

### Database setup
Create a database using the script below.


```mysql
create table lessons
(
    LESSON_id          bigint auto_increment
        primary key,
    LESSON_name        varchar(255) null,
    LESSON_description longtext     null,
    LESSON_start_time  varchar(255) null,
    LESSON_end_time    varchar(255) null,
    LESSON_price       double       null,
    LESSON_createts    bigint       null,
    LESSON_updatets    bigint       null
)
    engine = InnoDB;

create table user
(
    USER_id       bigint auto_increment
        primary key,
    USER_username varchar(255) null,
    USER_email    varchar(255) null,
    USER_password varchar(255) null,
    USER_createts bigint       null,
    USER_updatets bigint       null
)
    engine = InnoDB;

create table booking
(
    BOOKING_id             bigint auto_increment
        primary key,
    USER_id                bigint       null,
    LESSON_id              bigint       null,
    BOOKING_phone          varchar(255) null,
    BOOKING_createTs       bigint       null,
    BOOKING_updateTs       bigint       null,
    BOOKING_payment_status tinyint      null,
    constraint LESSON_id
        foreign key (LESSON_id) references lessons (LESSON_id),
    constraint USER_id
        foreign key (USER_id) references user (USER_id)
)
    engine = InnoDB;

create table payment
(
    PAYMENT_id       bigint auto_increment
        primary key,
    BOOKING_id       bigint null,
    PAYMENT_amount   double null,
    PAYMENT_createts bigint null,
    PAYMENT_updatets bigint null,
    constraint payment_ibfk_2
        foreign key (BOOKING_id) references booking (BOOKING_id)
)
    engine = InnoDB;

create index BOOKING_id
    on payment (BOOKING_id);

```

### Installation steps

#### Step 1 - Clone the project

Open the terminal and navigate to the directory you want.
Use this commend to get the project
```shell
git clone https://github.com/eipi717/BookingSystemAPI.git
```

#### Step 2 - Open the project
Open the project using whatever IDE you want, IntelliJ is recommended

#### Step 3 - Edit `application.properties`
1. Edit the Database details
2. Edit the path of the [Recommender script](https://github.com/eipi717/BookingSystemRecommender)

### Links
- [Demo Site](https://demo-site.booking-system.tech/)

- [My LinkedIn Profile](https://www.linkedin.com/in/nicholas-ho-954053216/)

- [My GitHub Profile](https://github.com/eipi717)

