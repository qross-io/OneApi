# 统一接口 OneApi

OneApi 是区别与传统SSM后端开发的新的接口开发方式，可极大的提高开发效率，减少大量重复代码的开发工作。

## OneApi 接口示例
基于文件的管理方式的接口示例（出自Master项目）：
```sql
## title | GET | id=1 |
SELECT title FROM jobs WHERE id=#{jobId} -> FIRST CELL

## title | PUT |
UPDATE qross_jobs SET title="#{"title"}", mender=@userid WHERE id=#{id}

## filter | GET | offset=0 |
IF $id IS NOT UNDEFINED THEN
    SELECT id, title, cron_exp FROM qross_jobs WHERE id=#{id};
ELSIF '#{title}' IS EMPTY THEN
    SELECT id, title, cron_exp FROM qross_jobs WHERE id<>#{jobId} ORDER BY id ASC LIMIT #{offset}, 15;
ELSE
    SELECT id, title, cron_exp FROM qross_jobs WHERE id<>#{jobId} AND title LIKE '%#{title}%' ORDER BY id ASC LIMIT #{offset}, 15;
END IF;
```

## OneApi 主要功能 

在接口中只需要编写核心逻辑，不再需要各种Java类。OneApi运行在Spring Boot环境下。

* 在Spring Boot项目中，Controller只需要配置一次。
* OneApi的接口可以写在Spring Boot项目的文件中，与项目一起受源代码管理。
* OneApi的接口不需要会Java即可进行接口开发。
* OneApi提供基于静态Token和动态Token的权限验证。
* 通过PQL，可以输出任意Json格式的数据接口。
* 通过PQL，可以方便的进行列转行等常用操作，满足前端开发需求。
* 通过PQL，可快速访问任意关系型数据库和其他数据源如 Redis 等。
* 通过PQL，可快速将输出各种文件并实现文件下载功能。

## OneApi 文档及技术支持

OneApi 免费使用，有任何使用问题均可联系作者或留言。

**参考文档 [www.qross.cn/oneapi](http://www.qross.cn/oneapi)**  
**官方网站 [www.qross.io](http://www.qross.io)**  
**作者邮箱 [wu@qross.io](mailto:wu@qross.io)**