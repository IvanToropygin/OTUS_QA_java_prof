# 🚀 Test Automation Framework

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://openjdk.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.x-green.svg)](https://selenium.dev/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-orange.svg)](https://maven.apache.org/)
[![Guice](https://img.shields.io/badge/Guice-5.1-lightgrey.svg)](https://github.com/google/guice)

## 🌟 Обзор проекта

Современный фреймворк для автоматизированного тестирования с использованием:

- **Java 17** (LTS версия)
- **Selenium WebDriver 4.x**
- **Google Guice** для dependency injection
- **Maven** для управления зависимостями
- **JUnit 5**

## ⚙️ Требования

### Проверка установки Java

java -version
Ожидаемый вывод:
openjdk version "17.0.x" 2023-10-17 LTS


### Проверка установки Maven

mvn --version
Ожидаемый вывод:

Apache Maven 3.9.x ...
Java version: 17.0.x ...

## Запуск тестов

mvn clean test



## 💡 Лучшие практики

✅ Используйте аннотации Guice (@Inject)

✅ Придерживайтесь Page Object Model

✅ Разделяйте тестовые данные и логику

✅ Используйте явные ожидания (WebDriverWait)
