# Launch Interceptor Program: Requirements Specification

Implements the Launch Interceptor Program which is a tool used to determine whether an input of radar tracking information fulfills the requirements of launching an interceptor. 

## Description
The Launch Interceptor Program implements a boolean function called `Decide()`, which determines whether an interceptor should be launched. If the radar tracking information produces a certain combination of Launch Interceptor Conditions (LIC) then the Decide function will return "YES"; indicating that an interceptor should be launched. Conversely, if `Decide()` returns "NO" then the interceptor should not be launched. In total there are fifteen LICs which have been implemented as boolean functions; each returning either `true` (the LIC was fulfilled) or `false` (LIC was not fulfilled).

* The *Condition Met Vector* (CMV) is a boolean vector with 15 entries, each corresponding to the satisfaction of the LICs. E.g. `CVM[i]` is ´true´ if the LIC #i has been satisfied.

* The *Logical Connector Matrix* (LCM) is a 15x15 matrix with entries of logical connectors; **ANDD**, **ORR** and **NOTUSED**. It determines how an individual LIC (e.g. LIC #i) should be logically combined with another LIC (e.g. LIC #j).

* The *Preliminary Unlocking Matrix* (PUM) is a 15x15 boolean matrix in which each entry corresponds to the satisfaction of the logical combination of the LCM and CVM elements. E.g. `PUM[i][j]` is false if either `CVM[i]` or `CVM[j]` is false and `LCM[i][j]` is **ANDD**.

* The *Preliminary Unlocking Vector* (PUV) is a boolean vector that indicates whether the LIC should be considered for the interceptor to launch, where each entry corresponds to a LIC.

* The *Final Unlocking Matrix* (FUV) is formed from combining PUV and PUM. For example, `FUV[i]` is set to `true` if either `PUV[i]` is false (LIC #i should not hold back launch) or if all elements in the `PUM[i]` row is true.

Lastly, Decide() determines if the interceptor should launch by checking that all the entries in FUV are true. In all other cases (e.g. if at least one `FUV[i]` is false), the interceptor should not launch.

## Motivation
The aim of this project is to implement a program according to the modern development techniques, which was an assignment given by the course DD2480 Software Engineering Fundamentals. The project was devised from the material in "An experimental evaluation of the assumption of independence in multi-version programming" by J.C. Knight and N.G. Leveson, IEEE Transactions on Software Engineering 12(1):96-109 January 1986 (adapted by John Regehr and Martin Monperrus).

## Build Status

[![Build Status](https://travis-ci.org/adibbin/fundamentals-lab1.svg?branch=master)](https://travis-ci.org/adibbin/fundamentals-lab1)

## Code Style

The program is written in Java and follows the [Google Java Style](https://google.github.io/styleguide/javaguide.html) format.

## Getting Started
Please see the following guides to set up the environment for this system:

* [Gradle](https://gradle.org/install/)
* [Travis CI](https://docs.travis-ci.com/user/tutorial/)
* [JUnit](https://junit.org/junit5/docs/current/user-guide/)

Note that Travis CI requires the user to have a GitHub account; how to create an account can be found [here](https://help.github.com/articles/signing-up-for-a-new-github-account/).

Once an account has been set up, the user will be able to get a working copy of the existing Git repository with the following commands:

for Linux:

```shell
$ cd /home/user/my_project
```

for Windows:

```shell
$ cd /c/user/my_project
```

for macOS:

```shell
$ cd /Users/user/my_project
```

and type:

```shell
$ git clone https://github.com/adibbin/fundamentals-lab1.git
```

### Prerequisites

* Gradle version 5.1.1 or greater
* JUnit version 4.12 or greater
* JDK version 8

See section [Built With](#built-with) for more details on the tools.

## How to run

The program will run an instance of `Decide()` with defined global declarations, which are the pre-determined radar tracking data and the parameters for LIC calculations. Modify the radar data in Decide.java and the parameters in Parameters.java to run the program with custom data. The program can be compiled and run through the console by using the following commands from the project root folder:

```shell
cd src/main/java/
javac Decide.java
java Decide -run
```
or 

```shell
gradle build
gradle run --args='-run'
```

## Running Tests

Whenever code is pushed from the local machine to GitHub, tests are performed before allowing the code to merge with the repository. The tests were written using JUnit, where each test is testing either the different LIC requirements, the functions calculating the matrices CVM, PUM, and FUV as well as the Decide() function. If one wishes, it is also possible to perform automated tests on their local copy. See below for more information.

### Testing on local machine

Gradle is a build tool that was used to automate testing for this system. To run the automated tests use the following commands:

```shell
gradle build
gradle test
```
Running the tests should either pass or fail. If the test(s) pass, it should look something like this:

![Pass](https://github.com/adibbin/fundamentals-lab1/blob/master/PassTest.png)

and if it fails:

![Fail](https://github.com/adibbin/fundamentals-lab1/blob/master/FailTest.png)

The tests also verifies whether or not the Java code follows our code style. See the section [Code Style](#code-style) for further details.

### Testing Pull Requests

Travis CI is a build tool that is synced with the GitHub project and performs tests on every Pull Request before merging it.

Please read [workflow.md](https://github.com/adibbin/fundamentals-lab1/blob/master/workflow.md) for more information on the process for submitting pull requests to us.

## Built With

* [Gradle](https://docs.gradle.org/current/userguide/userguide.html) - open-source build automation tool.
* [Travis CI](https://docs.travis-ci.com/) - hosted, distributed continuous integration service.
* [JUnit](https://junit.org/junit5/docs/current/user-guide/) - open source framework.
* [Java SE Development Kit 8](https://docs.oracle.com/javase/8/docs/) - multi-platform programming language.
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)/[Eclipse](https://www.eclipse.org/) - integrated development environment (IDE).

## Contributing

Please read [workflow.md](https://github.com/adibbin/fundamentals-lab1/blob/master/workflow.md) for details on the process for submitting pull requests to us.

The work was divided between the contributors as evenly as possible. Each contributor was assigned the task of implementing at least three different LICs.

## Authors

* **Vera Blomkvist Karlsson** - [verakar](https://github.com/verakar)
* **Gustaf Gunér** - [gustafguner](https://github.com/gustafguner)
* **Adibbin Haider** - [adibbin](https://github.com/adibbin)
* **Sasha Hellstenius** - [sashahe](https://github.com/sashahe)
* **Emelie Tham** - [EmelieTham](https://github.com/EmelieTham)

See also the list of [contributors](https://github.com/adibbin/fundamentals-lab1/contributors) who participated in this project.
