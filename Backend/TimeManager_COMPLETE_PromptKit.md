# ⏱️ TIME MANAGEMENT SYSTEM — COMPLETE PROMPT KIT
### HCLTech Hackathon | Team: Srinath · Santhosh · Angel · Vamsi
### GitHub: https://github.com/itssanthosh28/Timemanager
### Stack: React.js · Spring Boot · JWT · PostgreSQL · Maven · Port: 8081

---

> ## ✅ HOW TO USE
> 1. **Team Lead (Santhosh)** → Run MASTER PROMPT first together (10 min)
> 2. **Each member** → Copy YOUR individual prompt → paste into Claude → start coding
> 3. **Git** → Work on your branch only → commit every 30 min → merge to dev when done

---

---

# 🧠 MASTER PROMPT — Run Together First

```
You are a senior full-stack architect. Our team of 4 is building a Time Attendance System for an HCLTech final hackathon assessment. This decides our offer letter conversion.

## PROBLEM STATEMENT
Build a Time Attendance System where:
- Employees log daily working hours against a project
- Managers approve or reject timesheet entries
- Employees track their submission history (PENDING / APPROVED / REJECTED)
- Managers view all employee entries and take action

## ACTORS
- EMPLOYEE: Login → Submit timesheet entry → View own history
- MANAGER: Login → View all entries → Approve or Reject → View approval history

## TECH STACK
- Frontend: React.js (JavaScript)
- Backend: Spring Boot + Maven
- Security: JWT
- Database: PostgreSQL
- Port: 8081
- DB Name: time_attendence_db

## TEAM MEMBERS & ROLES
1. Srinath  → Backend: JWT Security (User entity, AuthController, SecurityConfig, JwtUtil, JwtFilter)
2. Santhosh → Backend: Business Logic (Project entity, Timesheet entity, all services/controllers/repos/DTOs)
3. Angel    → Frontend: Auth (Login page, AuthContext, ProtectedRoute, RoleRoute, axios setup, Navbar)
4. Vamsi    → Frontend: Features (Employee dashboard, Submit timesheet form, History page, Manager dashboard, Approve/Reject UI)

## FINAL DATABASE SCHEMA (USE EXACTLY AS GIVEN)

### USERS table
- id: SERIAL PRIMARY KEY
- name: VARCHAR NOT NULL
- email: VARCHAR UNIQUE NOT NULL
- password: VARCHAR NOT NULL
- role: VARCHAR NOT NULL ('EMPLOYEE' or 'MANAGER')
- manager_id: INT FOREIGN KEY → users(id) NULL (employee points to their manager)

### PROJECTS table
- id: SERIAL PRIMARY KEY
- name: VARCHAR NOT NULL

### TIMESHEETS table (Core table)
- id: SERIAL PRIMARY KEY
- user_id: INT FK → users(id) NOT NULL
- project_id: INT FK → projects(id) NOT NULL
- date: DATE NOT NULL
- hours: INT NOT NULL
- status: VARCHAR NOT NULL DEFAULT 'PENDING' ('PENDING' / 'APPROVED' / 'REJECTED')
- approved_by: INT FK → users(id) NULL (manager who approved/rejected)
- remarks: VARCHAR NULL (manager's comment)

## FINAL API DESIGN (USE EXACTLY AS GIVEN)
- POST /api/auth/login                    → Public
- POST /api/auth/register                 → Public
- POST /api/timesheets                    → EMPLOYEE: submit entry
- GET  /api/timesheets/my/{userId}        → EMPLOYEE: view own history
- GET  /api/timesheets                    → MANAGER: view ALL entries
- POST /api/timesheets/{id}/approve       → MANAGER: approve or reject entry

## application.properties
server.port=8081
spring.datasource.url=jdbc:postgresql://localhost:5432/time_attendence_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
jwt.secret=hcltech-timemanager-secret-key-2024-secure
jwt.expiration=86400000

## GitHub Repo: https://github.com/itssanthosh28/Timemanager
## Branches:
- main → production
- dev  → integration
- feature/backend-security  → Srinath
- feature/backend-logic     → Santhosh
- feature/frontend-auth     → Angel
- feature/frontend-features → Vamsi

## YOUR TASKS

### 1. CONFIRM PACKAGE STRUCTURE
Package: com.hcltech.timemanager

src/main/java/com/hcltech/timemanager/
  config/         → SecurityConfig.java, CorsConfig.java
  controller/     → AuthController.java, TimesheetController.java, ProjectController.java
  service/        → TimesheetService.java, ProjectService.java (interfaces)
  service/impl/   → TimesheetServiceImpl.java, ProjectServiceImpl.java
  repository/     → UserRepository.java, TimesheetRepository.java, ProjectRepository.java
  entity/         → User.java, Project.java, Timesheet.java
  dto/request/    → LoginRequest.java, RegisterRequest.java, TimesheetRequest.java, ApprovalRequest.java
  dto/response/   → AuthResponse.java, TimesheetResponse.java, ProjectResponse.java, ApiResponse.java
  mapper/         → TimesheetMapper.java
  exception/      → GlobalExceptionHandler.java, ResourceNotFoundException.java
  security/       → JwtUtil.java, JwtAuthFilter.java, UserDetailsServiceImpl.java
  enums/          → Role.java, TimesheetStatus.java

### 2. TEAM FILE OWNERSHIP (No overlaps)
Output a table: Member | Branch | Files They Own | Files They NEVER Touch

### 3. ARCHITECTURE FLOW
Client → Controller → Service → Repository → DB
JWT: Request → JwtAuthFilter → SecurityContext → Controller

### 4. KEY BUSINESS LOGIC
Explain how TimesheetServiceImpl handles:
- submitEntry(): save with status=PENDING, set user from JWT token
- approveOrReject(): find timesheet → set status (APPROVED/REJECTED) → set approved_by (manager id) → set remarks → save

### 5. REACT FOLDER STRUCTURE
src/
  context/AuthContext.js
  routes/ProtectedRoute.jsx, RoleRoute.jsx
  services/authService.js, timesheetService.js, projectService.js
  pages/Login.jsx, EmployeeDashboard.jsx, SubmitTimesheet.jsx, TimesheetHistory.jsx, ManagerDashboard.jsx
  components/Navbar.jsx, StatusBadge.jsx, LoadingSpinner.jsx
  App.js

### 6. GIT SETUP COMMANDS
Exact commands to create all 4 feature branches from dev in the repo itssanthosh28/Timemanager

Output everything as a concise technical brief the team reads together in 10 minutes.
```

---
---

# 👤 PROMPT 1 — SRINATH (Backend: JWT Security)

```
You are a senior Spring Boot security engineer. I am Srinath, building JWT security for a Time Attendance System (HCLTech hackathon). This is my offer letter assessment.

## MY DETAILS
- Package: com.hcltech.timemanager
- Branch: feature/backend-security
- GitHub: https://github.com/itssanthosh28/Timemanager
- Port: 8081, DB: time_attendence_db

## FILES I OWN (I create these — Santhosh does NOT touch these)
- enums/Role.java
- enums/TimesheetStatus.java  ← shared enum, I create it
- entity/User.java
- repository/UserRepository.java
- security/JwtUtil.java
- security/JwtAuthFilter.java
- security/UserDetailsServiceImpl.java
- config/SecurityConfig.java
- config/CorsConfig.java
- controller/AuthController.java
- dto/request/LoginRequest.java
- dto/request/RegisterRequest.java
- dto/response/AuthResponse.java
- dto/response/ApiResponse.java  ← shared wrapper, I create it
- application.properties

## GENERATE COMPLETE, COMPILABLE CODE FOR EACH:

### 1. enums/Role.java
public enum Role { EMPLOYEE, MANAGER }

### 2. enums/TimesheetStatus.java
public enum TimesheetStatus { PENDING, APPROVED, REJECTED }

### 3. entity/User.java
- Annotations: @Entity, @Table(name="users"), @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
- Fields:
  - id: Long, @Id @GeneratedValue(strategy=IDENTITY)
  - name: String, @Column(nullable=false)
  - email: String, @Column(unique=true, nullable=false)
  - password: String, @Column(nullable=false)
  - role: String (store as "EMPLOYEE" or "MANAGER" — simple VARCHAR as per DB design)
  - managerId: Integer, @Column(name="manager_id") — nullable
  - createdAt: LocalDateTime, @CreationTimestamp
- Implements UserDetails:
  - getAuthorities() → return List.of(new SimpleGrantedAuthority("ROLE_" + role))
  - getUsername() → return email
  - getPassword() → return password
  - isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled → all return true

### 4. repository/UserRepository.java
- findByEmail(String email) → Optional<User>
- existsByEmail(String email) → boolean
- findByManagerId(Integer managerId) → List<User>
- findByRole(String role) → List<User>

### 5. security/JwtUtil.java
- @Component
- @Value("${jwt.secret}") private String secret
- @Value("${jwt.expiration}") private long expiration
- generateToken(UserDetails userDetails) → JWT with subject=email, claim "role"=user.role, HS256
- extractUsername(String token) → String
- extractRole(String token) → String
- isTokenValid(String token, UserDetails userDetails) → boolean
- Use jjwt 0.11.5 (Keys.hmacShaKeyFor(secret.getBytes()))

### 6. security/JwtAuthFilter.java
- Extend OncePerRequestFilter, @Component, @RequiredArgsConstructor, @Slf4j
- Extract Bearer token → validate → set UsernamePasswordAuthenticationToken in SecurityContextHolder
- Catch all exceptions silently (log warn, let security handle)

### 7. security/UserDetailsServiceImpl.java
- Implements UserDetailsService
- loadUserByUsername(email) → findByEmail → orElseThrow UsernameNotFoundException

### 8. config/SecurityConfig.java
- @Configuration, @EnableWebSecurity, @RequiredArgsConstructor
- SecurityFilterChain bean (modern API — NOT WebSecurityConfigurerAdapter)
- Public: POST /api/auth/login, POST /api/auth/register
- MANAGER only (hasRole): GET /api/timesheets, POST /api/timesheets/**/approve
- Authenticated: POST /api/timesheets, GET /api/timesheets/my/**, GET /api/projects/**
- sessionManagement: STATELESS, csrf: disabled, cors: enabled
- addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter)
- BCryptPasswordEncoder @Bean
- AuthenticationManager @Bean
- AuthenticationProvider @Bean (DaoAuthenticationProvider)

### 9. config/CorsConfig.java
- allowedOrigins "*", allowedMethods GET/POST/PUT/DELETE/OPTIONS, allowedHeaders "*"

### 10. dto/response/ApiResponse.java (Generic — Santhosh also uses this)
```java
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder().success(true).message(message)
            .data(data).timestamp(LocalDateTime.now()).build();
    }
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder().success(false).message(message)
            .timestamp(LocalDateTime.now()).build();
    }
}
```

### 11. dto/request/RegisterRequest.java
- name (@NotBlank), email (@Email @NotBlank), password (@Size(min=6) @NotBlank), role (@NotBlank), managerId (Integer, nullable)

### 12. dto/request/LoginRequest.java
- email (@Email @NotBlank), password (@NotBlank)

### 13. dto/response/AuthResponse.java
- token, userId (Long), name, email, role, managerId (Integer)

### 14. controller/AuthController.java
- @RestController @RequestMapping("/api/auth") @RequiredArgsConstructor @Slf4j
POST /api/auth/register:
  - Check existsByEmail → 400 if exists
  - Build User → encode password → role defaults logic
  - Save → generateToken → return 201 ApiResponse.success("Registered", AuthResponse)

POST /api/auth/login:
  - authManager.authenticate() in try-catch BadCredentialsException → 401
  - Load user → generateToken → return 200 ApiResponse.success("Login successful", AuthResponse)

### 15. application.properties
server.port=8081
spring.datasource.url=jdbc:postgresql://localhost:5432/time_attendence_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
jwt.secret=hcltech-timemanager-secret-key-2024-secure
jwt.expiration=86400000

### 16. pom.xml — list all required dependencies with groupId, artifactId, version
(spring-boot-starter-web, security, data-jpa, validation, postgresql, lombok, jjwt 0.11.5 x3)

## RULES
- 100% complete compilable code — zero placeholders
- Lombok everywhere
- Every controller method: log.info("...") at start
- Always return ApiResponse<T>

## GIT COMMANDS
git clone https://github.com/itssanthosh28/Timemanager.git
cd Timemanager && git checkout dev && git pull origin dev
git checkout -b feature/backend-security
git push origin feature/backend-security
# After coding:
git add . && git commit -m "feat(security): JWT auth, SecurityConfig, User entity, AuthController"
git push origin feature/backend-security
# Then PR: feature/backend-security → dev on GitHub

## INTERVIEW PREP — Know These Cold
- Why JWT over sessions? → Stateless, scales horizontally, no server-side session storage
- What does OncePerRequestFilter guarantee? → Filter executes exactly once per HTTP request
- Why BCrypt? → Adaptive hashing, salted, brute-force resistant
- Why SecurityFilterChain? → Modern Spring, not deprecated, more composable than WebSecurityConfigurerAdapter
- How does ROLE_ prefix work? → hasRole("MANAGER") checks for "ROLE_MANAGER" in authorities
- What if JWT expired? → ExpiredJwtException thrown in filter, caught silently, auth not set, → 403
```

---
---

# 👤 PROMPT 2 — SANTHOSH (Backend: Business Logic)

```
You are a senior Spring Boot developer. I am Santhosh, building all business logic for a Time Attendance System (HCLTech hackathon). My offer letter depends on this.

## MY DETAILS
- Package: com.hcltech.timemanager
- Branch: feature/backend-logic
- GitHub: https://github.com/itssanthosh28/Timemanager

## WHAT SRINATH HANDLES — I DO NOT RECREATE THESE
entity/User.java, security/*, config/SecurityConfig.java, controller/AuthController.java,
dto/response/ApiResponse.java, enums/Role.java, enums/TimesheetStatus.java

## FILES I OWN
entity/Project.java, entity/Timesheet.java,
repository/ProjectRepository.java, repository/TimesheetRepository.java,
dto/request/TimesheetRequest.java, dto/request/ApprovalRequest.java,
dto/response/TimesheetResponse.java, dto/response/ProjectResponse.java,
mapper/TimesheetMapper.java,
service/TimesheetService.java, service/impl/TimesheetServiceImpl.java,
service/ProjectService.java, service/impl/ProjectServiceImpl.java,
controller/TimesheetController.java, controller/ProjectController.java,
exception/ResourceNotFoundException.java, exception/GlobalExceptionHandler.java

## GENERATE COMPLETE CODE:

### entity/Project.java
@Entity @Table(name="projects") @Data @Builder @NoArgsConstructor @AllArgsConstructor
Fields: id (Long PK auto), name (String not null)

### entity/Timesheet.java
@Entity @Table(name="timesheets") @Data @Builder @NoArgsConstructor @AllArgsConstructor
Fields:
- id: Long PK auto
- user: @ManyToOne(fetch=LAZY) @JoinColumn(name="user_id", nullable=false) → User
- project: @ManyToOne(fetch=LAZY) @JoinColumn(name="project_id", nullable=false) → Project
- date: LocalDate, @Column(nullable=false)
- hours: Integer, @Column(nullable=false)
- status: String, @Column(nullable=false) — stores "PENDING"/"APPROVED"/"REJECTED"
- approvedBy: @ManyToOne(fetch=LAZY) @JoinColumn(name="approved_by") → User (nullable)
- remarks: String (nullable)
- createdAt: LocalDateTime @CreationTimestamp
- updatedAt: LocalDateTime @UpdateTimestamp

### repository/TimesheetRepository.java
- findByUser_Id(Long userId) → List<Timesheet>
- findAllByOrderByCreatedAtDesc() → List<Timesheet>
- findByUser_IdOrderByDateDesc(Long userId) → List<Timesheet>

### repository/ProjectRepository.java
- existsByName(String name) → boolean
- findByNameIgnoreCase(String name) → Optional<Project>

### dto/request/TimesheetRequest.java
- projectId (@NotNull Long)
- date (@NotNull LocalDate)
- hours (@NotNull @Min(1) @Max(24) Integer)

### dto/request/ApprovalRequest.java
- status (@NotBlank String) — "APPROVED" or "REJECTED"
- remarks (String, nullable)

### dto/response/TimesheetResponse.java
- id, userId, employeeName, projectId, projectName, date, hours, status,
  approvedById (nullable), approvedByName (nullable), remarks, createdAt

### dto/response/ProjectResponse.java
- id, name

### mapper/TimesheetMapper.java
@Component
- toResponse(Timesheet t) → TimesheetResponse (map all fields manually, handle null approvedBy)
- toResponseList(List<Timesheet>) → List<TimesheetResponse>

### service/ProjectService.java (interface)
- createProject(String name) → ProjectResponse
- getAllProjects() → List<ProjectResponse>
- getProjectById(Long id) → ProjectResponse

### service/impl/ProjectServiceImpl.java
@Service @RequiredArgsConstructor @Slf4j
- createProject: check existsByName → throw if exists → save → return ProjectResponse
- getAllProjects: findAll → map to list
- getProjectById: findById or throw ResourceNotFoundException

### service/TimesheetService.java (interface)
- submitEntry(Long userId, TimesheetRequest) → TimesheetResponse
- getMyEntries(Long userId) → List<TimesheetResponse>
- getAllEntries() → List<TimesheetResponse>
- approveOrReject(Long timesheetId, Long managerId, ApprovalRequest) → TimesheetResponse

### service/impl/TimesheetServiceImpl.java
@Service @RequiredArgsConstructor @Slf4j @Transactional
Inject: TimesheetRepository, ProjectRepository, UserRepository, TimesheetMapper

submitEntry(Long userId, TimesheetRequest request):
  1. log.info("Employee {} submitting timesheet", userId)
  2. fetch User by id → orElseThrow ResourceNotFoundException
  3. fetch Project by request.projectId → orElseThrow ResourceNotFoundException
  4. build Timesheet with status="PENDING"
  5. save and return mapper.toResponse()

getMyEntries(Long userId):
  1. log.info("Fetching history for user {}", userId)
  2. return mapper.toResponseList(repo.findByUser_IdOrderByDateDesc(userId))

getAllEntries():
  1. log.info("Manager fetching all entries")
  2. return mapper.toResponseList(repo.findAllByOrderByCreatedAtDesc())

approveOrReject(Long timesheetId, Long managerId, ApprovalRequest request):
  1. log.info("Manager {} processing timesheet {} → {}", managerId, timesheetId, request.getStatus())
  2. Validate status is "APPROVED" or "REJECTED" → else throw IllegalArgumentException
  3. fetch timesheet → orElseThrow ResourceNotFoundException
  4. if status already APPROVED or REJECTED → throw RuntimeException("Entry already processed")
  5. fetch manager User → orElseThrow
  6. set timesheet.status, timesheet.approvedBy, timesheet.remarks
  7. save and return mapper.toResponse()

### controller/ProjectController.java
@RestController @RequestMapping("/api/projects") @RequiredArgsConstructor @Slf4j
- POST / → createProject(@RequestParam String name) → 201
- GET / → getAllProjects() → 200

### controller/TimesheetController.java
@RestController @RequestMapping("/api/timesheets") @RequiredArgsConstructor @Slf4j
Inject: TimesheetService, UserRepository

Helper method:
  private Long getCurrentUserId() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    return userRepository.findByEmail(email).orElseThrow().getId();
  }

- POST /               → submitEntry: Long userId = getCurrentUserId() → 201
- GET /my/{userId}     → getMyEntries(userId) → 200
- GET /                → getAllEntries() → 200
- POST /{id}/approve   → approveOrReject: Long managerId = getCurrentUserId() → 200

All return ApiResponse<T>.

### exception/ResourceNotFoundException.java
extends RuntimeException
constructor(String resource, String field, Object value)
message: resource + " not found with " + field + " : " + value

### exception/GlobalExceptionHandler.java
@RestControllerAdvice @Slf4j
- ResourceNotFoundException → 404
- MethodArgumentNotValidException → 400 (return field error map)
- RuntimeException → 400
- Exception → 500
All return ApiResponse.error(message)

## RULES
- All service methods: @Transactional, log at start
- No business logic in controllers
- Complete compilable code — zero placeholders

## GIT COMMANDS
git clone https://github.com/itssanthosh28/Timemanager.git
cd Timemanager && git checkout dev && git pull origin dev
git checkout -b feature/backend-logic
git push origin feature/backend-logic
git add . && git commit -m "feat(logic): Timesheet/Project entities, services, controllers"
git push origin feature/backend-logic
# PR: feature/backend-logic → dev

## INTERVIEW PREP
- Why Service Interface + Impl? → Loose coupling, testable, easy to swap implementation
- Why @Transactional? → Atomic DB operations, auto rollback on failure
- Why no logic in controller? → Single Responsibility Principle
- How does approveOrReject prevent double action? → Check current status before update
- Explain the timesheet lifecycle → PENDING (submitted) → APPROVED or REJECTED (manager action)
- Why Mapper class? → Decouples entity from API response, hides internal DB structure
```

---
---

# 👤 PROMPT 3 — ANGEL (Frontend: Auth Module)

```
You are a senior React developer. I am Angel, building the complete auth frontend for a Time Attendance System (HCLTech hackathon). My offer letter depends on this.

## MY DETAILS
- React.js, JavaScript (NO TypeScript)
- Backend: http://localhost:8081/api
- Branch: feature/frontend-auth
- GitHub: https://github.com/itssanthosh28/Timemanager

## FILES I OWN
src/utils/tokenUtils.js, src/services/authService.js,
src/context/AuthContext.js, src/routes/ProtectedRoute.jsx, src/routes/RoleRoute.jsx,
src/pages/Login.jsx, src/components/Navbar.jsx, src/App.js

## GENERATE COMPLETE WORKING CODE:

### src/utils/tokenUtils.js
- saveToken(token), getToken(), removeToken()
- saveUser(user) → JSON.stringify to localStorage
- getUser() → JSON.parse from localStorage
- removeUser(), clearAuth() → removes both token and user
- decodeToken(token) → JSON.parse(atob(token.split('.')[1]))
- isTokenExpired(token) → decoded.exp * 1000 < Date.now()

### src/services/authService.js
- Create axios instance: baseURL = 'http://localhost:8081/api'
- Request interceptor: attach Authorization: Bearer {token} if token exists
- Response interceptor: if 401 → clearAuth() → window.location.href = '/login'
- Export this axios instance as `api` — Vamsi imports this for his API calls
- export login(email, password) → POST /auth/login → return response.data
- export register(name, email, password, role, managerId) → POST /auth/register → return response.data

### src/context/AuthContext.js
State: { user: {userId, name, email, role, managerId}, isAuthenticated, loading }
- On mount (useEffect): check token → if valid and not expired → restore user from localStorage
- loginUser(email, password): call login() → save token + user → set state → return { success, role }
- logoutUser(): clearAuth() → reset state → window.location.href = '/login'
- Export AuthProvider component + useAuth() custom hook

### src/routes/ProtectedRoute.jsx
- If loading → show loading div
- If !isAuthenticated → Navigate to /login
- Else → Outlet

### src/routes/RoleRoute.jsx
Props: allowedRoles (array e.g. ['MANAGER'])
- If loading → show loading div
- If !isAuthenticated → Navigate to /login
- If !allowedRoles.includes(user.role) → Navigate to /dashboard (or role-specific home)
- Else → Outlet

### src/pages/Login.jsx
Design: Split layout — dark navy left panel (40%) with app branding, white right panel with form.
Left panel: "⏱️ TimeTracker" in large bold white, tagline below in muted color.
Right panel: centered white card, "Welcome back" title, email + password fields.

Features:
- Client validation: email required + format, password required
- Inline field errors in red below each input
- API error banner above form
- Submit button: full width, navy, "Signing in..." loading state
- On success: if role=MANAGER → navigate('/manager/dashboard'), else → navigate('/employee/dashboard')
- Generate complete JSX with all inline styles (no CSS file needed)

### src/components/Navbar.jsx
- Show only when isAuthenticated
- Left: "⏱️ TimeTracker" brand link → role-appropriate home
- Middle links:
  - EMPLOYEE: Dashboard | Submit Hours | My History
  - MANAGER: All Entries
- Right: "{user.name}" + role badge (green for MANAGER, blue for EMPLOYEE) + Logout button
- Dark navy background (#1a2744), white/muted links
- All inline styles

### src/App.js
Set up full routing structure:
- Public: /login
- Protected > RoleRoute(['EMPLOYEE']): /employee/dashboard, /employee/submit, /employee/history
- Protected > RoleRoute(['MANAGER']): /manager/dashboard
- Default / → Navigate to /login

Leave Vamsi's page imports as comments so he can uncomment them:
// import EmployeeDashboard from './pages/EmployeeDashboard';
// import SubmitTimesheet from './pages/SubmitTimesheet';
// import TimesheetHistory from './pages/TimesheetHistory';
// import ManagerDashboard from './pages/ManagerDashboard';

And their routes as commented JSX:
{/* <Route path="/employee/dashboard" element={<EmployeeDashboard />} /> */}

## RULES
- Functional components + hooks only
- No TypeScript, no UI library (no MUI, no Tailwind)
- Complete code with ALL imports
- Handle loading states properly

## GIT COMMANDS
git clone https://github.com/itssanthosh28/Timemanager.git
cd Timemanager && git checkout dev && git pull origin dev
git checkout -b feature/frontend-auth
git push origin feature/frontend-auth
git add . && git commit -m "feat(auth): Login, AuthContext, ProtectedRoute, RoleRoute, Navbar, App.js"
git push origin feature/frontend-auth
# PR: feature/frontend-auth → dev

## INTERVIEW PREP
- Why AuthContext over prop drilling? → One place for auth state, any component accesses via useAuth()
- What does RoleRoute add over ProtectedRoute? → Role-level access control on top of auth check
- Why save user in localStorage? → Persists auth across browser refresh
- How does axios interceptor work? → Runs before every request, silently attaches Bearer token
- What happens on 401? → Interceptor clears auth + redirects to /login automatically
- How does login redirect work per role? → loginUser returns role, navigate based on role value
```

---
---

# 👤 PROMPT 4 — VAMSI (Frontend: Feature Pages)

```
You are a senior React developer. I am Vamsi, building all feature pages for a Time Attendance System (HCLTech hackathon). My offer letter depends on this.

## MY DETAILS
- React.js, JavaScript
- Branch: feature/frontend-features
- GitHub: https://github.com/itssanthosh28/Timemanager

## WHAT ANGEL BUILT — DO NOT RECREATE
AuthContext, ProtectedRoute, RoleRoute, Login.jsx, Navbar.jsx, authService.js, App.js

## I IMPORT api LIKE THIS:
import { api } from '../services/authService';

## FILES I OWN
src/services/timesheetService.js, src/services/projectService.js,
src/pages/EmployeeDashboard.jsx, src/pages/SubmitTimesheet.jsx,
src/pages/TimesheetHistory.jsx, src/pages/ManagerDashboard.jsx,
src/components/StatusBadge.jsx, src/components/LoadingSpinner.jsx

## API ENDPOINTS I USE
POST /timesheets           body: { projectId, date, hours }
GET  /timesheets/my/{id}   → employee history
GET  /timesheets           → all entries (manager)
POST /timesheets/{id}/approve  body: { status, remarks }
GET  /projects             → project list

## GENERATE COMPLETE CODE:

### src/services/timesheetService.js
import { api } from './authService';
- submitTimesheet(projectId, date, hours) → POST /timesheets → return response.data
- getMyTimesheets(userId) → GET /timesheets/my/{userId} → return response.data
- getAllTimesheets() → GET /timesheets → return response.data
- approveOrReject(id, status, remarks) → POST /timesheets/{id}/approve → return response.data

### src/services/projectService.js
import { api } from './authService';
- getAllProjects() → GET /projects → return response.data

### src/components/StatusBadge.jsx
Props: status ("PENDING" | "APPROVED" | "REJECTED")
Colored pill badge:
- PENDING  → yellow background #fffbeb, text #b45309, border #fcd34d
- APPROVED → green background #f0fdf4, text #166534, border #86efac
- REJECTED → red background #fff1f2, text #9f1239, border #fda4af
Inline styles, small font, rounded

### src/components/LoadingSpinner.jsx
Centered spinning circle using CSS animation (@keyframes spin)
Navy color matching app theme

### src/pages/EmployeeDashboard.jsx
On mount: fetch getMyTimesheets(user.userId)
Display:
- Greeting: "Good day, {user.name} 👋"
- 4 stat cards: Total Entries | Pending | Approved | Total Hours
- 2 action buttons: "+ Log Today's Hours" (→ /employee/submit) | "View Full History" (→ /employee/history)
- Loading spinner during fetch
- Navy + white color scheme, clean card layout, all inline styles

### src/pages/SubmitTimesheet.jsx
On mount: fetch getAllProjects() → populate dropdown
Form fields:
- Project: <select> dropdown populated with projects
- Date: <input type="date"> defaulting to today (new Date().toISOString().split('T')[0])
- Hours: <input type="number"> min=1 max=24
Client validation: all fields required, hours 1-24
On submit:
  - call submitTimesheet(projectId, date, hours)
  - On success: show green banner "✅ Entry submitted! Status: PENDING" → reset form
  - On error: show red error banner with API message
Loading state on submit button
"← Back to Dashboard" link at top
White card layout, inline styles

### src/pages/TimesheetHistory.jsx
On mount: fetch getMyTimesheets(user.userId)
Display as HTML table:
Columns: Date | Project | Hours | Status | Approved By | Remarks | Submitted On
- StatusBadge component in Status column
- "Approved By" → show name or "—" if null
- "Remarks" → show text or "—" if null
- Empty state: "No entries yet." + link to /employee/submit
- Loading spinner
- Error state if fetch fails
All inline styles, clean table with alternating row backgrounds

### src/pages/ManagerDashboard.jsx
On mount: fetch getAllTimesheets()
Features:
- Title: "Team Timesheet Entries"
- Filter tabs: All | Pending | Approved | Rejected
  - Each tab shows count badge: "Pending (5)"
  - Clicking tab filters entries using Array.filter() client-side
- Table columns: Employee | Project | Date | Hours | Status | Submitted On | Action
- Action column:
  - If PENDING: "✅ Approve" button + "❌ Reject" button
  - If APPROVED/REJECTED: just show "—"
- On "Approve" click:
  - remarks = window.prompt("Add remarks (optional):") ?? ''
  - call approveOrReject(id, "APPROVED", remarks)
  - re-fetch all entries (call getAllTimesheets again)
- On "Reject" click:
  - remarks = window.prompt("Reason for rejection (required):")
  - if (!remarks) → alert("Reason is required for rejection") → return
  - call approveOrReject(id, "REJECTED", remarks)
  - re-fetch all entries
- Loading spinner during fetch and during each approve/reject action
- Empty state per filter tab
- All inline styles, same navy color scheme

### ROUTES TO ADD IN App.js
Paste these inside the correct RoleRoute sections:

Inside RoleRoute(['EMPLOYEE']):
<Route path="/employee/dashboard" element={<EmployeeDashboard />} />
<Route path="/employee/submit" element={<SubmitTimesheet />} />
<Route path="/employee/history" element={<TimesheetHistory />} />

Inside RoleRoute(['MANAGER']):
<Route path="/manager/dashboard" element={<ManagerDashboard />} />

### ALSO show me the import lines to add at top of App.js:
import EmployeeDashboard from './pages/EmployeeDashboard';
import SubmitTimesheet from './pages/SubmitTimesheet';
import TimesheetHistory from './pages/TimesheetHistory';
import ManagerDashboard from './pages/ManagerDashboard';

## RULES
- All async: try/catch with error state
- useEffect for data on mount, useState for all state
- Functional components only
- Complete code with ALL imports — no placeholders
- No TypeScript, no UI framework

## GIT COMMANDS
git clone https://github.com/itssanthosh28/Timemanager.git
cd Timemanager && git checkout dev && git pull origin dev
git checkout -b feature/frontend-features
git push origin feature/frontend-features
git add . && git commit -m "feat(features): Employee/Manager dashboards, Submit, History pages"
git push origin feature/frontend-features
# PR: feature/frontend-features → dev

## INTERVIEW PREP
- How does submit flow work end-to-end? → Fill form → POST /timesheets → status=PENDING → visible in history
- How does manager approve? → Fetch all PENDING entries → click Approve → POST /{id}/approve with APPROVED
- How does filter tab work? → One API call, filter in-memory with Array.filter() by status
- How do you handle loading states? → useState flag, show spinner while true, hide on complete
- What is the status lifecycle? → PENDING → APPROVED or REJECTED (manager action)
- How does re-fetching work after approve/reject? → Call getAllTimesheets() again, React re-renders table
```

---
---

# 🔀 GIT WORKFLOW — EXACT COMMANDS

## TEAM LEAD (Santhosh) — ONE-TIME SETUP
```bash
git clone https://github.com/itssanthosh28/Timemanager.git
cd Timemanager

# Ensure dev branch exists
git checkout main
git checkout -b dev 2>/dev/null || git checkout dev
git push origin dev

# Create all 4 feature branches FROM dev
git checkout dev

git checkout -b feature/backend-security && git push origin feature/backend-security && git checkout dev
git checkout -b feature/backend-logic && git push origin feature/backend-logic && git checkout dev
git checkout -b feature/frontend-auth && git push origin feature/frontend-auth && git checkout dev
git checkout -b feature/frontend-features && git push origin feature/frontend-features && git checkout dev

echo "✅ All branches created! Share the repo link with team."
```

## EACH MEMBER — DAILY WORKFLOW
```bash
# Start of session
git checkout feature/YOUR-BRANCH
git fetch origin && git merge origin/dev

# Work... then commit every 30 min
git add .
git commit -m "feat: what you did"
git push origin feature/YOUR-BRANCH
```

## PULL REQUEST (when module is complete)
```
GitHub → Timemanager repo → Pull Requests → New Pull Request
Base: dev | Compare: feature/YOUR-BRANCH
Title: "feat: [YourName] - Module Name"
→ Create PR → Team Lead merges
```

## FILE OWNERSHIP (Zero Conflict Policy)
| Member | Branch | Owns | Never Touches |
|--------|--------|------|---------------|
| Srinath | feature/backend-security | entity/User.java, security/*, config/SecurityConfig.java, config/CorsConfig.java, controller/AuthController.java, dto/(Login\|Register\|Auth)*.java, dto/response/ApiResponse.java, enums/*.java, application.properties | Everything else |
| Santhosh | feature/backend-logic | entity/(Project\|Timesheet).java, repository/(Project\|Timesheet)Repository.java, service/*, controller/(Timesheet\|Project)Controller.java, mapper/*.java, exception/*.java | Srinath's files |
| Angel | feature/frontend-auth | src/utils/tokenUtils.js, src/services/authService.js, src/context/AuthContext.js, src/routes/*.jsx, src/pages/Login.jsx, src/components/Navbar.jsx, src/App.js | Vamsi's pages |
| Vamsi | feature/frontend-features | src/services/(timesheet\|project)Service.js, src/pages/(Employee\|Manager)*.jsx, src/components/(StatusBadge\|LoadingSpinner).jsx | Angel's files |

## ⏱️ HACKATHON TIME PLAN
| Time | Activity |
|------|----------|
| 0:00–0:15 | Read problem + run Master Prompt together |
| 0:15–0:30 | Santhosh creates branches, Srinath generates Spring Boot project at start.spring.io |
| 0:30–3:00 | Everyone codes independently. Commit every 30 min. |
| 3:00–3:30 | All PRs to dev. Merge. Integration test full flow. |
| 3:30–4:00 | Bug fixes, polish, final merge dev → main |
| 4:00–4:30 | Demo run + practice interview answers |

## 🎤 DEMO LINE (Memorize This)
"We built a Time Attendance System using React and Spring Boot, secured with JWT. Employees log daily hours against projects, and managers approve or reject each entry. We used a clean 3-table design — users with self-referencing manager_id, projects, and timesheets — where approval state lives directly in the timesheet table to keep it simple and fast. Our layered Spring Boot architecture separates Controller, Service, and Repository concerns, following industry best practices."
