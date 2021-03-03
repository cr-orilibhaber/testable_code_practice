Test Aware Development
=====================

**Unit testing poorly designed *legacy code*** to improve its test coverage and code quality.

What is it about?
-------------------
The code in this repository should be treated as ***legacy code* that is used in production** and **needs to be unit tested**.
Although this is a **very small piece of code**, it has **a lot of the problems that are common in actual *legacy code***.

Rules
--------------
> You **can't change any existing code unless it is covered by tests**,
**with the exception** that the **change is intended to allow adding unit tests**
and is either an **automated (safe) IDE refactoring** or a **trivial (small, none functional) change.**

> You are **not allowed to use** (and also should not require using) ***PowerMock* or any other bytecode manipulating**
> **testing libraries** to override or get **access** to **members or methods** that are otherwise **inaccessible or**
> **can't be overridden.**

Details
-------
### Where do I start?
The **starting point** is the **TripService** class, **try unit testing it** by following the above rules.
### Ask yourself the following questions
What kind of structural or logical **restrictions are preventing me** from **easily unit testing** the code?

How should I refactor the code to overcome those restrictions using **safe incremental changes**
that are **guaranteed not to degrade its functional integrity?**

What changes are **needed** to promote test coverage and what changes are **desirable** to achieve better code quality?

To make the best out of this exercise, it's also **recommended to write down** (or document in code)
any **encountered code/architectural _smells_** and **how one might resolve them to improve code quality?**

