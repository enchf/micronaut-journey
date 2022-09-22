# Micronaut Journey

## Objective

Show what Micronaut can provide to develop and integrate multiple microservices.
In this case, an API Gateway will be created combining two services.

## Use Case

A catalog of products is developed internally in a company and maintained separately from the inventory.
The inventory manages prices and availability. For public, these two APIs are combined in a single API.

## Requisites

* Install Micronaut API
* Install Docker or Colima to use Docker CLI
* Install Maven

## Steps

* Create the Catalog API.
* Create the Inventory API.
* Add database persistence to both APIs.
* Create a communication channel to synchronize both APIs data.
* Combine the APIs into a single public one with an API Gateway.

### Create the Catalog API

Option 1: Use [Micronaut Launch](https://micronaut.io/launch).
Option 2: Install and use [Micronaut CLI](https://micronaut.io/download/).
