# Object-Oriented Programming - Final Project

This project was developed in **Java** as the final assignment for the **Object-Oriented Programming (OOP)** subject.  
The system allows the **management and execution of trials** and their respective editions, with different data persistence mechanisms.

---

## 📌 Main Features

- **Flexible persistence**: at the start of the program, the user chooses how to store data:
  - **CSV** files
  - **JSON** files

- **Management menu**:
  - Create, list, and delete **trials**
  - Create, list, duplicate, and delete **editions**
  - Restriction: a trial cannot be deleted if it is linked to an edition

- **Execution menu**:
  - Runs the edition corresponding to the current year
  - Players participating in each trial are entered by the user
  - Simulation outcomes are based on probabilities:
    - Gain or lose experience points (**PI**)
    - Rank up: *Engineer → Master → Doctor*
  - End conditions:
    - A player is **disqualified** if their PI reaches 0
    - A player **wins** if they complete all trials with more than 0 PI

---

## 🏗️ Architecture

The project follows the **Layered Architecture** model, divided into **three layers**, fully aligned with OOP design principles:

### 1. Presentation Layer

- Composed of:
  - `Menu` (console-based UI)
  - Three controllers:
    - `Controller` (main, orchestrates program execution)
    - `ConductorController` (execution and main management)
    - `CompositorController` (auxiliary management)
- All controllers interact directly with `Menu` to communicate with the user.

### 2. Persistence Layer

- Designed with **read/write interfaces**, enabling multiple persistence strategies (**CSV** and **JSON**).
- Each entity type has its own persistence interface.
- **Trials** are stored in **4 separate files** depending on their subtype:
  - `PublicArticle` (publication of an article)
  - `StudyMaster` (master’s study)
  - `PhDefense` (doctoral thesis defense)
  - `BudgetRequest` (budget request)

### 3. Business Layer

- Managed by:
  - `ConductorController` → associated with `EditionManager`, `ExecutionManager`, and `TrialManager`
  - `CompositorController` → associated with `EditionManager` and `TrialManager`
- Managers handle lists of their respective entities.
- Abstract class **`Trial`** with 4 subclasses (`PublicArticle`, `StudyMaster`, `PhDefense`, `BudgetRequest`) demonstrates inheritance and polymorphism.
- **`Work`** class is used during trial execution:
  - Initializes an array of **threads** (one per player), showcasing concurrency in OOP.

---

## 📂 Project Structure

```
src/
├── presentation/
│   ├── Menu.java
│   ├── Controller.java
│   ├── ConductorController.java
│   └── CompositorController.java
├── persistence/
│   ├── interfaces/
│   ├── csv/
│   └── json/
├── business/
│   ├── managers/
│   │   ├── EditionManager.java
│   │   ├── ExecutionManager.java
│   │   └── TrialManager.java
│   ├── models/
│   │   ├── Trial.java
│   │   ├── PublicArticle.java
│   │   ├── StudyMaster.java
│   │   ├── PhDefense.java
│   │   └── BudgetRequest.java
│   └── Work.java
```

---

## 📖 Technologies & Concepts

- **Language:** Java.
- **Subject:** Object-Oriented Programming (OOP).
- **Architecture:** Layered Architecture.
- **OOP Principles:** Inheritance, Polymorphism, Encapsulation, Abstraction.
- **GRASP Patterns Applied:**
  - **Controller** → `Controller`, `ConductorController`, and `CompositorController` act as intermediaries between the UI (`Menu`) and the business logic.
  - **Creator** → Managers (`EditionManager`, `ExecutionManager`, `TrialManager`) are responsible for creating and managing their related entities.
  - **High Cohesion & Low Coupling** → Separation of responsibilities between layers and managers ensures maintainability and scalability.
  - **Polymorphism** → The abstract class `Trial` and its subclasses (`PublicArticle`, `StudyMaster`, `PhDefense`, `BudgetRequest`) allow handling different trial types through a single interface.
- **Persistence:** JSON and CSV.
- **Concurrency:** use of *threads* for trial execution.
