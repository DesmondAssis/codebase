# 目录
[TOC]

# Quartz
### 主要接口

 - Schedule
 - Trigger
 
	 -  SimpleTrigger
	 - CronTrigger
 - Job
 - JobDetail
 
### 示例代码
```java
package com.desmond.codebase.qaurtz;

import java.io.Serializable;
import java.util.Date;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Li.Xiaochuan on 17/8/4.
 */
public class MyJob implements Serializable, Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new Date() + ": doging something.");
    }

    public static void main(String[] args) throws SchedulerException {
        // 1. define the job and tie it to our MyJob class
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        // 2. trigger the job to run now, and the repeat every 10 seconds
        SimpleTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        simpleSchedule()
                                .withIntervalInSeconds(10)
                                .repeatForever())
                .build();

        // 3. tell quartz to schedule the job using our trigger
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();

    }

}
```

### 基本思路
 业务工作放在自定义job中，e.g. MyJob implements Job. MyJob在由JobDetail 创建。JobDetail 有JobDataMap 用来给Job instance 传递各种各样的参数.
 

```java
// define the job and tie it to our DumbJob class
  JobDetail job = newJob(DumbJob.class)
      .withIdentity("myJob", "group1") // name "myJob", group "group1"
      .usingJobData("jobSays", "Hello World!")
      .usingJobData("myFloatValue", 3.141f)
      .build();

public class DumbJob implements Job {

    public DumbJob() {
    }

    public void execute(JobExecutionContext context)
      throws JobExecutionException
    {
      JobKey key = context.getJobDetail().getKey();

      JobDataMap dataMap = context.getJobDetail().getJobDataMap();

      String jobSays = dataMap.getString("jobSays");
      float myFloatValue = dataMap.getFloat("myFloatValue");

      System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }
  }
```
常见的Trigger有SimpleTrigger/CronTrigger,前者可以用于简单的触发器，后者则使用cron expression作为出发条件，用于高级的日期触发。Scheduler是一个容器，负责注册trigger/jobdetail, 并把他们配对。在合适的时候利用trigger出发job execution。容器中有一个线程池，用来并行调度执行每个作业，这样可以提高容器效率。
![这里写图片描述](http://img1.51cto.com/attachment/200907/200907231248316831062.png)
#### 动态调度
Quartz的JobDetail、Trigger都可以在运行时重新设置，并且在下次调用时候起作用。这就为动态作业的实现提供了依据。你可以将调度时间策略存放到数据库，然后通过数据库数据来设定Trigger，这样就能产生动态的调度


# Thread
## 线程基本概念
### 状态
1. new , 创建后进入的状态, e.g. Thread t = new MyThread();
2. runnable, 调用 t.start, 就绪，等待cpu
3. running, 运行。只有先runnable才能到running.
4. blocked. 放弃对cpu使用权，停止执行
	1. 等待阻塞：运行状态中的线程执行wait()方法，使本线程进入到等待阻塞状态；

   2.同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态；

   3.其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。
5. dead. 执行完或者异常退出run,生命周期结束.
6. 状态图
   ![状态图](./images/notes/thread-status.jpg)
7. [参考](http://www.cnblogs.com/lwbqqyumidi/p/3804883.html)

### 创建方式 
	1. extend Thread
	2. implemnts Runnable
	3. implemnts Callable
		feature 其实是一种Callable. thread->Runnable->Callable
## 线程安全实现方法
#### 互斥（阻塞/唤醒）同步
	1. synchronized
		1. 方法
		2. 代码块
		3. 锁 实例/类
	2. ReetrantLock
	3. 可重入锁定义
	 ```
	 线程可以进入任何一个它已经拥有的锁所同步着的代码块
	 ```
#### 非阻塞同步（乐观锁）
```
CAS(compare and swap), 变量内存地址V， 旧的预期值A, 新值B, 当且仅当V=A时，处理器用B更新V的值，否则就不执行。该操作利用cpu指令，为原子性的。但是会产生A-B-A的问题。i.e. 如果变量V初次读取的是A,并且准备赋值检查时仍然是A,没有问题吗？如果他在这段时间被改为B,然后又被改为了A,那么CAS会认为他没改变。为解决这个问题加一个变量控制版本. 1A-2B-3A
```
#### 无同步方案
	1. 可重入代码
	```
	状态量都由参数出入等，不应用堆上数据集，不使用公用的系统资源的功能.
	```
	2. 线程本地存储(ThreadLocal)
#### synchronized 与 reentrantLock 比较
[参考](http://blog.csdn.net/fw0124/article/details/6672522)
## 线程池
## 分布式锁
### 乐观锁/悲观锁
# 数据库事务
 `事务把数据库从一种一致性的状态转换成另一种一致状态`
## ACID
1. Atomicity 原子性 `要不都失败，要不都成功`
2. Consistncy 一致性 `在一个事务执行之前和执行之后数据库必须处于一致性状态，比如买票，库存表扣掉的库存会到订单表里`
3. Isolation 隔离性 `并发的事务是相互隔离的`
4. Durability 持久性 `系统或者介质发生故障时，已经提交的事务不丢失`

## ACID 实现原理
### Atomicity/Durablity
```
借助undo log:
a=1,b=2
事务开始
数据读入内存
记录a=1到undolog
修改a=10(内存中)
记录b=2到undolog
修改b=20(内存中)
将undolog写入磁盘(我理解为数据已经到数据库中了，已经持久化了，只是缺少一个标识说这是不是一个正常的数据)
将数据从内存中写入磁盘
提交事务
io频繁

undolog + redolog:
a=1,b=2
事务开始
记录a=1到undolog
修改a=10
记录a=10到redolog
记录b=2到undolog
修改b=20
记录b=20到redolog
将redolog 写入磁盘
提交事务

把undo也写入redolog中，而redolog可以缓存起来,可以减少io.
回滚操作本质上也是对数据进行修改.
```
### Consistency
```
事务开始前和开始后都必须处于一致的状态，这个感觉要Mysql+应用程序一起来控制
```

### Isolation
```
由存储引擎(比如innodb)来实现,采用Next-key Locking方式来加锁。
Record Lock:单个行记录锁
GAP Lock:间隙锁，锁定一个范围，但不包含记录本身，

Next-key Lock:锁定一定范围，并锁定记录本身=record lock + gap lock
比如有一个字id, 值有 1,3,5,8,10,17,22
那没 锁的范围可能 为 (-无穷,1],(1,8],(8,17],(17~+无穷)
那么trans1 真该更改 8, 那么 1~17 是被锁定的(8为record lock, 1~7,9~17为gap lock)，其他transaction 是不能修改1~17的.

```

## Isolation隔离性

### 隔离级别
1. 读未提交(Read Uncommitted)
2. 读已提交(Read Committed)
3. 可重复读(Repeatable Read)
4. 可串行化(Serializable)
5. Mysql 默认是 Repetable Read, Oracle 默认是 Read Committed

### Isolation并发带来的问题
1. 脏读 `读到未提交的数据`
2. 不可重复读 `同一事务中如果在T1读取了一些记录,在T2再读同样的记录时，这些记录可能被修改、删除不见了`
3. 幻读 `保证在同一事务中可重复读数据，但是在另事务中更新后某一结果后，当前事务再更新时，就会“惊奇的”发现了这些新数据，貌似之前读到的数据是“鬼影”一样的幻觉`
### 不同隔离级别出现的问题（Y-会出现，N-不会出现）

|隔离级别|脏读|不可重复读|幻读|
|:--:|:--:|:--:|:--:|
|读未提交|Y|Y|Y|
|读提交|N|Y|Y|
|可重复读|N|N|Y|
|串型化|N|N|N|



### 示例
`myqsl 事务隔离级别分为全局级别和会话级别,spring 中可以指定当前事务的隔离级别,默认采用default,即数据库的事务隔离级别，区别是通过客户端连接的一个会话,启动会话isolation时会去读会话的isolation,之后自己可以通过set 去改变global isolation(全局改完，系统的会话初始的isolation也会改变，当开始新的session,会采用默认的系统的会话isolation), 也可以set 当前会话的isolation.`

`可重复读：tra1 开启，第一次读数据有2条，tra2 开启，插入一条新数据，然后commit, 这时候tra1再查询，查到的数据仍然是2条。也就是说对于tra1事务没有结束，不管其他事务是否修改了数据，那么数据都是不变的。这样会导致幻读`

`感觉问题都是一级一级新产生<->解决的，终极方案是用串行化： 读未提交->产生脏读现象->为解决脏读，推迟读提交->产生不可重复读现象->为解决不可重复度，推出可重复度->产生幻读现象->为解决幻读，推出终极方案：串行化`


```sql
select @@global.tx_isolation,@@tx_isolation;
set global txt_isolation='repeatable-read';
set txt_isolation='repeatable-read';
```

[参见具体示例](http://blog.csdn.net/taylor_tao/article/details/7063639)

## Spring中的事务
### 事务隔离级别
`同数据库的，默认采用数据库设置的级别`
### 事务的传播性
`事务传播性都是针对方法而言的`

|类型|描述|
|:--:|:--:|
|REQUIRED|	业务方法需要在一个事务中运行,如果方法运行时,已处在一个事务中,那么就加入该事务,否则自己创建一个新的事务.这是spring默认的传播行为|
|SUPPORTS|	如果业务方法在某个事务范围内被调用,则方法成为该事务的一部分,如果业务方法在事务范围外被调用,则方法在没有事务的环境下执行|
|MANDATORY|	只能在一个已存在事务中执行,业务方法不能发起自己的事务,如果业务方法在没有事务的环境下调用,就抛异常|
|REQUIRES_NEW|	业务方法总是会为自己发起一个新的事务,如果方法已运行在一个事务中,则原有事务被挂起,新的事务被创建,直到方法结束,新事务才结束,原先的事务才会恢复执行|
|NOT_SUPPORTED|	声明方法需要事务,如果方法没有关联到一个事务,容器不会为它开启事务.如果方法在一个事务中被调用,该事务会被挂起,在方法调用结束后,原先的事务便会恢复执行|
|NEVER|	声明方法绝对不能在事务范围内执行,如果方法在某个事务范围内执行,容器就抛异常.只有没关联到事务,才正常执行|
|NESTED|	如果一个活动的事务存在,则运行在一个嵌套的事务中.如果没有活动的事务,则按REQUIRED|属性执行.它使用了一个单独的事务, 这个事务拥有多个可以回滚的保证点.内部事务回滚不会对外部事务造成影响, 它只对DataSourceTransactionManager 事务管理器起效|

#设计模式


----------

[1]: http://math.stackexchange.com/