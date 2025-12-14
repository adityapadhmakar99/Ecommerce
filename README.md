# E-commerce Application

This is a Java-based E-commerce application that implements several design patterns to ensure clean, maintainable, and extensible code.

## Design Patterns Used

### 1. Strategy Pattern
- **Location**: `com.ap.payment` package
- **Purpose**: Encapsulates different payment algorithms (Credit Card, COD) and makes them interchangeable
- **Key Components**:
  - `PaymentStrategy` interface
  - `CardPayment` and `CODPayment` concrete implementations
- **Usage**: The `PaymentService` uses the appropriate payment strategy based on user selection

### 2. Factory Method Pattern
- **Location**: `PaymentFactory` class
- **Purpose**: Creates payment strategy instances without exposing the instantiation logic
- **Key Methods**:
  - `PaymentFactory.get(PaymentMode mode)` - Returns the appropriate payment strategy

### 3. Repository Pattern
- **Location**: `com.ap.repo` package
- **Purpose**: Abstracts data access layer from business logic
- **Key Repositories**:
  - `PaymentRepo`
  - `OrderRepo`
  - `UserRepo`
  - `ProductRepo`
  - `CartRepo`
  - `InventoryRepo`
  - `SellerRepo`

### 4. Service Layer Pattern
- **Location**: `com.ap.service` package
- **Purpose**: Contains business logic and coordinates between controllers and repositories
- **Key Services**:
  - `PaymentService` - Handles payment processing
  - `OrderService` - Manages order processing
  - `UserService` - Handles user-related operations
  - `ProductService` - Manages product catalog
  - `CartService` - Handles shopping cart operations
  - `InventoryService` - Manages product inventory

### 5. Entity Pattern
- **Location**: `com.ap.entity` package
- **Purpose**: Represents the domain model and business entities
- **Key Entities**:
  - `User` - Represents application users
  - `Product` - Represents products in the catalog
  - `Order` - Represents customer orders
  - `Payment` - Represents payment transactions
  - `Cart` - Represents user's shopping cart
  - `Inventory` - Tracks product inventory
  - `Seller` - Represents product sellers

## Project Structure

```
src/main/java/com/ap/
├── constants/       # Enums and constants
├── entity/          # Domain model classes
├── payment/         # Payment strategy implementations
├── repo/            # Data access layer
└── service/         # Business logic layer
```

## Key Features

1. **Flexible Payment Processing**
   - Multiple payment methods (Credit Card, COD)
   - Easy to add new payment methods

2. **Order Management**
   - Order creation and tracking
   - Order status updates

3. **Inventory Management**
   - Product stock tracking
   - Low stock notifications

4. **User Management**
   - User registration and authentication
   - Order history

## Getting Started

1. Clone the repository
2. Build with Maven: `mvn clean install`
3. Run the application

## Dependencies

- Java 11 or higher
- Maven 3.6 or higher

## Design Decisions

1. **Separation of Concerns**: Clear separation between data access, business logic, and presentation layers
2. **Extensibility**: Easy to add new payment methods or business rules
3. **Testability**: Dependencies are injected, making the code easy to test
4. **Maintainability**: Follows SOLID principles and clean code practices
