🎓 Quản Lý Sinh Viên - CRUD REST API
Dự án Java Spring Boot xây dựng RESTful API quản lý thông tin sinh viên, sử dụng SQL Server làm cơ sở dữ liệu và SpringDoc OpenAPI / Swagger UI để tài liệu hóa và kiểm thử API.
---
🛠️ Công nghệ sử dụng
Công nghệ	Phiên bản / Mô tả
Java	17
Spring Boot	3.2.4
Spring Data JPA	ORM và thao tác CSDL
Hibernate	JPA implementation
Microsoft SQL Server	Database
SpringDoc OpenAPI	Tài liệu API
Swagger UI	Kiểm thử REST API
Maven	Quản lý dependencies và build project
---
📁 Cấu trúc dự án
```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── example/
    │           └── lab3/
    │               ├── Lab3Application.java
    │               │
    │               ├── controller/
    │               │   └── StudentController.java
    │               │
    │               ├── service/
    │               │   └── StudentService.java
    │               │
    │               ├── repository/
    │               │   └── StudentRepository.java
    │               │
    │               └── entity/
    │                   └── Student.java
    │
    └── resources/
        └── application.properties
```
---
📌 Mô tả các thành phần
`Lab3Application.java`: Class khởi chạy ứng dụng Spring Boot.
`StudentController.java`: Tiếp nhận và xử lý các HTTP request từ client.
`StudentService.java`: Xử lý logic nghiệp vụ của ứng dụng.
`StudentRepository.java`: Thực hiện truy vấn và thao tác dữ liệu thông qua Spring Data JPA.
`Student.java`: Entity ánh xạ với bảng `students` trong SQL Server.
`application.properties`: Cấu hình kết nối cơ sở dữ liệu và các thiết lập của ứng dụng.
---
🗄️ Cấu hình cơ sở dữ liệu
1. Tạo database và bảng
Mở SQL Server Management Studio (SSMS) và thực thi script sau:
```sql
CREATE DATABASE quanlysinhvien;
GO

USE quanlysinhvien;
GO

CREATE TABLE [dbo].[students] (
    [id] UNIQUEIDENTIFIER NOT NULL,
    [student_code] NVARCHAR(50) NOT NULL,
    [full_name] NVARCHAR(255) NOT NULL,
    [email] NVARCHAR(255) NOT NULL,
    [phone] VARCHAR(255) NULL,
    [class_name] VARCHAR(255) NULL,
    PRIMARY KEY (id)
);
GO
```
2. Thêm dữ liệu mẫu
```sql
INSERT INTO [dbo].[students]
    ([id], [student_code], [full_name], [email], [phone], [class_name])
VALUES
    (
        'e78a09ed-6a65-48ce-be7e-410f6de63c89',
        'SV007',
        N'Đặng Nhật Anh',
        'g@gmail.com',
        '0966789012',
        'C2024A'
    ),
    (
        '5b599794-18e8-4a45-a6c2-755dcaf3881a',
        'SV001',
        N'Nguyễn Văn A',
        'a@gmail.com',
        '0901234567',
        'C2024A'
    );
GO
```
---
⚙️ Cấu hình ứng dụng
File cấu hình: `src/main/resources/application.properties`
```properties
spring.application.name=lab3

spring.datasource.url=jdbc:sqlserver://localhost;instanceName=SQLSERVER;databaseName=quanlysinhvien;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=123123
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

server.port=8080
```
> ⚠️ **Lưu ý:** Hãy thay đổi `spring.datasource.username` và `spring.datasource.password` theo tài khoản SQL Server trên máy của bạn. **Không nên commit mật khẩu thật lên GitHub.**
---
🌐 REST API Endpoints
STT	Method	Endpoint	Mô tả
1	GET	`/api/students`	Lấy danh sách tất cả sinh viên
2	GET	`/api/students?keyword=...`	Tìm kiếm sinh viên theo từ khóa
3	GET	`/api/students/{id}`	Lấy thông tin sinh viên theo UUID
4	POST	`/api/students`	Thêm sinh viên mới
5	PUT	`/api/students/{id}`	Cập nhật thông tin sinh viên
6	DELETE	`/api/students/{id}`	Xóa sinh viên theo UUID
🔎 Tìm kiếm sinh viên
API hỗ trợ tìm kiếm linh hoạt theo: Mã sinh viên, Họ và tên, Email, Số điện thoại.
Ví dụ theo tên: `GET /api/students?keyword=Nguyễn`
Ví dụ theo mã: `GET /api/students?keyword=SV001`
---
📤 Ví dụ Request / Payload
1. Thêm sinh viên mới (`POST /api/students`)
Headers: `Content-Type: application/json`
Body:
```json
{
    "studentCode": "SV002",
    "fullName": "Trần Văn B",
    "email": "b@gmail.com",
    "phone": "0912345678",
    "className": "C2024A"
}
```
2. Cập nhật sinh viên (`PUT /api/students/{id}`)
Headers: `Content-Type: application/json`
Body:
```json
{
    "studentCode": "SV002",
    "fullName": "Trần Văn B",
    "email": "tranvanb@gmail.com",
    "phone": "0912345678",
    "className": "C2024A"
}
```
3. Xóa sinh viên (`DELETE /api/students/{id}`)
```http
DELETE /api/students/5b599794-18e8-4a45-a6c2-755dcaf3881a
```
---
🚀 Hướng dẫn chạy dự án
Bước 1: Clone repository
```bash
git clone <URL_REPOSITORY_CUA_BAN>
cd <TEN_THU_MUC_DU_AN>
```
Bước 2: Chuẩn bị SQL Server
Cài đặt và khởi động Microsoft SQL Server.
Mở SSMS và tạo database tên `quanlysinhvien`.
Thực thi đoạn script SQL ở mục Cấu hình cơ sở dữ liệu.
Bước 3: Cấu hình kết nối database
Mở file `src/main/resources/application.properties` và cập nhật lại thông tin tài khoản SQL Server của bạn:
```properties
spring.datasource.username=<YOUR_USERNAME>
spring.datasource.password=<YOUR_PASSWORD>
```
(Nếu SQL Server của bạn sử dụng port hoặc instance khác, hãy điều chỉnh lại `spring.datasource.url` cho phù hợp).
Bước 4: Chạy ứng dụng
Cách 1: Mở project bằng IntelliJ IDEA, Eclipse hoặc VS Code, sau đó chạy class `Lab3Application.java`.
Cách 2: Sử dụng terminal với Maven wrapper:
Trên macOS / Linux: `./mvnw spring-boot:run`
Trên Windows: `mvnw.cmd spring-boot:run`
Ứng dụng sẽ khởi chạy tại: `http://localhost:8080`
---
📚 Swagger UI & Kiểm thử API
Sau khi ứng dụng khởi động thành công, bạn có thể truy cập giao diện Swagger UI để xem tài liệu và test API trực quan:
👉 URL: http://localhost:8080/swagger-ui/index.html
Các công cụ kiểm thử khác:
Bạn cũng có thể dùng Postman, cURL hoặc REST Client trên VS Code.
Ví dụ kiểm tra danh sách sinh viên bằng cURL:
```bash
  curl http://localhost:8080/api/students
  ```
---
📌 Tóm tắt tính năng
✅ Hiển thị danh sách sinh viên.
✅ Tìm kiếm sinh viên theo từ khóa (Mã, Tên, Email, SĐT).
✅ Xem thông tin chi tiết sinh viên theo ID.
✅ Thêm, Cập nhật, Xóa sinh viên (CRUD đầy đủ).
✅ Kết nối SQL Server thông qua Spring Data JPA.
✅ Tài liệu hóa và kiểm thử API tự động bằng Swagger UI.
