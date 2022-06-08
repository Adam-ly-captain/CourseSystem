# course_system

| 接口地址                      | 功能说明                                       | 接口说明                                                     | 样例                                                         |
| ----------------------------- | ---------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| /user/login                   | 登录                                           | account代表账号，password代表密码，登录成功返回UID           | ![image-20220608064551861](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608064551861.png) |
| /user/register                | 注册（管理员功能）                             | password代表密码，roleId代表角色ID，其中0代表管理员，1代表教师，2代表学生，name表示用户真实名；注册成功后返回随机账号 | ![image-20220608064633742](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608064633742.png) |
| /course/add                   | 添加课程（管理员功能）                         | courseName不能重复，否则返回400，courseDesc代表课程信息描述，hours代表学时数，credit代表学分，college代表学院名 | ![image-20220608165755085](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608165755085.png) |
| /course/update                | 更新课程信息（管理员功能）                     | courseId代表课程ID，courseName代表课程名，courseDesc代表课程描述，hours代表学时数，credit代表学分，college代表学院名；同样需要注意课程名不能重复，否则返回400 | ![image-20220608171312878](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608171312878.png) |
| /course/get/page              | 返回课程分页信息（管理员功能）                 | pageNum代表第N页，pageSize代表第N页的数据量                  | ![image-20220608171653591](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608171653591.png) |
| /courseoffering/add           | 添加课程设置（管理员功能）                     | classroom代表教室名，teacherId代表教师ID，dayOfWeek代表星期几，lessonOfDay代表第几节课开始此课程，courseId代表课程ID，courseOfferingName代表课程设置名，startWeek代表课程设置起始周，stopWeek代表结课周，maxNum代表该课程设置最大人数；该接口会自动检测时间以及教室冲突 | ![image-20220608173120510](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608173120510.png) |
| /courseoffering/update        | 更新课程设置（管理员功能）                     | courseOfferingId代表该课程设置ID，其他参数与前一个接口描述功能一致；该接口也同样会自动检测时间以及教室冲突 | ![image-20220608173634692](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608173634692.png) |
| /courseoffering/get/all/page  | 获取课程设置分页数据（管理员功能）             | pageNum代表第N页数据，pageSize代表需要返回的一页数据的数据量 | ![image-20220608174029082](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608174029082.png) |
| /student/select               | 学生选课（学生功能）                           | sid代表学生ID，courseOfferingId代表课程设置ID；该接口会自动检测选课时间是否冲突 | ![image-20220608174845189](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608174845189.png) |
| /student/cancel               | 取消选课（学生功能）                           | sid代表学生ID，courseOfferingId代表课程设置ID；如果未选择过该课程设置则返回400状态码 | ![image-20220608175704512](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608175704512.png) |
| /student/get/curriculum/{sid} | 获取学生课表（学生功能）                       | sid代表学生ID                                                | http://localhost:8080/student/get/curriculum/4               |
| /student/show/courseofferings | 获取学生分页选课列表数据（学生功能）           | sid为学生ID，pageNum为第N页数据，pageSize代表该页总数据量    | ![image-20220608185740180](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608185740180.png) |
| /teacher/get/students         | 获取该课程设置下的分页学生信息数据（教师功能） | tid代表教师ID，courseOfferingId代表课程设置ID，pageNum代表第N页，pageSize代表该页数据量 | ![image-20220608180152495](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608180152495.png) |
| /teacher/get/curriculum       | 获取教师课表（教师功能）                       | tid代表教师ID，pageNum为第N页数据，pageSize代表该页总数据量  | ![image-20220608191304183](C:\Users\59845\AppData\Roaming\Typora\typora-user-images\image-20220608191304183.png) |