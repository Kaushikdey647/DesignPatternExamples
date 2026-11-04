# Java 17 Lab

Maven multi-module repo for learning Java, anchored on Java 17. One module per topic; resources in [docs/resources.md](docs/resources.md).

| Module | Topic |
|---|---|
| 01-java-core | OOP, generics, collections, streams |
| 02-java17-features | records, sealed classes, pattern matching |
| 03-jdk-util-api | `java.util` |
| 04-effective-java | Effective Java 3e |
| 05-concurrency | threads, JMM, `java.util.concurrent` |
| 06-loom | virtual threads (JDK 21) |
| 07-dsa | data structures & algorithms |
| 08-design-patterns | Head First Design Patterns |
| 09-lld | low-level design problems |
| 10-jvm-internals | GC, class loading, JMH |
| 11-spring | Spring / Spring Boot |
| 12-reactive | Reactor, WebFlux |
| 13-micronaut | Micronaut |
| 14-jakarta-ee | Jakarta EE |
| 15-java21-features | record patterns, switch patterns, sequenced collections (JDK 21) |

## Build

```
mvn clean verify                 # all modules
mvn -pl 08-design-patterns test  # one module
```

Open the root `pom.xml` as a project in IntelliJ IDEA.
