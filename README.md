# 🧠 NeetCode Practice (Java 21)

Мой репозиторий для систематического прохождения курсов [NeetCode.io](https://neetcode.io/). 
Все решения написаны на современной **Java 21 LTS** с использованием идиоматичных подходов (Records, Switch Expressions, Stream API) и покрыты **JUnit 5** тестами.

## 📂 Структура проекта

Репозиторий организован как многомодульный Maven-проект:

* **`common/`** — Общие структуры данных (`ListNode`, `TreeNode`), используемые в задачах.
* **`beginner/`** — Базовый курс (Arrays, Two Pointers, Stack, Linked List, Binary Search и т.д.).
* **`advanced/`** — Продвинутый курс (Advanced Graphs, 2D DP, Bit Manipulation и т.д.).

## 🚀 Как запустить тесты

Для запуска всех тестов во всех модулях выполните:
```bash
mvn clean test