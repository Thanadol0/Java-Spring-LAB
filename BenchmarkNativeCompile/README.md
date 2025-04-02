# BenchmarkNativeCompile

Spring Boot Lab สำหรับทดลอง **GraalVM Native Image**  
เปรียบเทียบเวลา startup และ logic execution ระหว่าง **JVM** และ **Native Executable**  
พร้อมแสดงผลผ่าน API `/benchmark`

---

## 📦 Features

- แสดงผลผ่าน API `/benchmark` ครบทั้ง result, calculation time, startup time, และ runMode
- รองรับการรันผ่าน `.bat` script
- ใช้ได้ทั้งโหมด JVM และ Native

----

### สำหรับโหมด JVM:
- Java 21+  
- Maven (หรือใช้ `mvnw` ที่แนบมา)

### สำหรับโหมด Native:
- ติดตั้ง GraalVM CE (https://www.graalvm.org/) (เวอร์ชันเดียวกับ Java ที่ใช้ build)
- ติดตั้ง x64 Native Tools ไปที่หน้า: https://visualstudio.microsoft.com/visual-cpp-build-tools/
   ดาวน์โหลดตัวติดตั้ง "Build Tools for Visual Studio 2022"
   เมื่อเปิดขึ้นมา ให้เลือกเฉพาะ (C++) และเลือก :
   ✅ C++ build tools
   ✅ Windows 10 หรือ 11 SDK
   ✅ C++ CMake tools for Windows
   ✅ MSVC v143 - Latest
   ติดตั้งให้เสร็จ แล้ว เปิดด้วย x64 Native Tools Command Prompt for VS 2022 (พิมพ์หาใน Start Menu ได้เลย)

### หมายเหตุเกี่ยวกับ GraalVM **

 - หากคุณใช้ **GraalVM CE 24+** (รุ่นใหม่):  
   `native-image` จะถูกติดตั้งมาพร้อมกับ GraalVM แล้ว ✅

 - หากคุณใช้ **GraalVM รุ่นก่อนหน้า (เช่น 22.x - 23.x)**:  
   คุณต้องติดตั้ง `native-image` เอง โดยใช้คำสั่ง:

   ```(cmd)

   gu install native-image

   ```

---

###  ตรวจสอบว่า GraalVM และ native-image พร้อมใช้งาน
 - เปิด Terminal หรือ Command Prompt แล้วรัน:

   ```(cmd)

    java --version
    native-image --version

   ```

### วิธี run native 2 วิธี
 - วิธีที่ 1: รันผ่าน `.bat` script (ง่ายสุด แนะนำ)
    1. เปิด `x64 Native Tools Command Prompt for VS 2022`  (พิมพ์ใน Start Menu แล้วกดเปิด)
    2. เข้าไปยัง root ของโปรเจกต์ (โฟลเดอร์ที่มี `pom.xml`)
    3. พิมพ์หรือดับเบิลคลิก: **run-native.bat**
    ระบบจะดำเนินการ:
     สร้าง native image อัตโนมัติ 
     รัน .exe ที่ build เสร็จให้ทันที
     พร้อมดูผลที่ http://localhost:8080/benchmark
 - วิธีที่ 2: พิมพ์คำสั่งเอง (manual)
    1. เปิด x64 Native Tools Command Prompt for VS 2022
    2. เข้าไปยัง root ของโปรเจกต์ (โฟลเดอร์ที่มี `pom.xml`)
    3. พิมพ์คำสั่ง:
        .\mvnw -Pnative native:compile
        .\target\BenchmarkNativeCompile.exe
    4. ดูผลที่ http://localhost:8080/benchmark ครับ

## ทิ้งท้าย
ขอบคุณทุกคนที่เข้ามาอ่านและลองเล่น Spring Boot Lab นี้ครับ 
หากพบปัญหา อยากเสนอแนะให้การทดสอบดีขึ้น หรือให้ความรู้เพิ่มเติม(ยินดีรับฟังเป็นอย่างยิ่งครับ) สามารถเปิด issue, pull request หรือติดต่อมาได้เลยครับ :)
ขอบคุณมากครับ..