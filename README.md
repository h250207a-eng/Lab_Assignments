# Lab Assignment Two: Object-Oriented Programming in Java

This repository contains Java implementations of the laboratory exercises on object-oriented programming. Labs 1-10 contain **50 exercises**, covering Basic, Intermediate and Challenge tasks. Lab 11 contains demonstrations of four design patterns and a **Library Management System** mini-project that brings the main concepts together.

The submission includes source code, sample input files, written reports, recorded program output and a repeatable verification script. Each exercise is stored in its numbered lab folder, and source comments identify its lab and exercise number.

## Learning objectives

The assignment develops the ability to model objects, manage their state and relationships, and apply Java features in small programs and an integrated application.

| Concept | Application in this assignment |
|---|---|
| Encapsulation | Private fields, validated updates, immutable objects and protected collection views. |
| Inheritance | Shared behaviour in class hierarchies, constructor chaining and specialised subclasses. |
| Polymorphism | Overloading, overriding and method dispatch through superclass or interface references. |
| Abstraction | Abstract classes and interfaces that define common contracts for different implementations. |
| Supporting Java features | Exceptions, generics, collections, nested types, enums, records, file I/O, lambdas and streams. |
| Application design | Singleton, Factory, Strategy and Observer examples; a library application with CSV persistence. |

## Guide for review

| Resource | Purpose |
|---|---|
| [Exercise index](#exercise-index) | Maps all 50 exercises to their source files and tasks. |
| [Running the exercises](#running-the-exercises) | Explains compilation, execution and input requirements. |
| [Lab report](report/LAB_REPORT.html) | Presents objectives, source code, recorded outputs and conclusions. Open the HTML file in a web browser. |
| [Lab record](report/LAB_RECORD.md) | Summarises learning outcomes and prediction exercises, with links to evidence. |
| [Library mini-project](#lab-11-library-management-system) | Describes the integrated application and how to run it. |
| [Verification](#verification) | Explains the checks and distinguishes recorded results from a fresh run. |
| [Viva preparation](report/VIVA.md) | Provides notes for the lab manual's oral questions. |

## Requirements

- **Java Development Kit (JDK) 17 or later**, with `java` and `javac` available on `PATH`. A JDK is required for compilation; a Java runtime alone is insufficient.
- **Windows PowerShell** for the commands and verification script shown below.
- Any text editor or Java IDE for viewing and editing the source.

The Java programs use the standard library and require no external dependencies, Maven or Gradle. Java 17 supports the records and pattern matching used in the exercises.

Check the installed tools:

```powershell
java -version
javac -version
```

## Folder structure

| Folder | Contents |
|---|---|
| [1](1/) | Classes and objects |
| [2](2/) | Constructors, `this` and static members |
| [3](3/) | Encapsulation and access modifiers |
| [4](4/) | Inheritance |
| [5](5/) | Polymorphism |
| [6](6/) | Abstraction, abstract classes and interfaces |
| [7](7/) | Exception handling |
| [8](8/) | Generics and collections |
| [9](9/) | Nested types, enums, records and the Object contract |
| [10](10/) | File I/O, lambdas and streams |
| [11](11/) | Design pattern demonstrations and the library mini-project |
| [report](report/) | Lab report, lab record, viva notes and execution evidence |
| [verification](verification/) | Repeatable verification script and Java check programs |

The verification script stores compiled classes and temporary test data in `.build/`. Generated `.class` files, `.build/` and the library's default `11/data/` directory are excluded from Git by [.gitignore](.gitignore).

## Running the exercises

### Compile and run one exercise

Open PowerShell in the project root, then compile and run Lab 1 Exercise 1:

```powershell
cd 1
javac --release 17 -encoding UTF-8 CarBasics.java
java CarBasics
cd ..
```

Expected output:

```text
Toyota (2020), 42000.0 km
Honda (2018), 61000.0 km
Mazda (2022), 18000.0 km
```

Use the `.java` filename when compiling and the public class name without the extension when running. Replace the folder and filename with another entry from the [exercise index](#exercise-index). Each Lab 1-10 exercise runs independently and contains its own helper types.

You can also run an individual source file directly from the workspace root:

```powershell
java .\2\TemperatureFactories.java
```

### Compile all exercises in one folder

From inside the numbered folder:

```powershell
$sources = (Get-ChildItem -Filter *.java).FullName
javac --release 17 -encoding UTF-8 $sources
```

Then run the desired class, for example `java PersonAddress` in Lab 1. Use `cd ..` to return to the project root before switching labs. Compilation creates `.class` files alongside the source; these files are generated output.

### Input and file exercises

| Program | Input and behaviour |
|---|---|
| [SafeDivision](7/SafeDivision.java) | Enter two integers, such as `8 2`. Try `8 0` for division by zero and `hello 2` for invalid input. |
| [WordFrequency](8/WordFrequency.java) | Enter a paragraph on one line. Words are counted without case sensitivity and ranked by frequency, with alphabetical ordering for ties. |
| [FileLines](10/FileLines.java) | Enter five lines. They are saved and read back; fewer than five lines do not create a partial file. |
| [FileStatistics](10/FileStatistics.java) | Reads [sample.txt](10/sample.txt) by default when run from Lab 10, or accepts a file path as its first argument. |
| [LearnerReports](10/LearnerReports.java) | Reads [learners.csv](10/learners.csv) by default when run from Lab 10, or accepts a CSV path and an optional report directory. |

For the following examples, start in Lab 10 and compile that folder first:

```powershell
java FileLines saved-lines.txt
java FileStatistics sample.txt
java LearnerReports learners.csv grade-reports
```

The last command writes `report-A.txt`, `report-B.txt` and `report-C.txt` into `grade-reports`. The learner CSV uses the columns `name,roll,gpa`; this exercise accepts simple, unquoted CSV and does not support commas inside names. Relative file paths are resolved from the terminal's current directory.

## Exercise index

Exercises 1-2 are **Basic**, exercises 3-4 are **Intermediate**, and exercise 5 is the **Challenge** in each of Labs 1-10.

### Lab 1: Classes and objects

| Exercise | Java file | Task |
|---|---|---|
| 1 | [CarBasics.java](1/CarBasics.java) | Create and display three cars. |
| 2 | [CarAntique.java](1/CarAntique.java) | Identify cars more than 25 years old. |
| 3 | [LargestRectangle.java](1/LargestRectangle.java) | Calculate area and perimeter; find the largest of five rectangles. |
| 4 | [RectangleAliasing.java](1/RectangleAliasing.java) | Predict and demonstrate shared object references. |
| 5 | [PersonAddress.java](1/PersonAddress.java) | Use a Person with an Address; demonstrate chained access and null. |

### Lab 2: Constructors, `this` and static members

| Exercise | Java file | Task |
|---|---|---|
| 1 | [CircleConstructors.java](2/CircleConstructors.java) | Use a radius constructor and a default radius of 1.0. |
| 2 | [CircleCounter.java](2/CircleCounter.java) | Count circle constructions with a static counter and getter. |
| 3 | [EmployeeConstructors.java](2/EmployeeConstructors.java) | Use unique IDs, overloaded constructors and a copy constructor. |
| 4 | [InitialisationOrder.java](2/InitialisationOrder.java) | Predict and verify static and instance initialisation order. |
| 5 | [TemperatureFactories.java](2/TemperatureFactories.java) | Provide named temperature factories with a private constructor. |

### Lab 3: Encapsulation and access modifiers

| Exercise | Java file | Task |
|---|---|---|
| 1 | [ValidatedStudent.java](3/ValidatedStudent.java) | Keep student fields private and validate marks from 0 to 100. |
| 2 | [StudentGrades.java](3/StudentGrades.java) | Derive a grade from marks without storing a grade field. |
| 3 | [BankAccountHistory.java](3/BankAccountHistory.java) | Record transactions and expose an unmodifiable history snapshot. |
| 4 | [ImmutablePerson.java](3/ImmutablePerson.java) | Use a final class, private final fields and no setters. |
| 5 | [KelvinTemperature.java](3/KelvinTemperature.java) | Store kelvin internally, provide conversions and reject impossible values. |

### Lab 4: Inheritance

| Exercise | Java file | Task |
|---|---|---|
| 1 | [PersonStudent.java](4/PersonStudent.java) | Inherit Person behaviour and add a student's course. |
| 2 | [PersonTeacher.java](4/PersonTeacher.java) | Share Person code between Student and Teacher. |
| 3 | [VehicleInheritance.java](4/VehicleInheritance.java) | Trace Vehicle, Car and ElectricCar constructor calls. |
| 4 | [AnimalConstructor.java](4/AnimalConstructor.java) | Explain a missing superclass default constructor and use `super(name)`. |
| 5 | [ComposedStack.java](4/ComposedStack.java) | Replace ArrayList inheritance with composition. |

### Lab 5: Polymorphism

| Exercise | Java file | Task |
|---|---|---|
| 1 | [MethodOverloading.java](5/MethodOverloading.java) | Overload `print` for int, double, String and int arrays. |
| 2 | [AnimalPolymorphism.java](5/AnimalPolymorphism.java) | Call Cat, Cow and Duck sounds through Animal references. |
| 3 | [EmployeePayroll.java](5/EmployeePayroll.java) | Add Intern and calculate payroll through one mixed employee loop. |
| 4 | [FieldMethodDispatch.java](5/FieldMethodDispatch.java) | Compare field hiding with overridden method dispatch. |
| 5 | [PaymentProcessing.java](5/PaymentProcessing.java) | Compare instanceof pattern matching with polymorphic payment processing. |

### Lab 6: Abstraction, abstract classes and interfaces

| Exercise | Java file | Task |
|---|---|---|
| 1 | [AbstractShapes.java](6/AbstractShapes.java) | Implement Circle, Rectangle and Square using an abstract Shape. |
| 2 | [FlyableDemo.java](6/FlyableDemo.java) | Implement Flyable for Bird and Aeroplane. |
| 3 | [AccountAbstraction.java](6/AccountAbstraction.java) | Share deposit logic and specialise account fees. |
| 4 | [ComparableStudents.java](6/ComparableStudents.java) | Sort students by name with Comparable and by marks with Comparator. |
| 5 | [DefaultMethodConflict.java](6/DefaultMethodConflict.java) | Resolve conflicting interface default methods explicitly. |

### Lab 7: Exception handling

| Exercise | Java file | Task |
|---|---|---|
| 1 | [SafeDivision.java](7/SafeDivision.java) | Handle non-numeric input and division by zero. |
| 2 | [SafeArrayAccess.java](7/SafeArrayAccess.java) | Catch invalid array indexes. |
| 3 | [AgeValidation.java](7/AgeValidation.java) | Throw a checked InvalidAgeException outside ages 0-120. |
| 4 | [InsufficientFunds.java](7/InsufficientFunds.java) | Throw a checked exception for an unaffordable withdrawal. |
| 5 | [FinallyReturn.java](7/FinallyReturn.java) | Predict and demonstrate finally execution during a return. |

### Lab 8: Generics and collections

| Exercise | Java file | Task |
|---|---|---|
| 1 | [IntegerArrayList.java](8/IntegerArrayList.java) | Find the sum, maximum and reverse order of ten integers. |
| 2 | [UniqueNames.java](8/UniqueNames.java) | Remove duplicate names with a Set. |
| 3 | [GenericStack.java](8/GenericStack.java) | Implement generic push, pop, peek and isEmpty operations. |
| 4 | [WordFrequency.java](8/WordFrequency.java) | Count words in a Map and sort by frequency descending. |
| 5 | [GenericBubbleSort.java](8/GenericBubbleSort.java) | Sort Integer and String arrays with a bounded generic method. |

### Lab 9: Nested types, enums, records and the Object contract

| Exercise | Java file | Task |
|---|---|---|
| 1 | [LevelAdvice.java](9/LevelAdvice.java) | Switch over LOW, MEDIUM and HIGH enum values. |
| 2 | [StudentRecord.java](9/StudentRecord.java) | Print student records, compare their values and read components. |
| 3 | [BookEquality.java](9/BookEquality.java) | Implement ISBN equality and hash codes; reject duplicate books in a HashSet. |
| 4 | [CustomLinkedList.java](9/CustomLinkedList.java) | Support for-each with a static nested Node and inner iterator. |
| 5 | [CoinChange.java](9/CoinChange.java) | Find the fewest pennies, nickels, dimes and quarters for an amount. |

### Lab 10: File I/O, lambdas and streams

| Exercise | Java file | Task |
|---|---|---|
| 1 | [FileLines.java](10/FileLines.java) | Save five entered lines and read them back. |
| 2 | [RemoveEvenNumbers.java](10/RemoveEvenNumbers.java) | Use a Predicate and removeIf to remove even numbers. |
| 3 | [FileStatistics.java](10/FileStatistics.java) | Count a text file's lines, words and characters. |
| 4 | [AboveAverageStudents.java](10/AboveAverageStudents.java) | Print above-average students' names alphabetically using streams. |
| 5 | [LearnerReports.java](10/LearnerReports.java) | Load CSV learners, group them by grade band and write reports. |

## Lab 11: Library Management System

The [mini-project](11/) is a command-line application for managing library items, members and active loans. It combines encapsulation, inheritance, abstraction and polymorphism with custom exceptions, maps, streams and CSV persistence.

### Compile and run

Starting from the workspace root:

```powershell
cd 11
$sources = (Get-ChildItem -Filter *.java).FullName
javac --release 17 -encoding UTF-8 $sources
java LibraryApp
```

| Option | Operation |
|---|---|
| `1` | Add a book or DVD. |
| `2` | Register a member. |
| `3` | Borrow an item using a member ID and item ID. |
| `4` | Return an item using its item ID. |
| `5` | List overdue loans. |
| `6` | Search items by title. |
| `7` | List all items. |
| `0` | Save and exit. |

To try the application, add an item, register a member, borrow the item and choose `0`. Restart with the same data directory to inspect the saved state or return the item. Completed operations are also saved when input ends.

### Storage and borrowing rules

By default, the application loads and saves `items.csv`, `members.csv` and `loans.csv` in `data/` relative to the current directory. To select a separate data directory:

```powershell
java LibraryApp my-library-data
```

Members may hold at most **three active loans** across both item types. Books have a **14-day** loan period and DVDs have a **7-day** loan period. An item is overdue only when its due date is before today. Invalid saved data is reported at startup and is not replaced with an empty library.

### Design documentation and pattern examples

| Resource | Purpose |
|---|---|
| [PROJECT_REPORT.md](11/PROJECT_REPORT.md) | Requirements mapping, design choices, persistence and limitations |
| [CLASS_DIAGRAM.md](11/CLASS_DIAGRAM.md) | Class relationships in Mermaid format |
| [LibraryTests.java](11/LibraryTests.java) | Repeatable mini-project checks |
| [SingletonFactoryDemo.java](11/SingletonFactoryDemo.java) | Singleton configuration and email/SMS factory demonstration |
| [StrategyObserverDemo.java](11/StrategyObserverDemo.java) | Runtime discount strategy and grade-listener demonstration |

After compiling Lab 11, run the separate pattern examples from that folder:

```powershell
java SingletonFactoryDemo
java StrategyObserverDemo
cd ..
```

The first demonstrates Singleton and Factory; the second demonstrates Strategy and Observer. They are independent examples rather than features of the library menu.

## Verification

From the workspace root, run the [verification script](verification/Verify.ps1):

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\verification\Verify.ps1
```

The script compiles the Java sources with `--release 17 -Xlint:all`, runs all 50 exercises and both pattern demonstrations, and checks selected error paths, file outputs and object invariants. Library persistence is checked across three separate menu sessions: create and save, reload and return, then reload again.

Running verification updates the transcripts in [report/evidence](report/evidence/) and regenerates the HTML lab report. By default, it also renders console-output images from the execution transcripts. These images are output captures, not desktop screenshots. Temporary test data is stored separately in `.build/`.

To generate text-only report evidence:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\verification\Verify.ps1 -SkipImages
```

### Recorded results

The checked-in [verification summary](report/evidence/verification-summary.json) records a successful run on **14 October 2026**, targeting Java 17:

| Check | Result |
|---|---|
| Lab 1-10 exercises | 50 executed successfully |
| Design pattern demonstrations | 2 executed successfully |
| Total recorded executions, including additional checks | 62 passed |
| Exercise boundary and invariant checks | 37 passed |
| Library checks | 26 passed |
| Library menu persistence | Create/save, reload/return and reload again passed |

The 62 recorded executions include the exercises, pattern demonstrations and additional checks; they are not 62 distinct exercises. The assertion totals are recorded in the [exercise checks](report/evidence/ExerciseBoundaryChecks.txt) and [library checks](report/evidence/LibraryChecks.txt).

These results describe the saved run. Rerun verification to assess the current source after any changes. The checks cover selected requirements and boundaries rather than every possible input.

## Implementation choices and limitations

The programs use the following documented rules and conventions:

| Area | Choice |
|---|---|
| Lab 3 marks | A: 75-100; B: 60-74; C: 50-59; F: 0-49 |
| Lab 10 GPA bands | A: 3.5-4.0; B: 3.0 to below 3.5; C: below 3.0, within the valid 0-4 range |
| Circle counter | Counts successful constructions, not currently live objects after garbage collection |
| File statistics | Words are separated by whitespace; characters are Unicode code points, including line endings |
| Library loan periods | Books: 14 days; DVDs: 7 days |
| Overdue rule | A due date before today is overdue; an item due today is not |
| Library storage | Active loans are persisted; returned loans are removed from the active list |

The library uses the computer's local calendar for loan dates and assumes one running instance. Its CSV files are replaced individually; saving all three files is not a single database transaction. It stores active loans without a historical record of returned loans. Further design details and persistence limitations are explained in the [project report](11/PROJECT_REPORT.md).

## Submission contents

The source folders, sample input files, [reports](report/), [class diagram](11/CLASS_DIAGRAM.md) and [verification tools](verification/) provide the implementation and supporting evidence for review.

If submitting a ZIP archive, package the current numbered lab folders, reports, verification tools and this README. Exclude compiled `.class` files, `.build/` and generated library data. Recreate the archive after any subsequent changes so it matches the submitted source and documentation.
