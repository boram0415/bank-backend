# AccountProject

Spring Boot 백엔드 서비스.

## 스택

- Java 21 (Corretto)
- Spring Boot 4.1.x / Spring 7
- Gradle (Kotlin DSL)
- Spring Web, Spring Data JPA, Spring Security, Validation, Actuator
- H2 (local), MySQL (prod)
- Lombok, DevTools

## 실행

```bash
# local (H2 in-memory, 기본 프로파일)
./gradlew bootRun

# prod 프로파일로 실행
SPRING_PROFILES_ACTIVE=prod DB_URL=... DB_USERNAME=... DB_PASSWORD=... ./gradlew bootRun
```

기동 후 확인:

```bash
curl http://localhost:8080/api/health
curl http://localhost:8080/actuator/health
```

H2 콘솔 (local): http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:accountdb;MODE=MySQL;DB_CLOSE_DELAY=-1`
- User: `sa` / Password: (없음)

## 프로파일

- `local` — H2 in-memory, `ddl-auto=update`, SQL 로깅 ON
- `prod` — MySQL, `ddl-auto=validate`, 자격증명은 환경변수로 주입

## 테스트

```bash
./gradlew test
```

## 프로젝트 구조

```
src/main/java/com/boram/account/
├── AccountProjectApplication.java
├── api/                    # REST 컨트롤러
└── config/                 # 설정 (Security 등)
```

## 학습 회고 (devlog)

매일의 학습·구현·트러블슈팅 기록은 [`docs/devlog/`](docs/devlog) 참고.
관점 3가지: **오늘 한 것 / 막힌 것·해결 / 내일 할 것**
