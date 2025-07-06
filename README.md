# Calendar System - Server Side Application

## Overview

This is a Spring Boot server-side application that provides a RESTful API for managing calendar events. The API supports full CRUD operations for events and includes filtering capabilities to fetch events by day, week, or month. Events can be regular timed events, all-day events, or multi-day events.

## Features

### Event Management

- Create, read, update, and delete calendar events
- Support for all-day events and multi-day events
- Automatic duration calculation (in hours and minutes)

### Flexible Event Retrieval

- Get all events sorted by start time
- Filter events by specific day, week, or month
- Handle events that span multiple days

### Database Support

- Uses MySQL as the database
- Uses Spring Data JPA for ORM and database operations

## Technology Stack   

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## API Documentation

Test the API locally using Postman:  
`http://localhost:3001/events`

