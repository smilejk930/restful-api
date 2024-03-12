# restful-api

## jdk 설정

작업영역에서 `java.configuration.runtimes` 설정을 해준다.

```json
"java.configuration.runtimes": [
        {
            "name": "JavaSE-21",
            "path": "D:\\develop\\dev-workspace\\jdk\\openjdk-21.0.2",
            "default": true
        }
    ]
```

## vscode, cursor 등 사용을 위한 시스템 변수 등록

1. JAVA_HOME
   - openjdk-21.0.2
2. MAVEN_HOME
   - apache-maven-3.9.6

이후 path에 등록

- %JAVA_HOME%\bin
- %MAVEN_HOME%\bin
