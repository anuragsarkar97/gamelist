# gamelist

A game asset marketplace to buy and sell assets 

Assumptions - 

1. No authentication/authorization 
2. Only buy side no sell side 


Preequisite 

- Java 17 
- Docker 
- Gradle (Optional)
- Make

Dependencies 

- Spring boot 
- Postgres 
- SpringFox

How to run 

```
make build 
make docker 
docker compose up (this will startup db + app in an isolated env) 
```

APIS
```
POST /user        Create a new user + wallet 
```
```
{
    "user" : {
        "email" : "anuragsarkar250@gmail.com",
        "name" : "anurag sarkar"
    }, 
    "balance" : 400.0
}
```
```
DELETE /user/:userId      Delete the user and deactivate the wallet
```

```
POST /purchase    Create a new purchase order (remove asset from listing and reduce wallet balance) 
```
```
{
    "userId" : "37dde5ae-dd5f-4aa2-bcd9-553c43c69ad8",
    "inventoryId" : "326d6309-ac3f-4958-95aa-da7d23ea0711"
}
```
```
GET /purchase     Get purchase history of a user 
```
```
GET /listings     Get all asset available for purchase. 
```
```
{
    "data": [
        {
            "id": "4b0f9446-d1f3-45c2-a256-4cc54de649f5",
            "name": " Colt M4A1",
            "price": 125.0,
            "status": "SALE",
            "type": "SUPAKI-EXTERNAL",
            "externalId": "2"
        }
    ],
    "exception": null
}

```


File Structure 
```
|-- Dockerfile
|-- HELP.md
|-- Makefile
|-- build.gradle
|-- docker-compose.yml
|-- gradle
|   `-- wrapper
|       |-- gradle-wrapper.jar
|       `-- gradle-wrapper.properties
|-- gradlew
|-- gradlew.bat
|-- settings.gradle
`-- src
    |-- main
    |   |-- java
    |   |   `-- com
    |   |       `-- cod
    |   |           `-- gamelist
    |   |               |-- Application.java
    |   |               |-- DataLoader.java
    |   |               |-- config
    |   |               |   `-- SpringFoxConfig.java
    |   |               |-- controller
    |   |               |   |-- InventoryController.java
    |   |               |   |-- OrderController.java
    |   |               |   `-- UserController.java
    |   |               |-- exception
    |   |               |   |-- SQLException.java
    |   |               |   `-- UserPresentException.java
    |   |               |-- model
    |   |               |   |-- CreateUserRequest.java
    |   |               |   |-- Inventory.java
    |   |               |   |-- Order.java
    |   |               |   |-- PurchaseRequest.java
    |   |               |   |-- ResponseModel.java
    |   |               |   |-- SupakiResult.java
    |   |               |   |-- User.java
    |   |               |   `-- Wallet.java
    |   |               |-- repository
    |   |               |   |-- InventoryRepository.java
    |   |               |   |-- OrderRepository.java
    |   |               |   |-- UserRepository.java
    |   |               |   `-- WalletRepository.java
    |   |               |-- service
    |   |               |   |-- InventoryService.java
    |   |               |   |-- OrderService.java
    |   |               |   `-- UserService.java
    |   |               `-- util
    |   `-- resources
    |       |-- application.properties
    |       |-- static
    |       `-- templates
    `-- test
        `-- java
            `-- com
                `-- cod
                    `-- gamelist
                        |-- GamelistApplicationTests.java
                        `-- benchmark

24 directories, 35 files
```


